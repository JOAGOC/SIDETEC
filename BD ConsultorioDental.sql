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

CREATE TABLE IF NOT EXISTS Usuarios(
id_usuario int(2) not null primary key auto_increment,
usuario varchar(20) not null,
contraseña varchar(32) not null,
rol varchar(20) not null,
cambiarContra bit(1) not null);
Alter table Usuarios Add constraint UQ_Usuario Unique (usuario);

Insert into Usuarios values (2,'Secretaria','Secre012','Secretaria',0);
Insert into Usuarios values (1,'Dentista','Dentis123','Dentista',0);

CREATE TABLE `gestion_cita` (
  `idGestionCita` int NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `horario` varchar(50) DEFAULT NULL,
  `estatus` varchar(20) DEFAULT NULL,
  `idPaciente` int DEFAULT NULL,
  `detalleCita` varchar(50) DEFAULT NULL,
  `duracion` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idGestionCita`),
  KEY `idPaciente` (`idPaciente`),
  CONSTRAINT `gestion_cita_ibfk_1` FOREIGN KEY (`idPaciente`) REFERENCES `pacientes` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10966 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `pacientes` WRITE;
/*!40000 ALTER TABLE `pacientes` DISABLE KEYS */;
INSERT INTO `pacientes` VALUES (2,'Cesar Antonio','Medina Macias',20,'Masculino','3111617600','Magnolia #207 col.San Juan'),(3,'Alberto','Perez',23,'Masculino','3111830013','calle roma #20'),(7,'Carlos','Rodríguez',42,'Masculino','3456789012','Carrera 3 #50-60'),(8,'Luisa','Fernández',23,'Femenino','4567890123','Calle 4 #70-80'),(9,'Roberto','López',55,'Masculino','5678901234','Avenida 5 #90-100'),(10,'Carmen','Martínez',37,'Femenino','6789012345','Carrera 6 #110-120'),(11,'Miguel','García',31,'Masculino','7890123456','Calle 7 #130-140'),(12,'Sofía','Ruiz',26,'Femenino','8901234567','Avenida 8 #150-160'),(13,'Alejandro','Hernández',48,'Masculino','9012345678','Carrera 9 #170-180'),(14,'Laura','Jiménez',29,'Femenino','0123456789','Calle 10 #190-200'),(15,'Daniel','Díaz',34,'Masculino','0987654321','Avenida 11 #210-220'),(16,'Fernanda','Torres',27,'Femenino','9876543210','Carrera 12 #230-240'),(17,'Ricardo','Mendoza',41,'Masculino','8765432109','Calle 13 #250-260'),(18,'Camila','Morales',23,'Femenino','7654321098','Avenida 14 #270-280'),(19,'Manuel','Ortega',54,'Masculino','6543210987','Carrera 15 #290-300'),(20,'Isabel','Castro',36,'Femenino','45324534','Calle 16 #310-320'),(21,'David','Guerrero',30,'Masculino','4321098765','Avenida 17 #330-340'),(22,'Valentina','Aguirre',25,'Femenino','3210987654','Carrera 18 #350-360'),(23,'Javier','Ramírez',47,'Masculino','2109876543','Calle 19 #370-380'),(24,'Sandra','Vargas',28,'Femenino','1098765432','Avenida 20 #390-400'),(28,'Cesar','Medina',20,'Masculino','311244245','Lomas #32 col. Ticas');
/*!40000 ALTER TABLE `pacientes` ENABLE KEYS */;
UNLOCK TABLES;
DELIMITER //
CREATE PROCEDURE insertar_citas_disponibles(fecha_inicio DATE, fecha_fin DATE)
BEGIN
    DECLARE fecha_actual DATE;
    DECLARE horario_actual TIME;
    DECLARE intervalo_minutos INT DEFAULT 30;

    SET fecha_actual = fecha_inicio;

    WHILE fecha_actual <= fecha_fin DO

        SET horario_actual = '10:00';

        WHILE horario_actual <= '17:00' DO
            INSERT INTO gestion_cita (fecha, horario, estatus) VALUES (fecha_actual, horario_actual, 'Disponible');
            SET horario_actual = ADDTIME(horario_actual, SEC_TO_TIME(intervalo_minutos * 60));
        END WHILE;

        SET fecha_actual = DATE_ADD(fecha_actual, INTERVAL 1 DAY);

    END WHILE;
END;
//
DELIMITER ;
call insertar_citas_disponibles('2023-01-01','2023-12-31');
    
DELIMITER //
CREATE TRIGGER eliminar_citas_paciente BEFORE DELETE ON pacientes
FOR EACH ROW
BEGIN
  UPDATE gestion_cita
  SET estatus = 'Disponible', idPaciente = NULL , detalleCita=NULL
  WHERE idPaciente = OLD.id;
END;
//
DELIMITER ;

DELIMITER //
CREATE PROCEDURE actualizarCitas(IN fechaCita DATE, IN horaCita TIME, IN duracion INT)
BEGIN
    -- Calcula la hora de finalización de la cita
    SET @finCita = SEC_TO_TIME(TIME_TO_SEC(horaCita) + duracion * 60);

    -- Actualiza el estado de las citas en el rango de tiempo de la cita actual a "Bloqueado"
    UPDATE gestion_cita
    SET estatus = 'Bloqueado'
    WHERE fecha = fechaCita AND 
    horario >= horaCita AND horario < @finCita AND
    estatus = 'Disponible';
END//
DELIMITER ;


DELIMITER //
CREATE PROCEDURE bloquearCitasMediaHora(IN fechaCita DATE, IN horaCita TIME)
BEGIN
    DECLARE horas INT;
    DECLARE minutos INT;
    DECLARE duracion INT;

    -- Calcula la duración en minutos
    SET horas = HOUR(horaCita);
    SET minutos = MINUTE(horaCita);
    SET duracion = horas * 60 + minutos;

    -- Llama al procedimiento actualizarCitas para bloquear las citas
    CALL actualizarCitas(fechaCita, horaCita, duracion);
END//
DELIMITER ;


DELIMITER //

CREATE PROCEDURE eliminarCitasMediaHora(IN fechaCita DATE, IN horaCita TIME)
BEGIN
    DECLARE horas INT;
    DECLARE minutos INT;
    DECLARE duracion INT;
    DECLARE i INT;

    -- Inicializar la variable i en 0
    SET i = 0;

    -- Calcula la duración en minutos
    SET horas = HOUR(horaCita);
    SET minutos = MINUTE(horaCita);
    SET duracion = horas * 60 + minutos;

    -- Eliminar la cita
    DELETE FROM gestion_cita WHERE fecha = fechaCita AND horario = horaCita;

    -- Desbloquear las citas correspondientes a la duración
    IF duracion = 30 THEN
        CALL desbloquearCitasMediaHora(fechaCita, horaCita);
    ELSEIF duracion > 30 THEN
        WHILE i < duracion DO
            CALL desbloquearCitasMediaHora(fechaCita, ADDTIME(horaCita, SEC_TO_TIME(i * 60)));
            SET i = i + 30;
        END WHILE;
    END IF;
END//

DELIMITER ;

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