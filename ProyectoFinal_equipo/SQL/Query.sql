-- CONSULTA 1

-- La información de todos los atletas que hayan ganado alguna medalla. Asi como un conteo de las medallas 
-- de oro, plata y bronce que ganaron. La información debera ser ordenada con respecto a las medallas, es
-- decir primero oro, despues plata y al final bronce.

select numeropasaporte,nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero,
       sum(case when tipo = 'Oro' then 1 else 0 end) as oro,
       sum(case when tipo = 'Plata' then 1 else 0 end) as plata,
       sum(case when tipo = 'Bronce' then 1 else 0 end) as bronce
from (medalla natural join atleta)
group by numeropasaporte, nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero
order by oro desc, plata desc, bronce desc ;


-- COSULTA 2

-- Información de los entrenadores que entrenan a atletas que practican más de 1 disciplina

select e2.numeropasaporte ,e2.nombre,e2.primerapellido,e2.segundoapellido, e2.nacionalidad, e2.nombredisciplina 
from (select numeropasaporte,count(nombredisciplina) as disciplinas
	from
	(select distinct numeropasaporte,nombredisciplina
	from atleta natural join participar natural join evento)
	group by numeropasaporte) as a join entrenar e on a.numeropasaporte = e.numeropasaportea 
	join entrenador e2 on e.numeropasaportee = e2.numeropasaporte 
where disciplinas>1;


-- CONSULTA 3

-- La información de los jueces según las disiciplinas que supervisa, tales que vendieron más entradas:

select *
from (
select nombreDisciplina, count(folio) as boletos
from disciplina d natural join evento e natural join fase natural join entrada e2 
group by nombredisciplina) natural join supervisar s  natural join juez j
order by boletos desc;
