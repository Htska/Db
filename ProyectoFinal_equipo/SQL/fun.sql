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

-- Uso de la función
select * from medallero() as (Pais varchar, Oro bigint, Plata bigint, Bronce bigint);


-- FUNCION 2

-- función que regresa la capacidad restante de una localidad si se especifica su id, en otro caso regresa la capacidad restante de todas las localidades.
create or replace function capacidadRestante(idLocalidadS integer default null) 
returns table (idLocalidad int, nombre varchar(50), lugaresDisponibles int) as $$
begin
	-- Revisamos que el idLocalidadS  (si es proporcionado) sea válido 
	if idLocalidadS is not null then
		if not exists (select * from localidad l where l.idLocalidad = idLocalidadS) then
			raise exception 'idLocalidad inválido, la localidad con id % no existe', idLocalidadS;
		end if;
	end if;
	-- Regresamos el query
	return query
	select l.idLocalidad, l.nombre, 
		l.aforo-(select count(folio):: integer 
		from entrada natural join evento e
		where e.idLocalidad = l.idLocalidad) as lugaresDisponibles
	from localidad l
	where idLocalidadS is null or l.idLocalidad = idLocalidadS;
end;									
$$ 
language plpgsql

-- Usos de la función

--Obtener todas
select * from capacidadRestante();
-- Obtener una localidad en especifico
select * from capacidadRestante(1);
-- Localidad inválida
select * from capacidadRestante(200);





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
		
		select lugaresDisponibles into capacidadRestante
		from capacidadRestante(targetLocalidad);
		-- Revisamos si aún hay capacidad suficiente.
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

-- Se creó una localidad con aforo de 4 y sus resoectivas entidades necesarias para poder insertar entradas a ella. Dicha localidad se encuentra con capacidad máxima. Podemos probar el uso del trigger con:
-- Revisamos las entradas actuales, son 4.
select * from entrada 
where idevento = 444
-- Inserción cuando se excede la capacidad, regresará la excepción.
insert into entrada (folio, nombreFase, idEvento, numeroAsiento, costoBase) values ('ZQ87-ifjd5glhjr-0005', 'Fase 2', 444, 287, 100);