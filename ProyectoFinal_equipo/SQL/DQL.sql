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


-- CONSULTA 2

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
where nombrePais = 'United States' and ciudad <> 'Los Angeles';

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

-- Se crea una tabla temporal, donde se guarda la fecha de nacimiento mayor de un atleta en cada disciplina

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
-- La fecha y lugar de la fase 4 de cada disciplina, ordenados por el nombre de la disciplina y cronología.

select nombreDisciplina as disciplina, nombre as localidad, fecha, horaInicio as hora
from localidad natural join evento natural join fase
where nombreFase = 'Fase 4'
order by nombreDisciplina, fecha, hora;

-- CONSULTA 12
-- El evento más largo de cada disciplina, ordenados por la duración máxima, el nombre
-- de la disciplina y por cronología.

-- Se crean una tabla temporal, donde se guarda la duración máxima de cada disciplina.

with eventoMaximo as (
	select nombreDisciplina, max(duracionMaxima) as duracionMaxima
	from evento
	group by nombreDisciplina
)
-- Query
select nombreFase, idEvento, nombreDisciplina, duracionMaxima, fecha
from fase natural join evento natural join eventoMaximo
order by duracionMaxima desc, nombreDisciplina, fase , fecha;

-- CONSULTA 13

-- La lista de correos de atletas que representan a México y de los entrenadores que los entrenan.

-- Tabla temporal que relaciona los atletas que representan a México con sus respectivos correos.
with t1 as (
	select numeroPasaporte, correo
	from atleta natural join correoAtleta
	where nombrePais = 'Mexico'
)
-- Tabla temporal que cambia el nombre de la columna numeroPasaporte de la tabla 1.
, t2 as (select numeroPasaporte as numeroPasaporteA from t1)
-- Tabla temporal que hace un join natural para relacionar los atletas que representan a México con sus respectivos entrenadores.
, t3 as (select * from t2 natural join entrenar)
-- Tabla temporal que cambia el nombre de la columna numeroPasaporteE de la tabla 3.
, t4 as (select numeroPasaporteE as numeroPasaporte from t3)
-- Tabla temporal que hace un join natural para relacionar los atletas que representan a México con los atributos de sus respectivos entrenadores.
, t5 as (select * from t4 natural join entrenador)
-- Tabla temporal que relaciona a los entrenadores de los atletas que reprensentan a México con sus respectivos correos.
, t6 as (select correo from correoentrenador natural join t5)
-- La unión de los correos de atletas que representan a México junto a los correos de sus entrenadores.
select correo from t1 union select correo from t6;

-- CONSULTA 14

-- Atletas y Entrenadores menores o iguales a 30 años que practican o entrenan Basketball o Football.
select nombre, primerApellido, segundoApellido, nombrePais, fechaNacimiento, nombreDisciplina
from atleta natural join practicar 
where fechanacimiento > '1998-01-01' and (nombreDisciplina = 'Basketball' or nombreDisciplina = 'Football')
union 
select nombre, primerApellido, segundoApellido, nacionalidad, fechaNacimiento, nombreDisciplina
from entrenador 
where fechanacimiento > '1998-01-01' and (nombreDisciplina = 'Basketball' or nombreDisciplina = 'Football') 
order by nombreDisciplina, fechaNacimiento;

-- CONSULTA 15 

-- El nombre completo y el teléfono de los Atletas, Entrenadores y Jueces que participaran, entrenaran o supervisaran en la localidad con id 1 "Los Angeles Memorial Coliseum"
select nombre, primerApellido, segundoApellido, telefono
from atleta natural join telefonoatleta natural join participar natural join evento 
where idLocalidad = '1'
intersect 
select nombre, primerApellido, segundoApellido, telefono
from atleta natural join telefonoAtleta
union
select nombre, primerApellido, segundoApellido, telefono
from entrenador natural join telefonoEntrenador natural join disciplina natural join evento 
where idLocalidad = '1'
intersect 
select nombre, primerApellido, segundoApellido, telefono
from entrenador natural join telefonoEntrenador
union
select nombre, primerApellido, segundoApellido, telefono
from juez natural join telefonoJuez natural join supervisar natural join disciplina natural join evento 
where idLocalidad = '1'
intersect 
select nombre, primerApellido, segundoApellido, telefono
from juez natural join telefonoJuez;

