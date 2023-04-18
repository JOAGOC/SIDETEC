CREATE DATABASE IF NOT EXISTS consultorio_dental;

USE consultorio_dental;

CREATE TABLE IF NOT EXISTS pacientes(
  id INT(11) NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  edad INT(3) NOT NULL,
  genero VARCHAR(10) NOT NULL,
  telefono VARCHAR(10) NOT NULL,
  direccion VARCHAR(100) NOT NULL,
  PRIMARY KEY (id)
);
SET GLOBAL time_zone = '-3:00';

CREATE TABLE IF NOT EXISTS expediente_clinico(
folio int(11) not null auto_increment,
fecha_inicio varchar(30) not null,
fecha_actualizacion varchar(30),
motivo varchar(250) not null,
enfermedad varchar(250) not null,
observaciones varchar(250) not null,
estado varchar(50) not null,
primary key (folio));
ALTER TABLE expediente_clinico ADD COLUMN id_paciente int not null;
ALTER TABLE expediente_clinico ADD FOREIGN KEY(id_paciente) REFERENCES pacientes(id);
drop table expediente_clinico;
CREATE TABLE IF NOT EXISTS Usuarios(
id_usuario int(2) not null primary key auto_increment,
usuario varchar(20) not null,
contraseña varchar(10) not null,
rol varchar(20) not null);
SELECT*FROM PACIENTES;
SELECT *FROM EXPEDIENTE_CLINICO;
insert into Usuarios values (2,'Secretaria','Secre012','Secretaria');
insert into Usuarios values (1,'Dentista','Dentis123','Dentista');
select usuario, contraseña from usuarios;
select*from usuarios;
delete from usuarios where id_usuario=2;
drop table Usuarios;