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

create table practicar(
numeroPasaporte char(10),
nombreDisciplina varchar(50)
);

create table evento(
	idEvento int,
	idLocalidad int,
	nombreDisciplina varchar(50),
	fecha date,
	horaInicio time,
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

create table participar(
	numeroPasaporte int,
	idEvento int
);

alter table atleta add constraint atleta_d1
check (char_length(numeroPasaporte)=10);

alter table atleta add constraint atleta_pkey
primary key(numeroPasaporte);

alter table atleta add constraint atleta_d2
check (nombrePais <> '');

alter table atleta add constraint atleta_fkey
foreign key(nombrePais) references pais(nombrePais);

alter table atleta add constraint atleta_d3
check (nacionalidad <> '');

alter table atleta alter column fechaNacimiento
set not null;

alter table atleta add constraint atleta_d4
check (nombre <> '');

alter table atleta add constraint atleta_d5
check (primerApellido <> '');

alter table atleta add constraint atleta_d6
check (genero = 'M' or genero = 'F');

alter table telefonoAtleta add constraint telefonoAtleta_d1
check (telefono similar to '[0-9]{10}');

alter table telefonoAtleta add constraint telefonoAtleta_pkey
primary key(numeroPasaporte, telefono);

alter table telefonoAtleta add constraint telefonoAtleta_fkey
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table correoAtleta add constraint correoAtleta_d1
check (correo like '%_@_%._%');

alter table correoAtleta add constraint correoAtleta_pkey
primary key(numeroPasaporte, correo);

alter table correoAtleta add constraint correoAtleta_fkey
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table practicar add constraint practicar_fkey1
foreign key(numeroPasaporte) references atleta(numeroPasaporte);

alter table practicar add constraint practicar_fkey2
foreign key(nombreDisciplina) references disciplina(nombreDisciplina);

-- restricciones del evento
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
check(duracionMaxima is not null and duracionMaxima between 0 and 24)

--entidad
alter table evento add constraint evento_pkey
primary key(idEvento)

--referencial
alter table evento add constraint evento_fkey1
foreign key(idLocalidad) references localidad(idLocalidad)

alter table evento add constraint evento_fkey2
foreign key(nombreDisciplina) references disciplina(nombreDisciplina)

-- restricciones de la entrada
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

-- entidad
alter table entrada add constraint entrada_pkey
primary key(folio)

-- referencial
alter table entrada add constraint entrada_fkey1
foreign key(nombreFase) references fase(nombreFase)

alter table entrada add constraint entrada_fkey2
foreign key(idEvento) references evento(idEvento)

-- restricciones del pais
alter table pais add constraint pais_d1
check(nombrePais is not null and nombrePais <> '');

-- entidad
alter table pais add constraint pais_pkey
primary key(nombrePais)

-- restricciones de la relacion participar
alter table participar alter column numeroPasaporte
set not null;
alter table participar alter column idEvento
set not null;

-- referencial
alter table participar add constraint participar_fkey1
foreign key(numeroPasaporte) references atleta(numeroPasaporte)

alter table participar add constraint participar_fkey2
foreign key(idEvento) references evento(idEvento)

DROP SCHEMA IF EXISTS JuegosOlímpicos CASCADE;
CREATE SCHEMA JuegosOlímpicos;

-------------TABLAS CON LLAVES PRIMARIAS-----------------


--Tabla Localidad
CREATE TABLE localidad(
	idLocalidad INT,
	nombrePais VARCHAR(50),
	calle VARCHAR(50),
	numero INT,
	ciudad VARCHAR(50),
	nombre VARCHAR(50),
	tipo VARCHAR(50),
	aforo INT
);

 --Restricciones Localidad
 	--Dominio
alter table localidad alter column idLocalidad
set not null;
ALTER TABLE localidad ADD CONSTRAINT localidad_d1
CHECK(nombrePais is not null and nombrePais <> '');
ALTER TABLE localidad ADD CONSTRAINT localidad_d2
CHECK(calle is not null and calle <> '');
alter table localidad alter column numero
set not null;
ALTER TABLE localidad ADD CONSTRAINT localidad_d3
CHECK(ciudad is not null and ciudad <> '');
ALTER TABLE localidad ADD CONSTRAINT localidad_d4
CHECK(nombre is not null and nombre <> '');
alter table localidad add constraint localidad_d5
CHECK(tipo is not null and tipo <> '');
alter table localidad add constraint localidad_d6
CHECK(aforo is not null and aforo > 0);


	--Entidad
ALTER TABLE localidad ADD CONSTRAINT localidad_pkey
PRIMARY KEY (idLocalidad);

	--Referencial
alter table localidad add constraint localidad_fkey
foreign key (nombrePais) references pais(nombrePais);


--Tabla Patrocinador 
create table patrocinador(
	nombrePatrocinador VARCHAR(50)
);

--Restricciones Patrocinador
	--Dominio
alter table patrocinador add constraint patrocinador_d1
check(nombrePatrocinador is not null and nombrePatrocinador <> '');
	--Entidad
alter table patrocinador add constraint patrocinador_pkey
primary key (nombrePatrocinador);


--Tabla Disciplina
create table disciplina(
	nombreDisciplina VARCHAR(50),
	categoria CHAR(10)
);
--Restricciones Disciplina
	--Dominino
alter table disciplina add constraint disciplina_d1
check (nombreDisciplina is not null and nombreDisciplina <> '');
alter table disciplina add constraint disciplina_d2
check(categoria = 'Individual' or categoria = 'Equipo')

	--Entidad
alter table disciplina add constraint disciplina_pkey
primary key (nombreDisciplina);


--Tabla Medalla
create table medalla(
	numeroMedalla INT,
	nombreDisciplina VARCHAR(50),
	numeroPasaporte INT,
	tipo CHAR(6)
);

--Restricciones Medalla
	--Dominio
alter table medalla add constraint medalla_d1
check(numeroMedalla is not null and numeroMedalla > 0);
alter table medalla add constraint medalla_d2
check(nombreDisciplina is not null  and nombreDisciplina <> '');
alter table medalla alter column numeroPasaporte set not null;
alter table medalla add constraint medalla_d3
check(tipo = 'Oro' or tipo = 'Plata' or tipo='Bronce');
	--Entidad
alter table medalla add constraint medalla_pkey
primary key (numeroMedalla);
	-- Referencial
alter table medalla add constraint medalla_fkey1
foreign key (nombreDisciplina) references disciplina(nombreDisciplina);
alter table medalla add constraint medalla_fkey2
foreign key (numeroPasaporte) references atleta(numeroPasaporte);

--Tabla patrocinar
create table patrocinar(
	nombrePatrocinador VARCHAR(50),
	nombreDisciplina VARCHAR(50)
);

--Restricciones Patrocinar	
	--Dominio
alter table patrocinar add constraint patrocinar_d1
check(nombrePatrocinador is not null and nombrePatrocinador <> '');
alter table patrocinar add constraint patrocinar_d2
check (nombreDisciplina is not null and nombreDisciplina <> '');
	--Referencial
alter table patrocinar add constraint medalla_fkey1
foreign key (nombrePatrocinador) references patrocinador(nombrePatrocinador);
alter table patrocinar add constraint medalla_fkey2
foreign key (nombreDisciplina) references disciplina(nombreDisciplina);