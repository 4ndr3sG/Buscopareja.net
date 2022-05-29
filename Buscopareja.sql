CREATE DATABASE buscopareja;
USE buscopareja;
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema buscopareja
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema buscopareja
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `buscopareja` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci ;
USE `buscopareja` ;

-- -----------------------------------------------------
-- Table `buscopareja`.`Ubicacion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`Ubicacion` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Comunidad` VARCHAR(20) NOT NULL,
  `Provincia` VARCHAR(20) NOT NULL,
  `Codigopostal` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `buscopareja`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`Hotel` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `NombreHotel` VARCHAR(35) NOT NULL,
  `Habitacion` INT NULL,
  `Ubicacion_ID` INT NOT NULL,
  PRIMARY KEY (`ID`)/*
  INDEX `fk_Hotel_Ubicacion1_idx` (`Ubicacion_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Hotel_Ubicacion1`
    FOREIGN KEY (`Ubicacion_ID`)
    REFERENCES `buscopareja`.`Ubicacion` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE*/)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `buscopareja`.`Gustos`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`Gustos` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Tipo` ENUM("Cine", "Musica", "Deporte", "Evento social", "Retiro espiritual", "Fiesta", "Cultura") NOT NULL,
  `Concidencias` INT NOT NULL,
  PRIMARY KEY (`ID`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_bin;


-- -----------------------------------------------------
-- Table `buscopareja`.`Usuario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`Usuario` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Email` VARCHAR(25) NOT NULL,
  `Pais_nac` VARCHAR(20) NULL DEFAULT NULL,
  `Nombre_user` VARCHAR(20) NULL DEFAULT NULL,
  `Ciudad_nac` VARCHAR(20) NULL DEFAULT NULL,
  `Sexo` CHAR(1) NULL DEFAULT NULL,
  `Orientacion` VARCHAR(12) NULL DEFAULT NULL,
  `contrasena` VARCHAR(25) NULL,
  `Ubicacion_ID` INT NOT NULL,
  `Gustos_ID` INT NOT NULL,
  PRIMARY KEY (`ID`, `Email`)/*
  INDEX `fk_Usuario_Ubicacion1_idx` (`Ubicacion_ID` ASC) VISIBLE,
  INDEX `fk_Usuario_Gustos1_idx` (`Gustos_ID` ASC) VISIBLE,
  CONSTRAINT `fk_Usuario_Ubicacion1`
    FOREIGN KEY (`Ubicacion_ID`)
    REFERENCES `buscopareja`.`Ubicacion` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Usuario_Gustos1`
    FOREIGN KEY (`Gustos_ID`)
    REFERENCES `buscopareja`.`Gustos` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE*/)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `buscopareja`.`citas`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`citas` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Fecha` DATE NOT NULL,
  `Hotel_ID` INT NOT NULL,
  `Hotel_ID1` INT NOT NULL,
  `Usuario_ID` INT NOT NULL,
  `Usuario_Email` VARCHAR(25) NOT NULL,
  PRIMARY KEY (`ID`)
  /*INDEX `fk_citas_Hotel1_idx` (`Hotel_ID1` ASC) VISIBLE,
  INDEX `fk_citas_Usuario1_idx` (`Usuario_ID` ASC, `Usuario_Email` ASC) VISIBLE,
  CONSTRAINT `fk_citas_Hotel1`
    FOREIGN KEY (`Hotel_ID1`)
    REFERENCES `buscopareja`.`Hotel` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_citas_Usuario1`
    FOREIGN KEY (`Usuario_ID` , `Usuario_Email`)
    REFERENCES `buscopareja`.`Usuario` (`ID` , `Email`)
    ON DELETE CASCADE
    ON UPDATE CASCADE*/)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;


-- -----------------------------------------------------
-- Table `buscopareja`.`Chat`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `buscopareja`.`Chat` (
  `ID` INT NOT NULL AUTO_INCREMENT,
  `Fecha_inicio` DATE NOT NULL,
  `Usuario_ID1` INT NOT NULL,
  `Usuario_ID2` INT NOT NULL,
  PRIMARY KEY (`ID`)
  /*INDEX `fk_Chat_Usuario1_idx` (`Usuario_ID1` ASC) VISIBLE,
  INDEX `fk_Chat_Usuario2_idx` (`Usuario_ID2` ASC) VISIBLE,
  CONSTRAINT `fk_Chat_Usuario1`
    FOREIGN KEY (`Usuario_ID1`)
    REFERENCES `buscopareja`.`Usuario` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `fk_Chat_Usuario2`
    FOREIGN KEY (`Usuario_ID2`)
    REFERENCES `buscopareja`.`Usuario` (`ID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE*/)
ENGINE = InnoDB
DEFAULT CHARACTER SET = latin1
COLLATE = latin1_spanish_ci;




-- -----------------------------------------------------
-- Table `buscopareja`.`Comentarios`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `buscopareja`.`comentarios` (
  `ID` INT NOT NULL AUTO_INCREMENT,
 	`Email` VARCHAR(25) NOT NULL,
  `Comentario` VARCHAR(2000) NULL,
  PRIMARY KEY (`ID`,`Email`)
    )
ENGINE = INNODB;
-- -----------------------------------------------------
-- Table `buscopareja`.`Fotos`
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS `buscopareja`.`fotos` (
  `ID` INT NOT NULL AUTO_INCREMENT,
 	`Email` VARCHAR(25) NOT NULL,
  `Comentario` VARCHAR(2000) NULL,
  foto mediumblob,
  PRIMARY KEY (`ID`,`Email`)
    )
ENGINE = INNODB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

ALTER TABLE hotel ADD CONSTRAINT fk_Hotel_Ubicacion1 FOREIGN KEY (`Ubicacion_ID`) REFERENCES buscopareja.Ubicacion(ID) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE usuario ADD CONSTRAINT `fk_Usuario_Gustos1` FOREIGN KEY (`Gustos_ID`) REFERENCES `buscopareja`.`Gustos` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE usuario ADD CONSTRAINT `fk_Usuario_Ubicacion1` FOREIGN KEY (`Ubicacion_ID`) REFERENCES `buscopareja`.`Ubicacion`(`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE citas ADD CONSTRAINT `fk_citas_Hotel1` FOREIGN KEY (`Hotel_ID1`) REFERENCES `buscopareja`.`Hotel` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE citas ADD CONSTRAINT `fk_citas_Usuario1` FOREIGN KEY (`Usuario_ID` , `Usuario_Email`) REFERENCES `buscopareja`.`Usuario` (`ID` , `Email`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE chat ADD CONSTRAINT `fk_Chat_Usuario1` FOREIGN KEY (`Usuario_ID1`) REFERENCES `buscopareja`.`Usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE chat ADD CONSTRAINT `fk_Chat_Usuario2` FOREIGN KEY (`Usuario_ID2`) REFERENCES `buscopareja`.`Usuario` (`ID`) ON DELETE CASCADE ON UPDATE CASCADE;

INSERT INTO ubicacion (Comunidad, Provincia, Codigopostal)
VALUES('Madrid', 'Madrid', '28041'),
('Cataluña', 'Barcelona', '08016'),
('Cataluña', 'Tarragona', '43100'),
('Comunidad Valenciana', 'Valencia', '46035'),
('Andalucía', 'Cádiz', '11071'),
('Castilla y león', 'Burgos', '09080');

INSERT INTO usuario (Email, Pais_nac, Nombre_user, Ciudad_nac, Sexo, Orientacion, Contrasena,ubicacion_id, gustos_id)
VALUES ("andres@gmail.com", "España", "Andres","Madrid", "H","Heterosexaul", "1234567890",1, 1);
INSERT INTO usuario (Email, Pais_nac, Nombre_user, Ciudad_nac, Sexo, Orientacion, Contrasena,ubicacion_id, gustos_id)
VALUES ("admin@admin", "España", "Admin","Madrid", "H","indiferente", "admin",1, 1);

INSERT INTO gustos (tipo, concidencias)
VALUES('Deporte', 0),
('Musica', 0),
('Evento social', 0),
('Cine', 0),
('Retiro espiritual', 0),
('Fiesta', 0),
('Cultura', 0);
