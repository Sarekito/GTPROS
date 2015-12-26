Drop table InformeSeguimiento;

Drop table Tarea;

Drop Table Actividad;

Drop table Etapa;

Drop table TrabajadoresProyecto;

Drop table Proyecto;

Drop table Vacaciones ;

Drop table Administrador ;

Drop table Trabajador;

Drop table TipoTarea ;

Drop table RolCat;

Drop table Rol;

Drop table Categoria;

 



CREATE TABLE Categoria (

 tipoCategoria INT NOT NULL

);

 



ALTER TABLE Categoria ADD CONSTRAINT PK_Categoria PRIMARY KEY (tipoCategoria);



CREATE TABLE Rol (

 tipoRol VARCHAR(25) NOT NULL

);



ALTER TABLE Rol ADD CONSTRAINT PK_Rol PRIMARY KEY (tipoRol);



CREATE TABLE RolCat (tipoRol VARCHAR(25), categoria INT, PRIMARY KEY (tipoRol, categoria));





CREATE TABLE TipoTarea (

 tipoTarea VARCHAR(25) NOT NULL

);



ALTER TABLE TipoTarea ADD CONSTRAINT PK_tipoTarea PRIMARY KEY (tipoTarea);





CREATE TABLE Trabajador (

 user VARCHAR(100) NOT NULL,

 password VARCHAR(100) DEFAULT 'password' NOT NULL,

 tipoRol VARCHAR(25) NOT NULL,

 tipoCategoria INT NOT NULL

);



ALTER TABLE Trabajador ADD CONSTRAINT PK_Trabajador PRIMARY KEY (user);



CREATE TABLE Administrador (

 user VARCHAR(100) NOT NULL,

 password VARCHAR(100) DEFAULT 'password' NOT NULL

);



ALTER TABLE Administrador ADD CONSTRAINT PK_Trabajador PRIMARY KEY (user);





CREATE TABLE Vacaciones (

 user VARCHAR(100) NOT NULL,

 periodo INT NOT NULL,

 ano INT NOT NULL,

 inicio DATE,
 
 semanas INT

);



ALTER TABLE Vacaciones ADD CONSTRAINT PK_Vacaciones PRIMARY KEY (user,periodo,ano);





CREATE TABLE Proyecto (

 nombre VARCHAR(100) NOT NULL,

 fechaInicio DATE ,

 fechaFin DATE,

 fechaFinReal DATE,

 jefeProyecto VARCHAR(100) NOT NULL,

 estado VARCHAR(20) NOT NULL

);



ALTER TABLE Proyecto ADD CONSTRAINT PK_Proyecto PRIMARY KEY (nombre);





CREATE TABLE TrabajadoresProyecto (

 nombre VARCHAR(100) NOT NULL,

 user VARCHAR(100) NOT NULL,

 porcentaje INT NOT NULL

);



ALTER TABLE TrabajadoresProyecto ADD CONSTRAINT PK_TrabajadoresProyecto PRIMARY KEY (nombre,user);





CREATE TABLE Etapa (

 nombre VARCHAR(100) NOT NULL,

 numero INT NOT NULL,

 fechaInicio DATE NOT NULL,

 fechaFin DATE NOT NULL,

 fechaFinReal DATE,

 estado VARCHAR(20)

);



ALTER TABLE Etapa ADD CONSTRAINT PK_etapa PRIMARY KEY (nombre,numero);





CREATE TABLE Actividad (

 nombre VARCHAR(100) NOT NULL,

 numero INT NOT NULL,

 id INT NOT NULL,

 descripcion VARCHAR(500) NOT NULL,

 duracion INT NOT NULL,

 duracionReal INT,

 fechaComienzo DATE NOT NULL,

 fechaFin DATE NOT NULL,

 fechaFinReal Date,

 estado VARCHAR(20),

 tipoRol VARCHAR(25)

);



ALTER TABLE Actividad ADD CONSTRAINT PK_Actividad PRIMARY KEY (nombre,numero,id);





CREATE TABLE Tarea (

 nombreProyecto VARCHAR(100) NOT NULL,

 numeroEtapa INT NOT NULL,

 idActividad INT NOT NULL,

 trabajador VARCHAR(100) NOT NULL,

 numTarea INT NOT NULL,

 semana DATE NOT NULL,

 tipoTarea VARCHAR(25) NOT NULL,

 duracion INT NOT NULL

);



ALTER TABLE Tarea ADD CONSTRAINT PK_Tarea PRIMARY KEY (nombreProyecto,numeroEtapa,idActividad,trabajador,numTarea,semana);





CREATE TABLE InformeSeguimiento (

 nombreProyecto VARCHAR(100) NOT NULL,

 numeroEtapa INT NOT NULL,

 idActividad INT NOT NULL,

 trabajador VARCHAR(100) NOT NULL,

 numTarea INT NOT NULL,

 semana DATE NOT NULL,

 estado VARCHAR(20) NOT NULL

);



ALTER TABLE InformeSeguimiento ADD CONSTRAINT PK_informeSeguimiento PRIMARY KEY (nombreProyecto,numeroEtapa,idActividad,trabajador,numTarea,semana);





ALTER TABLE Trabajador ADD CONSTRAINT FK_Trabajador_0 FOREIGN KEY (tipoRol) REFERENCES Rol (tipoRol);

ALTER TABLE Trabajador ADD CONSTRAINT FK_Trabajador_1 FOREIGN KEY (tipoCategoria) REFERENCES Categoria (tipoCategoria);





ALTER TABLE Vacaciones ADD CONSTRAINT FK_Vacaciones_0 FOREIGN KEY (user) REFERENCES Trabajador (user);





ALTER TABLE Proyecto ADD CONSTRAINT FK_Proyecto_0 FOREIGN KEY (jefeProyecto) REFERENCES Trabajador (user);






ALTER TABLE TrabajadoresProyecto ADD CONSTRAINT FK_TrabajadoresProyecto_0 FOREIGN KEY (nombre) REFERENCES Proyecto (nombre);

ALTER TABLE TrabajadoresProyecto ADD CONSTRAINT FK_TrabajadoresProyecto_1 FOREIGN KEY (user) REFERENCES Trabajador (user);





ALTER TABLE Etapa ADD CONSTRAINT FK_etapa_0 FOREIGN KEY (nombre) REFERENCES Proyecto (nombre);






ALTER TABLE Actividad ADD CONSTRAINT FK_Actividad_0 FOREIGN KEY (nombre, numero) REFERENCES Etapa (nombre, numero);



ALTER TABLE Actividad ADD CONSTRAINT FK_Actividad_2 FOREIGN KEY (tipoRol) REFERENCES Rol (tipoRol);





ALTER TABLE Tarea ADD CONSTRAINT FK_Tarea_0 FOREIGN KEY (nombreProyecto,numeroEtapa,idActividad) REFERENCES Actividad (nombre,numero,id);

ALTER TABLE Tarea ADD CONSTRAINT FK_Tarea_1 FOREIGN KEY (trabajador) REFERENCES Trabajador (user);

ALTER TABLE Tarea ADD CONSTRAINT FK_Tarea_2 FOREIGN KEY (tipoTarea) REFERENCES TipoTarea (tipoTarea);



ALTER TABLE RolCat ADD CONSTRAINT FK_RolCat_1 FOREIGN KEY (tipoRol) REFERENCES Rol(tipoRol);

ALTER TABLE RolCat ADD CONSTRAINT FK_RolCat_2 FOREIGN KEY (categoria) REFERENCES Categoria(tipoCategoria);





ALTER TABLE InformeSeguimiento ADD CONSTRAINT FK_informeSeguimiento_0 FOREIGN KEY (nombreProyecto,numeroEtapa,idActividad,trabajador,numTarea,semana) REFERENCES Tarea (nombreProyecto,numeroEtapa,idActividad,trabajador,numTarea,semana);





insert into TipoTarea values('TratoUsuarios');

insert into TipoTarea values('Reuniones');

insert into TipoTarea values('LecturaDocumentacion');

insert into TipoTarea values('ElaboracionDocumentacion');

insert into TipoTarea values('TareasProgramas');

insert into TipoTarea values('Otros');



insert into Rol values('JefeProyecto');

insert into Rol values('Analista');

insert into Rol values('Disenador');

insert into Rol values('AnalistaProgramador');

insert into Rol values('ResponsablePruebas');

insert into Rol values('Programador');

insert into Rol values('Probador');



insert into Categoria values(1);

insert into Categoria values(2);

insert into Categoria values(3);

insert into Categoria values(4);



insert into RolCat values ('JefeProyecto',1);

insert into RolCat values ('Analista',2);

insert into RolCat values ('Disenador',3);

insert into RolCat values ('AnalistaProgramador',3);

insert into RolCat values ('ResponsablePruebas',3);

insert into RolCat values ('Programador',4);

insert into RolCat values ('Probador',4);



insert into Administrador values ('admin', 'password');

insert into Trabajador values('user', 'password', 'Jefeproyecto', 1);


