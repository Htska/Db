-- Tablas "Entidades"
create table atleta (
	numeroPasaporte char(10),
	nombrePais varchar(50),
	fechaNacimiento date,
	nacionalidad varchar(50),
	nombre varchar(50),
	primerApellido varchar(50),
	segundoApellido varchar(50),
	genero char(1)
);

create table telefonoAtleta (
	numeroPasaporte char(10),
	telefono char(10)
);

create table correoAtleta (
	numeroPasaporte char(10),
	correo varchar(50)
);

create table evento (
	idEvento int,
	idLocalidad int,
	nombreDisciplina varchar(50),
	fecha date,
	horaInicio timetz,
	duracionMaxima int
);

create table entrada (
	folio char(20),
	nombreFase varchar(20),
	idEvento int,
	numeroEvento int,
	costoBase int
);

create table pais (
	nombrePais varchar(50)
);

create table localidad (
	idLocalidad int,
	nombrePais varchar(50),
	calle varchar(50),
	numero int,
	ciudad varchar(50),
	nombre varchar(50),
	tipo varchar(50),
	aforo int
);

create table patrocinador (
	nombrePatrocinador varchar(50)
);

create table disciplina (
	nombreDisciplina varchar(50),
	categoria char(10)
);

create table medalla (
	numeroMedalla int,
	nombreDisciplina varchar(50),
	numeroPasaporte char(10),
	tipo char(6)
);

create table juez (
	numeroPasaporte char(10),
	fechaNacimiento date,
	nacionalidad varchar(50),
	nombre varchar(50),
	primerApellido varchar(50),
	segundoApellido varchar(50)
);

create table telefonoJuez (
	numeroPasaporte char(10),
	telefono char(10)
);

create table correoJuez (
	numeroPasaporte char(10),
	correo varchar(50)
);

create table fase (
	nombreFase varchar(20),
	idEvento int
);

create table entrenador (
	numeroPasaporte char(10),
	nombreDisciplina varchar(50),
	fechaNacimiento date,
	nacionalidad varchar(50),
	nombre varchar(50),
	primerApellido varchar(50),
	segundoApellido varchar(50)
);

create table telefonoEntrenador (
	numeroPasaporte char(10),
	telefono char(10)
);

create table correoEntrenador (
	numeroPasaporte char(10),
	correo varchar(50)
);

-- Tablas "Relaciones"

create table practicar(
	numeroPasaporte char(10),
	nombreDisciplina varchar(50)
);

create table participar(
	numeroPasaporte char(10),
	idEvento int
);

create table patrocinar(
	nombrePatrocinador varchar(50),
	nombreDisciplina varchar(50)
);

create table supervisar (
	numeroPasaporte char(10),
	nombreDisciplina varchar(50)
);

create table entrenar(
	numeroPasaporteE char(10),
	numeroPasaporteA char(10)
);

-- Restriciones 

-- Atleta
alter table atleta add constraint atleta_d1
check (char_length(numeroPasaporte)=10);

alter table atleta add constraint atleta_d2
check (nombrePais <> '');

alter table atleta alter column fechaNacimiento
set not null;

alter table atleta add constraint atleta_d3
check (nacionalidad <> '');

alter table atleta add constraint atleta_d4
check (nombre <> '');

alter table atleta add constraint atleta_d5
check (primerApellido <> '');

alter table atleta add constraint atleta_d6
check (genero = 'M' or genero = 'F');

-- TeléfonoAtleta
alter table telefonoAtleta add constraint telefonoAtleta_d1
check (telefono similar to '[0-9]{10}');

-- CorreoAtleta
alter table correoAtleta add constraint correoAtleta_d1
check (correo like '%_@_%._%');

-- Evento
alter table evento alter column idEvento
set not null;

alter table evento alter column idLocalidad
set not null;

alter table evento alter column fecha
set not null;

alter table evento alter column horaInicio
set not null;

alter table evento add constraint evento_d1
check(nombreDisciplina is not null and nombreDisciplina <> '');

alter table evento add constraint evento_d2
check(duracionMaxima is not null and duracionMaxima between 0 and 24);

-- Entrada
alter table entrada alter column nombreFase
set not null;

alter table entrada alter column idEvento
set not null;

alter table entrada alter column numeroEvento
set not null;

alter table entrada add constraint entrada_d1
check(folio is not null and char_length(folio)=20);

alter table entrada add constraint entrada_d2
check(nombreFase is not null and nombreFase <> '');

alter table entrada add constraint entrada_d3
check(costoBase is not null and costoBase >= 0);

-- País
alter table pais add constraint pais_d1
check(nombrePais is not null and nombrePais <> '');

-- Participar
alter table participar alter column numeroPasaporte
set not null;

alter table participar alter column idEvento
set not null;

--Localidad
alter table localidad alter column idLocalidad
set not null;

alter table localidad add constraint localidad_d1
check(nombrePais is not null and nombrePais <> '');

alter table localidad add constraint localidad_d2
check(calle is not null and calle <> '');

alter table localidad alter column numero
set not null;

alter table localidad add constraint localidad_d3
check(ciudad is not null and ciudad <> '');

alter table localidad add constraint localidad_d4
CHECK(nombre is not null and nombre <> '');

alter table localidad add constraint localidad_d5
check(tipo is not null and tipo <> '');

alter table localidad add constraint localidad_d6
check(aforo is not null and aforo > 0);

-- Patrocinador
alter table patrocinador add constraint patrocinador_d1
check(nombrePatrocinador is not null and nombrePatrocinador <> '');

-- Disciplina
alter table disciplina add constraint disciplina_d1
check (nombreDisciplina is not null and nombreDisciplina <> '');

alter table disciplina add constraint disciplina_d2
check(categoria = 'Individual' or categoria = 'Equipo');

-- Medalla
alter table medalla add constraint medalla_d1
check(numeroMedalla is not null and numeroMedalla > 0);

alter table medalla add constraint medalla_d2
check(nombreDisciplina is not null  and nombreDisciplina <> '');

alter table medalla alter column numeroPasaporte 
set not null;

alter table medalla add constraint medalla_d3
check(tipo = 'Oro' or tipo = 'Plata' or tipo='Bronce');

-- Patrocinar
alter table patrocinar add constraint patrocinar_d1
check(nombrePatrocinador is not null and nombrePatrocinador <> '');

alter table patrocinar add constraint patrocinar_d2
check (nombreDisciplina is not null and nombreDisciplina <> '');

-- Juez
alter table juez add constraint juez_d1
check (char_length(numeroPasaporte)=10);

alter table juez alter column fechaNacimiento
set not null;

alter table juez add constraint juez_d2
check (nacionalidad <> '');

alter table juez add constraint juez_d3
check (nombre <> '');

alter table juez add constraint juez_d4
check (primerApellido <> '');

alter table juez add constraint juez_d5
check (segundoApellido <> '');

-- TeléfonoJuez
alter table telefonoJuez add constraint telefonoJuez_d1
check (telefono similar to '[0-9]{10}');

-- CorreoJuez
alter table correoJuez add constraint correoJuez_d1
check (correo like '%_@_%._%');

-- Fase
alter table fase add constraint fase_d1
check (nombreFase <> '');

-- Entrenador
alter table entrenador add constraint entrenador_d1
check (char_length(numeroPasaporte)=10);

alter table entrenador add constraint entrenador_d2
check(nombreDisciplina is not null and nombreDisciplina <> '');

alter table entrenador alter column fechaNacimiento
set not null;

alter table entrenador add constraint entrenador_d3
check (nacionalidad <> '');

alter table entrenador add constraint entrenador_d4
check (nombre <> '');

alter table entrenador add constraint entrenador_d5
check (primerApellido <> '');

-- TeléfonoEntrenador
alter table telefonoEntrenador add constraint telefonoEntrenador_d1
check (telefono similar to '[0-9]{10}');

-- CorreoEntrenador
alter table correoEntrenador add constraint correoEntrenador_d1
check (correo like '%_@_%._%');

-- Entrenar
alter table entrenar alter column numeroPasaporteE
set not null;

alter table entrenar alter column numeroPasaporteA
set not null;

-- Llaves Primarias

alter table atleta add constraint atleta_pkey
primary key(numeroPasaporte);

alter table telefonoAtleta add constraint telefonoAtleta_pkey
primary key(numeroPasaporte, telefono);

alter table correoAtleta add constraint correoAtleta_pkey
primary key(numeroPasaporte, correo);

alter table evento add constraint evento_pkey
primary key(idEvento);

alter table entrada add constraint entrada_pkey
primary key(folio, nombreFase, idEvento); -- Primary Key Compuesta

alter table pais add constraint pais_pkey
primary key(nombrePais);

alter table localidad add constraint localidad_pkey
primary key(idLocalidad);

alter table patrocinador add constraint patrocinador_pkey
primary key(nombrePatrocinador);

alter table disciplina add constraint disciplina_pkey
primary key (nombreDisciplina);

alter table medalla add constraint medalla_pkey
primary key (numeroMedalla, nombreDisciplina); -- Primary Key Compuesta

alter table juez add constraint juez_pkey
primary key(numeroPasaporte);

alter table telefonoJuez add constraint telefonoJuez_pkey
primary key(numeroPasaporte, telefono);

alter table correoJuez add constraint correoJuez_pkey
primary key(numeroPasaporte, correo);

alter table fase add constraint fase_pkey
primary key(nombreFase, idEvento);

alter table entrenador add constraint entrenador_pkey
primary key(numeroPasaporte);

alter table telefonoEntrenador add constraint telefonoEntrenador_pkey
primary key(numeroPasaporte, telefono);

alter table correoEntrenador add constraint correoEntrenador_pkey
primary key(numeroPasaporte, correo);

-- Llaves Foráneas 

alter table atleta add constraint atleta_fkey
foreign key(nombrePais) references pais(nombrePais);

alter table telefonoAtleta add constraint telefonoAtleta_fkey
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table correoAtleta add constraint correoAtleta_fkey
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table evento add constraint evento_fkey1
foreign key(idLocalidad) references localidad(idLocalidad);

alter table evento add constraint evento_fkey2
foreign key(nombreDisciplina) references disciplina(nombreDisciplina);

alter table entrada add constraint entrada_fkey1
foreign key(nombreFase, idEvento) references fase(nombreFase, idEvento); -- Foreign Key compuesta

alter table localidad add constraint localidad_fkey
foreign key (nombrePais) references pais(nombrePais);

alter table medalla add constraint medalla_fkey1
foreign key (nombreDisciplina) references disciplina(nombreDisciplina);

alter table medalla add constraint medalla_fkey2
foreign key (numeroPasaporte) references atleta(numeroPasaporte);

alter table telefonoJuez add constraint telefonoJuez_fkey
foreign key(numeroPasaporte) references juez(numeroPasaporte);

alter table correoJuez add constraint correoJuez_fkey
foreign key(numeroPasaporte) references juez(numeroPasaporte);

alter table fase add constraint fase_fkey
foreign key(idEvento) references evento(idEvento);

-- 

alter table practicar add constraint practicar_fkey1
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table practicar add constraint practicar_fkey2
foreign key(nombreDisciplina) references disciplina(nombreDisciplina);

alter table participar add constraint participar_fkey1
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table participar add constraint participar_fkey2
foreign key(idEvento) references evento(idEvento);

alter table patrocinar add constraint patrocinar_fkey1
foreign key (nombrePatrocinador) references patrocinador(nombrePatrocinador);

alter table patrocinar add constraint patrocinar_fkey2
foreign key (nombreDisciplina) references disciplina(nombreDisciplina);

alter table supervisar add constraint supervisar_fkey1
foreign key(numeroPasaporte) references juez(numeroPasaporte);

alter table supervisar add constraint supervisar_fkey2
foreign key(nombreDisciplina) references disciplina(nombreDisciplina);

alter table entrenador add constraint entrenador_fkey
foreign key (nombreDisciplina) references disciplina(nombreDisciplina);

alter table telefonoEntrenador add constraint telefonoEntrenador_fkey
foreign key(numeroPasaporte) references entrenador(numeroPasaporte);

alter table correoEntrenador add constraint correoEntrenador_fkey
foreign key(numeroPasaporte) references entrenador(numeroPasaporte);

alter table entrenar add constraint entrenar_fkey1
foreign key(numeroPasaporteE) references entrenador(numeroPasaporte);

alter table entrenar add constraint entrenar_fkey2
foreign key(numeroPasaporteA) references atleta(numeroPasaporte);


-- Comentarios 

-- atleta
comment on table atleta is 'Tabla que contiene a los atletas que participan en los JJOO';
comment on column atleta.numeroPasaporte is 'Número del pasaporte del atleta, es su identificador';
comment on column atleta.nombrePais is 'Nombre del país que representa el atleta';
comment on column atleta.fechaNacimiento is 'Fecha de nacimiento del atleta';
comment on column atleta.nacionalidad is 'Nacionalidad del atleta';
comment on column atleta.nombre is 'Nombre del atleta';
comment on column atleta.primerApellido is 'Primer apellido del atleta';
comment on column atleta.segundoApellido is 'Segundo apellido del atleta, si tiene';
comment on column atleta.genero is 'Genero del atleta (M|F)';
comment on constraint atleta_pkey on atleta is 'La llave primaria de la tabla atleta';
comment on constraint atleta_fkey on atleta is 'La llave foránea de la tabla atleta, hace referencia a la tabla pais';
comment on constraint atleta_d1 on atleta is 'Restricción check que asegura que la longitud del número de pasaporte sea 10';
comment on constraint atleta_d2 on atleta is 'Restricción check que asegura que el nombre del Pais del atleta no sea vacío';
comment on constraint atleta_d3 on atleta is 'Restricción check que asegura que la nacionalidad no es vacía';
comment on constraint atleta_d4 on atleta is 'Restricción check que asegura que el nombre del atleta no sea vacío';
comment on constraint atleta_d5 on atleta is 'Restricción check que asegura que el primer apellido del atleta no sea vacío';
comment on constraint atleta_d6 on atleta is 'Restricción check que asegura que el género sea F o M';

-- teléfonoAtleta
comment on table telefonoAtleta is 'Tabla que contiene los telefonos de los atletas';
comment on column telefonoAtleta.numeroPasaporte is 'Número del pasaporte del atleta que tiene el télefono asociado';
comment on column telefonoAtleta.telefono is 'Número de télefono del atleta';
comment on constraint telefonoAtleta_pkey on telefonoAtleta is 'La llave primaria de la tabla telefonoAtleta';
comment on constraint telefonoAtleta_fkey on telefonoAtleta is 'La llave foránea de la tabla telefonoAtleta, hace refencia a la tabla atleta';
comment on constraint telefonoAtleta_d1 on telefonoAtleta is 'Restricción check que asegura que un telefono tiene longitud 10 y contiene únicamente números';

-- correoAtleta
comment on table correoAtleta is 'Tabla que contiene los correos de los atletas';
comment on column correoAtleta.numeroPasaporte is 'Número del pasaporte del atleta que tiene el correo asociado';
comment on column correoAtleta.correo is 'Correo del atleta';
comment on constraint correoAtleta_pkey on correoAtleta is 'La llave primaria de la tabla correoAtleta';
comment on constraint correoAtleta_fkey on correoAtleta is 'La llave foránea de la tabla atleta, hace referencia a la tabla atleta';
comment on constraint correoAtleta_d1 on correoAtleta is 'Restricción check que asegura que un correo tenga una estructura correcta';

-- practicar
comment on table practicar is 'Tabla que contiene la relación de practicar entre un atleta y una disciplina';
comment on column practicar.numeroPasaporte is 'Número de pasaporte del atleta que practica la disciplina';
comment on column practicar.nombreDisciplina is 'Nombre de la disciplina que practica el atleta';
comment on constraint practicar_fkey1 on practicar is 'Llave foránea de la tabla practicar, hace refencia al numero de Pasaporte del atleta';
comment on constraint practicar_fkey2 on practicar is 'Llave foránea de la tabla practicar, hace referencia al nombreDisciplina de la tabla disciplina';

-- localidad
comment on table localidad is 'Tabla que contiene las localidades disponibles para los eventos';
comment on column localidad.idLocalidad is 'Identificador de la localidad';
comment on constraint localidad_d1 on localidad is 'Restricción check que asegura que el nombre del país de localidad no sea una cadena vacía. ';
comment on constraint localidad_d2 on localidad is 'Restricción check que asegura que la calle no sea una cadena vacía.';
comment on column localidad.numero is 'Numero de la ubicación de la localidad';
comment on constraint localidad_d3 on localidad is 'Restricción check que asegura que la ciudad de localidad no sea una cadena vacía. ';
comment on constraint localidad_d4 on localidad is 'Restricción check que asegura que el nombre de la localidad no sea una cadena vacía.'; 
comment on constraint localidad_d5 on localidad is 'Restricción check que asegura que la tipo de localidad no sea una cadena vacía. ';
comment on constraint localidad_d6 on localidad is 'Restricción check que asegura que el aforo de la localidad sea mayor a cero.'; 
comment on constraint localidad_pkey on localidad is 'Llave primaria para localidad';
comment on constraint localidad_fkey on localidad is 'Llave foránea para localidad que hace referencia a pais.';

-- patrocinador
comment on table patrocinador is 'Tabla que contiene los patrocinadores de las disciplinas';
comment on constraint patrocinador_d1 on patrocinador is 'Restricción check que asegura que el nombre del patrocinador no sea una cadena vacía. ';
comment on constraint patrocinador_pkey on patrocinador is 'Llave primaria para patrocinador';

-- patrocinar
comment on table patrocinar is 'Tabla que representa la relación patrocinar entre patrocinador y disciplina';
comment on constraint patrocinar_d1 on patrocinar is 'Restricción check que asegura que el nombre del patrocinador no sea una cadena vacía. ';
comment on constraint patrocinar_d2 on patrocinar is 'Restricción check que asegura que el nombre de la disciplina no sea una cadena vacía. ';
comment on constraint patrocinar_fkey1 on patrocinar is 'Llave foránea para patrocinar que hace referencia a patrocinador';
comment on constraint patrocinar_fkey2 on patrocinar is 'Llave foránea para patrocinar que hace referencia a disciplina';

-- disciplina
comment on table disciplina is 'Tabla que contiene los disciplinas de los juegos olímpicos';
comment on constraint disciplina_d1 on disciplina is 'Restricción check que asegura que el nombre de la disciplina no sea una cadena vacía. ';
comment on constraint disciplina_d2 on disciplina is 'Restricción check que asegura que la categoría de la disciplina sea o Individual o Equipo ';
comment on constraint disciplina_pkey on disciplina is 'Llave primaria para disciplina';

-- medalla
comment on table medalla is 'Tabla que contiene las medallas otorgadas durante los juegos olímpicos';
comment on constraint medalla_d1 on medalla is 'Restricción check que asegura que el número de la medalla sea mayor a cero ';
comment on constraint medalla_d2 on medalla is 'Restricción check que asegura que el nombre de la disciplina no sea una cadena vacía ';
comment on column medalla.numeroPasaporte is 'Número de pasaporte del atleta';
comment on constraint medalla_d3 on medalla is 'Restricción check que asegura que el tipo de la medalla solo pueda ser Oro, Plata o Bronce ';
comment on constraint medalla_pkey on medalla is 'Llave primaria para medalla';
comment on constraint medalla_fkey1 on medalla is 'Llave foránea para medalla que hace referencia a disciplina';
comment on constraint medalla_fkey2 on medalla is 'Llave foránea para medalla que hace referencia a atleta';
