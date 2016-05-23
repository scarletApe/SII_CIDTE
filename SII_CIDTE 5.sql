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
-- Organizacion
-- ------------------------------------------------
CREATE TABLE Organizacion (
	id_organizacion INT NOT NULL auto_increment,
	nombre VARCHAR(64) NOT NULL,
	mensaje VARCHAR(100) NOT NULL,
	logo Longblob NOT NULL,
	PRIMARY KEY(id_organizacion)
);

-- ------------------------------------------------
-- DocumentoPDF
-- ------------------------------------------------
CREATE TABLE DocumentoPDF (
	id_documentopdf INT NOT NULL auto_increment,
	nombre VARCHAR(160) NOT NULL,
	documento Longblob NOT NULL,
	PRIMARY KEY(id_documentopdf)
);

-- ------------------------------------------------
-- Usuario
-- ------------------------------------------------
CREATE TABLE Usuario (
	curp CHAR(18) NOT NULL,
    username VARCHAR(64) NOT NULL,
    password VARCHAR(64) NOT NULL,
    rol VARCHAR(3) NOT NULL,
    id_organizacion INT NOT NULL,
    PRIMARY KEY(curp),
    FOREIGN KEY(id_organizacion) REFERENCES Organizacion(id_organizacion)
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
    telefono_casa VARCHAR(64),
    telefono_cell VARCHAR(64),
    telefono_oficina VARCHAR(64),
    fecha_nacimiento Date,
    sexo VARCHAR(10),
    foto Longblob,
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
-- TipoContratacion
-- ------------------------------------------------
CREATE TABLE TipoContratacion (
	id_contratacion INT NOT NULL auto_increment,
	curp CHAR(18) NOT NULL,
	tipo_participacion VARCHAR(120),
	HORAS VARCHAR(120),
	interno VARCHAR(120),
	institucion VARCHAR(120),
	area VARCHAR(120),
	programa VARCHAR(120),
	fecha_inicio DATE,
	fecha_terminacion DATE,
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
	id_documentopdf INT ,
	PRIMARY KEY(ID_NOMINA),
	FOREIGN KEY(id_contratacion) REFERENCES TipoContratacion(id_contratacion),
	FOREIGN KEY(id_documentopdf) REFERENCES DocumentoPDF(id_documentopdf)
);


-- ------------------------------------------------
-- Permiso
-- ------------------------------------------------
CREATE TABle Permiso(
	id_permiso INT NOT NULL AUTO_INCREMENT,
	curp CHAR(18) NOT NULL,
	motivo_solicitud VARCHAR(120),
	fecha_solicitud DATE,
	autorizado CHAR(1),
	fecha_autorizacion DATE,
	id_documentopdf INT ,
	PRIMARY KEY(id_permiso),
	FOREIGN KEY(curp) REFERENCES Usuario(curp),
	FOREIGN KEY(id_documentopdf) REFERENCES DocumentoPDF(id_documentopdf)
);


