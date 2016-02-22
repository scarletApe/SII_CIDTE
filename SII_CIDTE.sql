-- =====================================================
-- Base de Datos de SII del CIDTE
-- Creado el 17-Feb-2016
-- =====================================================


-- ------------------------------------------------
-- Definición de base de  datos y permisos para el usuario
-- ------------------------------------------------
CREATE DATABASE `SIICIDTE` CHARACTER SET utf8 COLLATE utf8_general_ci;

use `SIICIDTE`;

-- ------------------------------------------------
-- Tipo Usuario
-- ------------------------------------------------
DROP TABLE IF EXISTS TipoUsuario;
CREATE TABLE IF NOT EXISTS TipoUsuario (
	id_tipousario INT(1) NOT NULL auto_increment,
	nombre VARCHAR(64) NOT NULL,
	descripcion VARCHAR(100) NOT NULL,
	PRIMARY KEY(id_tipousario)
);


-- ------------------------------------------------
-- Usuario
-- ------------------------------------------------
DROP TABLE IF EXISTS Usuario;
CREATE TABLE IF NOT EXISTS Usuario (
	id_usuario INT NOT NULL auto_increment,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    id_tipousario INT(1) NOT NULL,
    nombre VARCHAR(64) NOT NULL,
    apaterno VARCHAR(64) NOT NULL,
    amaterno VARCHAR(64),
    calle VARCHAR(64),
    colonia VARCHAR(64),
    ciudad VARCHAR(64),
    estado VARCHAR(64),
    codigopostal INT,
    telefono INT,
    email VARCHAR(64),
    PRIMARY KEY(id_usuario),
    FOREIGN KEY(id_tipousario) REFERENCES TipoUsuario(id_tipousario)
);

