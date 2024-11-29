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
-- Caso en donde se desee obtener la capacidad de una ocalidad inválida
--select * from capacidadRestante(200);




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