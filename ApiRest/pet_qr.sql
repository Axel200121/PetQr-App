create database pet_qr;
use pet_qr;

create table usuario (
                         idUsuario int(4) PRIMARY KEY  AUTO_INCREMENT NOT NULL,
                         nombre varchar(50),
                         apellidoPaterno varchar(50),
                         apellidoMaterno varchar(50),
                         telefono varchar(10),
                         direccion varchar(100),
                         correo varchar(50),
                         psw varchar(50)
);

create table mascota(
                        idMascota int(4) PRIMARY KEY  AUTO_INCREMENT NOT NULL ,
                        nombre varchar(50),
                        tipoMascota varchar(50),
                        descripción varchar(150),
                        idUsuario int(4),
                        FOREIGN KEY (idUsuario) REFERENCES  usuario(idUsuario)

);