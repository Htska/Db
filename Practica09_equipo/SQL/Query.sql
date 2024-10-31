select nombrepatrocinador
from patrocinar
group by nombrepatrocinador
having count(nombredisciplina) = 1

select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Plata' and nombrepais='Japan'
group by nombrepais

select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Oro' and nombrepais='Mexico'
group by nombrepais

select nombrepais, count(numeromedalla) as medallasganadas
from (medalla natural join atleta)
where medalla.tipo = 'Bronce' and nombrepais='Spain'
group by nombrepais

select numeropasaporte, nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero
from (medalla natural join atleta)
where nombredisciplina = 'Weightlifting'

select numeropasaporte,nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero,
       sum(case when tipo = 'Oro' then 1 else 0 end) as oro,
       sum(case when tipo = 'Plata' then 1 else 0 end) as plata,
       sum(case when tipo = 'Bronce' then 1 else 0 end) as bronce
from (medalla natural join atleta)
group by numeropasaporte, nombrepais, fechanacimiento, nacionalidad, nombre, primerapellido, segundoapellido, genero
order by oro desc, plata desc, bronce desc ;
