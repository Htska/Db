--1
(select a.numeropasaporte, a.fechanacimiento , a.nacionalidad, a.nombre, a.primerapellido, 
a.segundoapellido
from (atleta natural join practicar) as a join entrenador e 
on (a.primerapellido = e.primerapellido and a.nombredisciplina = e.nombredisciplina)
order by a.primerapellido)

union

(select e.numeropasaporte, e.fechanacimiento , e.nacionalidad, e.nombre, e.primerapellido, 
e.segundoapellido
from (atleta natural join practicar) as a join entrenador e 
on (a.primerapellido = e.primerapellido and a.nombredisciplina = e.nombredisciplina)
order by e.primerapellido);


--2
select nombredisciplina,idevento, idlocalidad,fecha,horainicio,duracionmaxima,costobase 
from evento natural join entrada
where costobase > 2500
order by costobase ;

--3
select *
from  (select numeropasaporte,count(nombredisciplina) as disciplinas
	from
	(select distinct numeropasaporte,nombredisciplina
	from atleta natural join practicar natural join evento)
	group by numeropasaporte) natural join atleta
where disciplinas>1;

--4 

-- Tabla temporal para jueces con información de la disciplina y evento.
create temp table juezDisciplina as 
select j.numeropasaporte as pasaporteJuez, j.nacionalidad, e.idevento, s.nombredisciplina as disciplinaJuez 
from juez j
join supervisar s on j.numeropasaporte = s.numeropasaporte
join evento e on e.nombredisciplina = s.nombredisciplina

--Tabla temporal para entrenadores con información de evento.
create temp table entrenadorDisciplina as 
select e.numeropasaporte as entrenadorPasaporte, e.nacionalidad, ev.idevento, e.nombredisciplina as disciplinaEntrenador
from entrenador e 
join evento ev on ev.nombredisciplina = e.nombredisciplina

select distinct jd.pasaporteJuez, jd.idevento, ed.entrenadorPasaporte, ed.idevento
from juezDisciplina jd
join entrenadorDisciplina ed ON jd.nacionalidad = ed.nacionalidad
where jd.idevento <> ed.idevento;


--5
select nombrepatrocinador
from patrocinar
group by nombrepatrocinador
having count(distinct nombredisciplina) = 1;


--6
select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Oro' and nombrepais='Mexico'
group by nombrepais;

--7
select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Plata' and nombrepais='Japan'
group by nombrepais;

--8
select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Bronce' and nombrepais='Spain'
group by nombrepais;

--9;
select numeropasaporte, nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero
from (medalla natural join atleta)
where nombredisciplina = 'Weightlifting'

--10;
select numeropasaporte,nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero,
       sum(case when tipo = 'Oro' then 1 else 0 end) as oro,
       sum(case when tipo = 'Plata' then 1 else 0 end) as plata,
       sum(case when tipo = 'Bronce' then 1 else 0 end) as bronce
from (medalla natural join atleta)
group by numeropasaporte, nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero
order by oro desc, plata desc, bronce desc ;
