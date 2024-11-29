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


-- CONSULTA 4

-- Información de las localidades que más han sido utilizadas en fase 4 ordenadas por uso y aforo

select *
from (
    select idLocalidad, count(*) as numeroUsos
    from evento 
    natural join (
        select idLocalidad, idEvento
        from fase
        natural join evento
        where nombreFase = 'Fase 4')
    group by idLocalidad)
natural join localidad 
order by numeroUsos desc, aforo desc;

--CONSULTA 5

-- Disciplinas con mas patrocinadores que tienen el mayor número de espectadores presenciales.

-- Creamos 2 Tablas temporales que guarden el resultado de obtener el numero de patrocinadores que tiene una disciplina y otra que guarde el numero de espectadores que tiene una disciplina

--tabla con el numero de patrocinadores
with numeroPatrocinadores as (
    select nombreDisciplina, count(nombrePatrocinador) as numeroPatrocinadores
    from patrocinar
    natural join disciplina
    group by nombreDisciplina
),
--tabla con el numero de espectadores
numeroEspectadores as (
    select nombreDisciplina, count(folio) as numeroEspectadores
    from entrada
    natural join evento
    group by nombreDisciplina
)
-- Query
select d.nombreDisciplina, 
       p.numeroPatrocinadores, 
       e.numeroEspectadores
from disciplina d
--Realizamos inner join para incluir únicamente a las disciplinas con patrocinadores y espectadores.
inner join numeroPatrocinadores p on d.nombreDisciplina = p.nombreDisciplina
inner join numeroEspectadores e on d.nombreDisciplina = e.nombreDisciplina
ORDER BY numeroPatrocinadores DESC, numeroEspectadores DESC;

--CONSULTA 6

-- Eventos que se realizan fuera de Los Angeles pero dentro de Estados Unidos
select idEvento, nombreDisciplina, nombre, ciudad, fecha
from localidad natural join evento
where nombrePais = 'United States' and ciudad <> 'Los Angeles'

--CONSULTA 7

-- La información de los eventos que se llevan a cabo en la localidad con mayor cantidad de eventos, ordenados de acuerdo a su fecha de inicio

select idEvento, evento.idLocalidad, nombreDisciplina, fecha, horaInicio from  (
  select max(num_eventos)
  from (select idLocalidad, count(idEvento) as num_eventos
        from evento
            group by idLocalidad)) as m join (select idLocalidad, count(idEvento) as num_eventos
        from evento
            group by idLocalidad) as a on m.max = a.num_eventos join evento on a.idLocalidad = evento.idlocalidad 
order by fecha asc;

--CONSULTA 8

-- Países de acuerdo al número de atletas que los representan, ordenados de mayor a menor

select pais.nombrePais, count(numeroPasaporte) as atletas_representados 
from atleta right join pais on atleta.nombrepais = pais.nombrepais 
group by pais.nombrePais
order by atletas_representados desc;

-- CONSULTA 9
-- Cada disciplina practicada y el género predominante en ella, ordenados por el nombre de la disciplina

-- Se crean dos tablas temporales, donde la primera guarda cada disciplina, el género y el total de atletas de ese género
-- y la segunda guarda el máximo entre estos dos totales por género

with numeroGeneros as (
select nombreDisciplina, genero, count(genero) as numeroGen
from practicar natural join atleta 
group by nombreDisciplina, genero),

predominante as (
    select nombreDisciplina, max(numeroGen) as maxG
    from numeroGeneros
    group by nombreDisciplina
    order by nombreDisciplina
)
select predominante.nombreDisciplina, genero as genero_predominante
from predominante join numeroGeneros on numeroGen = maxG and predominante.nombreDisciplina = numeroGeneros.nombreDisciplina
order by predominante.nombreDisciplina;

-- CONSULTA 10
-- El atleta más jóven de cada disciplina, ordenados por el nombre de la disciplina

-- Se crea una tabla temporal, donde se guarda la fecha de nacimiento mayor de cada disciplina

with fechaMenor as (
	select nombreDisciplina, max(fechaNacimiento) as fechaNacimiento
	from practicar natural join atleta
	group by nombreDisciplina
)
-- Query
select nombreDisciplina, nombre, primerApellido, segundoApellido, fechaNacimiento 
from atleta natural join fechaMenor
order by nombreDisciplina ;


-- CONSULTA 11
-- Los jueces que supervisan la fase 4 de cada disciplina, ordenados por el nombre de la disciplina.

select nombreDisciplina, nombre, primerApellido, segundoApellido 
from juez natural join supervisar natural join disciplina natural join evento natural join fase
where nombreFase = 'Fase 4'
group by nombreDisciplina, nombre, primerApellido, segundoApellido
order by nombreDisciplina ;

-- CONSULTA 12
-- El evento más largo de cada disciplina, ordenados por el nombre de la disciplina.

-- Se crean una tabla temporal, donde se guarda la duración máxima de cada disciplina.

with eventoMaximo as (
	select nombreDisciplina, max(duracionMaxima) as duracionMaxima
	from evento
	group by nombreDisciplina
)
	
select nombreFase, nombreDisciplina, duracionMaxima, fecha
from fase natural join evento natural join eventoMaximo
order by nombreDisciplina ;