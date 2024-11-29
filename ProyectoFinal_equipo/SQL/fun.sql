-- FUNCION 1

-- Función que regresa el medallero olímpico.

create or replace function medallero() returns setof "record" as
$$
declare
	r record; -- Se declara el record a utilizar
begin
	for r in (select nombrePais as Pais, 
		sum(case when tipo = 'Oro' then 1 else 0 end) as oro,
       	sum(case when tipo = 'Plata' then 1 else 0 end) as plata,
       	sum(case when tipo = 'Bronce' then 1 else 0 end) as bronce
		from (medalla natural join atleta natural join pais)
		group by nombrepais
		order by oro desc, plata desc, bronce desc) 
	-- Se realiza la consulta, en la que se agrupa por paises y se cuentan las medallas que ha ganado cada atleta representante del país
		loop
			return next r;
		end loop;
	return;
end;
$$
language plpgsql;

-- Ejemplo del uso de la función
-- select * from medallero() as (Pais varchar, Oro bigint, Plata bigint, Bronce bigint);


-- FUNCION 2

-- función que regresa la capacidad restante de una localidad si se especifica su id, en otro caso regresa la capacidad restante de todas las localidades.
create or replace function capacidadRestante(idLocalidadS integer default null) 
returns table (idLocalidad int, nombre varchar(50), idEvento int, fecha date, lugaresDisponibles int) as $$
begin

	if idLocalidadS is not null then
		if not exists (select * from localidad l where l.idLocalidad = idLocalidadS) then
			raise exception 'idLocalidad inválido, la localidad con id % no existe', idLocalidadS;
		end if;
	end if;

	return query
	select l.idLocalidad, l.nombre, e.idEvento, e.fecha,  
		l.aforo-(select count(folio):: integer 
		from entrada en 
		where en.idEvento = e.idEvento) as lugaresDisponibles
	from localidad l
	join evento e on l.idLocalidad = e.idLocalidad
	where idLocalidadS is null or l.idLocalidad = idLocalidadS;
end;									
$$ 
language plpgsql;

-- Ejemplos del uso de la función

--Obtener todas las capacidades restantes.
--select * from capacidadRestante();
-- Obtener la capacidad de una localidad en especifico
--select * from capacidadRestante(1);
-- Caso en donde se desee obtener la capacidad de una localidad inválida
--select * from capacidadRestante(200);


-- FUNCION 3

-- Función que recibe el nombre del país y disciplina, regresa los atletas de dicho país que  
-- practican la diciplina, junto con la información de sus entrenadores.
create or replace function equipo(npais varchar,ndisciplina varchar) returns setof "record" as 
$$
declare 
	r record;
begin 
	for r in (select a.numeroPasaporte, a.nombre, a.primerApellido, a.segundoApellido, 
				e.numeroPasaporte, e.nombre, e.primerApellido, e.segundoApellido from	
	atleta as a natural join practicar as p 
	join entrenar as et on et.numeroPasaporteA = a.numeroPasaporte 
	join entrenador e on et.numeroPasaporteE = e.numeroPasaporte
	where a.nombrePais = npais and p.nombreDisciplina = ndisciplina)
	loop
		return next r;
	end loop;
	return;
end;
$$
language plpgsql;

-- Ejemplos del uso de la función
-- Cuando sí hay participantes del país y la disciplina
--select * from equipo('United States','Archery') as (Atleta char(10), nombreA varchar, primerapellidoA varchar, segundoapellidoA varchar, Entrenador char(10), nombreE varchar, primerapellidoE varchar, segundoapellidoE varchar);
-- No existe el país o no hay participantes: resultado vacío
--select * from equipo('UNAM','Swimming') as (Atleta char(10), nombreA varchar, primerapellidoA varchar, segundoapellidoA varchar, Entrenador char(10), nombreE varchar, primerapellidoE varchar, segundoapellidoE varchar);




--------- DISPARADORES ----------

--DISPARADOR 1
-- Disparador que revisa que no se exceda el aforo de la localidad, regresando una excepción si sucede el caso anterior.
-- Se utiliza una función definida previamente llamada capacidadRestante() para poder obtener la capacidad de la localidad a revisar.
create or replace function revisarDisponibilidad()
returns trigger as $$
declare 
	targetLocalidad int; 
	capacidadRestante int;
begin 
		select idLocalidad into targetLocalidad
		from evento 
		where idEvento = new.idEvento;

		select lugaresDisponibles
		into capacidadRestante
		from capacidadRestante(targetLocalidad)
		where idEvento = new.idEvento;
	
		
		if capacidadRestante <= 0 then 
			raise exception 'Se ha alcanzado la capacidad máxima de la localidad especificada.';
		end if;
		
		return new;
end
$$ language plpgsql;

-- Asignamos el disparador a entrada, este se activa antes de insertar o actualizar.
create trigger checkDisponibilidadTrigger
before insert or update on entrada
for each row
execute function revisarDisponibilidad();

--Ejemplo del uso del trigger
-- Se creó una localidad con aforo de 4 y sus resoectivas entidades necesarias para poder insertar entradas a ella. Dicha localidad se encuentra con capacidad máxima. Podemos probar el uso del trigger con:
-- Revisamos las entradas actuales, son 4.
--select * from entrada 
--where idevento = 999
-- Inserción cuando se excede la capacidad, regresará la excepción.
--insert into entrada (folio, nombreFase, idEvento, numeroAsiento, costoBase) values ('ZQ87-ifjd5glhjr-0009', 'Fase 2', 999, 287, 100);


--DISPARADOR 2
-- Agregamos la columna costoFinal a la tabla entrada, al se un atributo calculado, este no aparece en el modelo relacional.
alter table entrada add column costoFinal int;

-- Función que regresa un disparador, el cual se encarga de calcular el precio final de una entrada en especifico.
create or replace function precioFinal()
returns trigger as $$
begin
	-- El precio aumentará en 9% en cada fase que se avance.
    new.costoFinal := new.costoBase *
                      case
                          when f.nombreFase = 'Fase 1' THEN 1.0 -- El precio es el mismo que el de base
                          when f.nombreFase = 'Fase 2' THEN 1.09 
                          when f.nombreFase = 'Fase 3' THEN 1.18
                          when f.nombreFase = 'Fase 4' THEN 1.27
end
    from fase f
    where f.idEvento = new.idEvento;

    return new;
end;
$$ language plpgsql;
-- Asociamos el trigger a la tabla entrada cuando se decida insertar o actualizar.
create trigger actualizarPrecioFinal
before insert or update on entrada
for each row
execute function precioFinal();

--Creamos la tabla de agendas, la cuál contiene la información de los eventos y su respectiva información
create table agenda(
	idEvento int,
	idLocalidad int,
	nombreDisciplina varchar(50),
	fecha date,
	horaInicio timetz,
	duracionMaxima int,
	precio int,
	participantes text
);
--Llave primaria
alter table agenda add constraint agenda_pkey
primary key (idEvento); 
-- Llaves foráneas
alter table agenda add constraint agenda_fkey1
foreign key (idLocalidad) references localidad(idLocalidad) on delete cascade on update cascade;

alter table agenda add constraint agenda_fkey2
foreign key (nombreDisciplina) references disciplina(nombreDisciplina) on delete cascade on update cascade;

--DISPARADOR 3 creacion/ actualizacion de evento.
--Definimos la funcion que ejecutará el trigger, esta se encarga de juntar toda la información.
create or replace function creacionEventosAgenda()
returns trigger as $$
	declare
		participantes text;
		precio int;
	begin
		-- Si no hay entradas, este será nulo
		select e.costoFinal  
		into precio
		from entrada e
		where e.idEvento = new.idEvento;
		
		-- Si no hay participantes, la entrada será nula
		select string_agg(p.numeroPasaporte, ', ')
	    into participantes
	    from participar p
		where p.idEvento = new.idEvento;
		-- Insertamos dentro de agenda
		insert into agenda(idEvento, idLocalidad, nombreDisciplina, fecha, horaInicio, duracionMaxima,precio, participantes) values (new.idEvento, new.idLocalidad, new.nombreDisciplina, new.fecha, new.horaInicio, new.duracionMaxima, precio, participantes)
		on conflict (idEvento) -- Si se llegan a tener valores distintos, este caso ocurre en los update y evita valores duplicados.
    		do update set 
        idLocalidad = excluded.idLocalidad,
        nombreDisciplina = excluded.nombreDisciplina,
        fecha = excluded.fecha,
        horaInicio = excluded.horaInicio,
        duracionMaxima = excluded.duracionMaxima,
        precio = excluded.precio,
        participantes = excluded.participantes;
	return new;
	end;
$$ language plpgsql;
-- Creamos el trigger y lo asignamos a evento
create trigger crearEventoAgenda
after insert or update on evento
for each row
execute function creacionEventosAgenda();


--DISPARADOR 4 creacion/actualizacion de entradas.
--Función que ejecuta el trigger, este actualiza el valor de costo final cuando una entrada sea insertada o eliminada. Se quedará el costo más pequeño. Pues consideraremos el precio como precio desde:...
create or replace function creacionEntradasAgenda()
returns trigger as $$
	declare
		precioNuevo int;
	begin
		--Seleccionamos el minimo, si no hay más entradas, se asignará el valor null
		select min(e.costoFinal)  
		into precioNuevo
		from entrada e
		where e.idEvento = new.idEvento;

		update agenda 
		set precio = precioNuevo
		where idEvento = new.idEvento;
	return new;
	end;
$$ language plpgsql;
-- Creamos el trigger y lo asignamos a entrada
create trigger crearEntradaAgenda
after insert or update on entrada
for each row
execute function creacionEntradasAgenda();

--DISPARADOR 5 creacion/ actualizacion de participantes.
-- Función que ejecuta el trigger al crear o actualizar la tabla de participar.
create or replace function creacionParticiparAgenda()
returns trigger as $$
	declare
		participantesNuevos text;
	begin	
		-- Agregamos los nuevos participantes a nuestra cadena.
		select string_agg(p.numeroPasaporte, ', ')
	    into participantesNuevos
	    from participar p
		where p.idEvento = new.idEvento;

		update agenda
		set participantes = participantesNuevos
		where idEvento = new.idEvento;
	return new;
	end;
$$ language plpgsql;
-- creamos el trigger y lo asignamos a participar.
create trigger crearParticipanteAgenda
after insert or update on participar
for each row
execute function creacionParticiparAgenda();

--DISPARADOR 6 Eliminar evento
-- Función que ejecuta el disparador al eliminar un evento, esta función elimina la entrada de la agenda.
create or replace function eliminarEventoAgenda()
returns trigger as $$
	begin	
		delete from agenda
		where idEvento = old.idEvento;
	return old;
	end;
$$ language plpgsql;
-- Creamos y asignamos el disparador al eliminar en evento.
create trigger eliminarEventoAgenda
after delete on evento
for each row
execute function eliminarEventoAgenda();

-- DISPARADOR 7 eliminar Entrada
-- Función que ejecuta el disparador al eliminar una entrada. La función se encarga de actualizar el costo al más bajo disponible dentro de las entradas actuales.
create or replace function eliminarEntradaAgenda()
returns trigger as $$
	declare
		precioNuevo int;
	begin	
		select min(e.costoFinal)  
		into precioNuevo
		from entrada e
		where e.idEvento = old.idEvento;

		update agenda 
		set precio = precioNuevo
		where idEvento = old.idEvento;
	return old;
	end;
$$ language plpgsql;
-- Creamos y asignamos el disparador al eliminar en entrada.
create trigger eliminarEntradaAgenda
after delete on entrada
for each row
execute function eliminarEntradaAgenda();

-- DISPARADOR 8 Eliminar participantes.
-- Función que ejecuta el disparador al eliminar un participante. La función actualiza la cadena con los nuumero de pasaporte de los participantes.
create or replace function eliminarParticiparAgenda()
returns trigger as $$
	declare
		participantesNuevos text;
	begin	
		select string_agg(p.numeroPasaporte, ', ')
	    into participantesNuevos
	    from participar p
		where p.idEvento = old.idEvento;

		update agenda
		set participantes = participantesNuevos
		where idEvento = old.idEvento;
	return old;
	end;
$$ language plpgsql;
-- Creamos y asignamos el disparador al eliminar en participar.
create trigger eliminarParticipanteAgenda
after delete on participar
for each row
execute function eliminarParticiparAgenda();


---- PRUEBAS PARA AGENDA -----

--Cada que se realice un cambio, se puede revisar la agenda con: 
	--select * from agenda

--- PRUEBAS INSERTAR:
--Insertamos un evento nuevo:
	--insert into evento (idEvento, idLocalidad, nombreDisciplina, fecha, horaInicio, duracionMaxima) values (888, 10, 'Lacrosse', '7/27/2028', '21:45', 2);
-- Para probar entrada, requerimos de una fase:
	--insert into fase (nombreFase, idEvento) values ('Fase 2', 888);
--Agregamos entradas, se quedará aquella con el precio más bajo:
	--insert into entrada (folio, nombreFase, idEvento, numeroAsiento, costoBase) values ('GP10-4dzob56x04-4444', 'Fase 2', 888, 430, 1000);
	--insert into entrada (folio, nombreFase, idEvento, numeroAsiento, costoBase) values ('GP10-4dzob56x04-4445', 'Fase 2', 888, 431, 10000);
	--insert into entrada (folio, nombreFase, idEvento, numeroAsiento, costoBase) values ('GP10-4dzob56x04-4446', 'Fase 2', 888, 432, 100);
--Agregamos participaciones:
	--insert into participar (numeroPasaporte, idEvento) values ('0ILQFH4UXK', 888);
	--insert into participar (numeroPasaporte, idEvento) values ('0AG89XNV0Q', 888);
	--insert into participar (numeroPasaporte, idEvento) values ('1SXZRS2NX9', 888);

--- PRUEBAS ACTUALIZAR:
	--update participar set numeropasaporte = '52SSHDLP9X' where numeropasaporte = '0ILQFH4UXK'
	--update entrada set costobase = 10 where folio = 'GP10-4dzob56x04-4444'
	--update evento set nombredisciplina = 'Swimming' where idevento = 888

--- PRUEBAS ELIMINAR: (Al eliminar la entrada de menor precio, se debe de actualizar el costo)
	--delete from participar where numeroPasaporte = '1SXZRS2NX9'
	--delete from entrada where folio = 'GP10-4dzob56x04-4444'
	--delete from evento where idEvento = 888
