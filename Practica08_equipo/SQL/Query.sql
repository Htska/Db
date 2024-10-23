select *
from atleta
where nombre like 'R%';

select *
from entrenador
where date_part('month',fechanacimiento) = 6; 

select * 
from evento
where fecha between '2024-01-01' and '2024-04-14';

select *
from localidad
where aforo > 400;

select *
from patrocinador;
