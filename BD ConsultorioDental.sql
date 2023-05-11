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
SET GLOBAL time_zone = '-7:00';

CREATE TABLE expediente_clinico (
    folio INT NOT NULL AUTO_INCREMENT,
    fecha DATE,
    motivo VARCHAR(70),
    enfermedad VARCHAR(255),
    observaciones VARCHAR(255),
    tratamiento VARCHAR(255),
    estado CHAR(1),
    id_paciente INT,
    PRIMARY KEY (folio),
    FOREIGN KEY (id_paciente) REFERENCES pacientes(id)
);

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
ALTER TABLE expediente_clinico ADD FOREI¿GN KEY(id_paciente) REFERENCES pacientes(id);

CREATE TABLE IF NOT EXISTS Usuarios(
id_usuario int(2) not null primary key auto_increment,
usuario varchar(20) not null,
contraseña varchar(32) not null,
rol varchar(20) not null);
Alter table Usuarios Add constraint UQ_Usuario Unique (usuario);

SELECT*FROM PACIENTES;
SELECT *FROM EXPEDIENTE_CLINICO;
insert into Usuarios values (2,'Secretaria','Secre012','Secretaria');
insert into Usuarios values (1,'Dentista','Dentis123','Dentista');
select usuario, contraseña from usuarios;
select*from usuarios;
delete from usuarios where id_usuario=2;
-- |Motivos:
-- Limpieza dental
-- Dolor de muelas
-- Caries dental
-- Extracción dental
-- Blanqueamiento dental
-- Ortodoncia
-- Diente astillado
-- Problemas de encías
-- Revisión de rutina
-- Problemas de mordida
-- Prótesis dental
-- Inflamación dental
-- Sensibilidad dental
-- Implante dental
-- Frenillos dentales

-- |Enfermedades:
-- Caries dental
-- Gingivitis
-- Periodontitis
-- Maloclusión
-- Diente impactado
-- Infección dental
-- Diente astillado o roto
-- Sensibilidad dental
-- Bruxismo
-- Halitosis (mal aliento)
-- Placa dental
-- Cálculos dentales
-- Lesiones de la mucosa oral
-- Quistes dentales
-- Lesiones de la lengua o labios