--1
(select a.primerapellido, a.numeropasaporte as pasaporteAtleta, e.numeropasaporte as pasaporteEntrenador,
a.nombredisciplina as disciplina, a.nombre as nombreAtleta, e.nombre as nombreEntrenador, a.nacionalidad as nacionalidadAtleta
, e.nacionalidad as nacionalidaEntrenador
from (atleta natural join practicar) as a join entrenador e 
on (a.primerapellido = e.primerapellido and a.nombredisciplina = e.nombredisciplina)
order by a.primerapellido);


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
	from atleta natural join participar natural join evento)
	group by numeropasaporte) natural join atleta
where disciplinas>1;

--4 

(select j.numeropasaporte, j.nacionalidad,'Juez' AS puesto,j.nombredisciplina,  j.nombre, j.primerapellido, j.segundoapellido
from (juez natural join supervisar) j)
union
(select e.numeropasaporte, e.nacionalidad,'Entrenador' AS puesto,e.nombredisciplina, e.nombre, e.primerapellido, e.segundoapellido 
from entrenador e 
LEFT JOIN (juez natural join supervisar) js 
ON e.nacionalidad = js.nacionalidad 
   AND e.nombredisciplina = js.nombredisciplina
WHERE js.numeropasaporte IS NULL)
order by nacionalidad


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
