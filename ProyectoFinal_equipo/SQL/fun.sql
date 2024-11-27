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

