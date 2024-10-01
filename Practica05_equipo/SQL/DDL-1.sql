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
