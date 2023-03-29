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
fecha_inicio date not null,
fecha_actualizacion date,
tratamiento varchar(250) not null,
aparatologia varchar(250) not null,
duracion varchar(50) not null,
pronostico varchar(250) not null,
observaciones varchar(250) not null,
primary key (folio));
ALTER TABLE expediente_clinico ADD COLUMN id_paciente int not null;
ALTER TABLE expediente_clinico ADD FOREIGN KEY(id_paciente) REFERENCES pacientes(id);