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

create table telefonoAtleta(
	numeroPasaporte char(10),
	telefono char(10)
);

create table correoAtleta(
	numeroPasaporte char(10),
	correo varchar(50)
);

create table evento(
	idEvento int,
	idLocalidad int,
	nombreDisciplina varchar(50),
	fecha date,
	horaInicio timetz,
	duracionMaxima int
);

create table entrada(
	folio char(20),
	nombreFase varchar(20),
	idEvento int,
	numeroEvento int,
	costoBase int
);

create table pais(
	nombrePais varchar(50)
);

create table localidad(
	idLocalidad int,
	nombrePais varchar(50),
	calle varchar(50),
	numero int,
	ciudad varchar(50),
	nombre varchar(50),
	tipo varchar(50),
	aforo int
);

create table patrocinador(
	nombrePatrocinador varchar(50)
);

create table disciplina(
	nombreDisciplina varchar(50),
	categoria char(10)
);

create table medalla(
	numeroMedalla int,
	nombreDisciplina varchar(50),
	numeroPasaporte int,
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
primary key(folio);

alter table pais add constraint pais_pkey
primary key(nombrePais);

alter table localidad add constraint localidad_pkey
primary key(idLocalidad);

alter table patrocinador add constraint patrocinador_pkey
primary key(nombrePatrocinador);

alter table disciplina add constraint disciplina_pkey
primary key (nombreDisciplina);

alter table medalla add constraint medalla_pkey
primary key (numeroMedalla);

alter table juez add constraint juez_pkey
primary key(numeroPasaporte);

alter table telefonoJuez add constraint telefonoJuez_pkey
primary key(numeroPasaporte, telefono);

alter table correoJuez add constraint correoJuez_pkey
primary key(numeroPasaporte, correo);

alter table fase add constraint fase_pkey
primary key(nombreFase, idEvento);

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
foreign key(nombreFase) references fase(nombreFase);

alter table entrada add constraint entrada_fkey2
foreign key(idEvento) references evento(idEvento);

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

