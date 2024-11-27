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


--

