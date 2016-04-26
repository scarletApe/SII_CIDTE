-- =====================================================
-- Base de Datos de SII del CIDTE
-- Creado el 17-Feb-2016
-- Modificado Mar 17 2016
-- =====================================================


-- ------------------------------------------------
-- Definición de base de  datos y permisos para el usuario
-- ------------------------------------------------
-- CREATE DATABASE `SIICIDTE` CHARACTER SET utf8 COLLATE utf8_general_ci;

-- use `SIICIDTE`;

-- ------------------------------------------------
-- Usuario
-- ------------------------------------------------
CREATE TABLE Usuario (
	curp CHAR(18) NOT NULL,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    rol VARCHAR(3) NOT NULL,
    PRIMARY KEY(curp)
);

-- ------------------------------------------------
-- Datos Generales del Usuario
-- ------------------------------------------------
CREATE TABLE DatosGenerales (
	id_dg INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	rfc VARCHAR(13) ,
	nombre VARCHAR(64) ,
    apaterno VARCHAR(64),
    amaterno VARCHAR(64),
    calle VARCHAR(64),
    colonia VARCHAR(64),
    ciudad VARCHAR(64),
    estado VARCHAR(64),
    codigopostal INT default 0,
    numero_ext INT default 0,
    numero_int INT default 0,
    telefono_casa INT default 0,
    telefono_cell INT default 0,
    telefono_oficina INT default 0,
    fecha_nacimiento Date,
    sexo VARCHAR(10),
    foto blob,
    email VARCHAR(64),
    PRIMARY KEY(id_dg),
    FOREIGN KEY(curp) REFERENCES Usuario(curp)
);

-- ------------------------------------------------
-- Datos Legales del Usuario
-- ------------------------------------------------
CREATE TABLE DatosLegales (
	id_dl INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	nacionalidad VARCHAR(64),
	visa_americana VARCHAR(64),
	id_pasaporte VARCHAR(64),
	vijencia_pasaporte Date,
	licencia_manejo VARCHAR(64),
	vijencia_licencia Date,
	PRIMARY KEY(id_dl),
	FOREIGN KEY(curp) REFERENCES Usuario(curp)
);

-- ------------------------------------------------
-- Datos Personales del Usuario
-- ------------------------------------------------
CREATE TABLE DatosPersonales (
	id_dp INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	religion VARCHAR(64),
	estado_civil VARCHAR(64),
	talla_altura INT default 0,
	talla_cintura INT default 0,
	talla_cuello INT default 0,
	talla_calzado INT default 0,
	PRIMARY KEY(id_dp),
	FOREIGN KEY(curp) REFERENCES Usuario(curp)
);

-- ------------------------------------------------
-- Datos Medicos del Usuario
-- ------------------------------------------------
CREATE TABLE DatosMedicos (
	id_dm INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	numero_ss VARCHAR(64),
	tipo_sangre VARCHAR(4),
	peso INT default 0,
	lentes VARCHAR(3),
	alergias VARCHAR(120),
	discapacidades VARCHAR(120),
	tratamiento_vida VARCHAR(120),
	entidad_medica VARCHAR(120),
	PRIMARY KEY(id_dm),
	FOREIGN KEY(curp) REFERENCES Usuario(curp)
);


-- ------------------------------------------------
-- Organizacion
-- ------------------------------------------------
CREATE TABLE Organizacion (
	id_organizacion INT NOT NULL auto_increment,
	nombre VARCHAR(64) NOT NULL,
	mensaje VARCHAR(100) NOT NULL,
	logo BLOB NOT NULL,
	PRIMARY KEY(id_organizacion)
);

-- ------------------------------------------------
-- TipoContratacion
-- ------------------------------------------------
CREATE TABLE TipoContratacion (
	id_contratacion INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	HORAS VARCHAR(120),
	interno VARCHAR(120),
	institucion VARCHAR(120),
	area VARCHAR(120),
	programa VARCHAR(120),
	PRIMARY KEY(id_contratacion),
	FOREIGN KEY(curp) REFERENCES Usuario(curp)
);

-- ------------------------------------------------
-- Nomina
-- ------------------------------------------------
CREATE TABle Nomina (
	ID_NOMINA INT NOT NULL AUTO_INCREMENT,
	id_contratacion INT NOT NULL,
	grupo_laboral VARCHAR(120),
	nivel VARCHAR(120),
	categorial VARCHAR(120),
	forma_contratacion VARCHAR(120),
	descripcion VARCHAR(120),
	pdf Blob ,
	PRIMARY KEY(ID_NOMINA),
	FOREIGN KEY(id_contratacion) REFERENCES TipoContratacion(id_contratacion)
);






