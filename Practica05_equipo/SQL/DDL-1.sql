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
