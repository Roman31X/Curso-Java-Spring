-- CREAMOS LA BASE DE DATOS
CREATE SCHEMA `zona_fit_db` ;

-- Usamos la Base de datos Creada
USE zona_fit_db

-- CREAMOS LAS TABLAS EN LA BASE DE DATOS
CREATE TABLE `zona_fit_db`.`cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NULL,
  `apellido` VARCHAR(45) NULL,
  `membresia` INT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `membresia_UNIQUE` (`membresia` ASC) VISIBLE);