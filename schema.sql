DROP DATABASE IF EXISTS proyecto_final_Fernando_Santiago_Villegas;
CREATE DATABASE proyecto_final_Fernando_Santiago_Villegas;

USE proyecto_final_Fernando_Santiago_Villegas;

CREATE TABLE estados(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	estado VARCHAR(50) NOT NULL,
	CONSTRAINT estado_UK UNIQUE (estado)
);

CREATE TABLE municipios(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	municipio VARCHAR(100) NOT NULL,
	CONSTRAINT municipio_UK UNIQUE (municipio)
);

CREATE TABLE codigos_postales(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_estados INT NOT NULL, 
	id_municipios INT NOT NULL,
	codigo_postal VARCHAR(5) NOT NULL,
	colonia VARCHAR(100) NOT NULL,
	CONSTRAINT id_estados_FK FOREIGN KEY (id_estados) REFERENCES estados(id),
	CONSTRAINT id_muncipios_FK FOREIGN KEY (id_municipios) REFERENCES municipios(id),
	CONSTRAINT codigo_postal_UK UNIQUE (codigo_postal)
);

CREATE TABLE ubicaciones(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_codigos_postales INT NOT NULL,
	calle VARCHAR(50) NOT NULL,
	exterior VARCHAR(50) NOT NULL,
	interior VARCHAR(50) DEFAULT 'N/A',
	referencias VARCHAR(300) NOT NULL,
	CONSTRAINT id_codigos_postales_FK FOREIGN KEY (id_codigos_postales) REFERENCES  codigos_postales(id),
	CONSTRAINT ubicacion_UK UNIQUE (id_codigos_postales, calle, exterior, interior)
);

CREATE TABLE personas_fisicas(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre VARCHAR(30) NOT NULL,
	paterno VARCHAR(20) NOT NULL,
	materno VARCHAR(20) DEFAULT '',
	rfc VARCHAR(13) NOT NULL,
	CONSTRAINT rfc_UK UNIQUE (rfc)
);

CREATE TABLE personas_morales(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	razon_social VARCHAR(100) NOT NULL,
	rfc VARCHAR(12) NOT NULL,
	CONSTRAINT rfc_UK UNIQUE (rfc)
);

CREATE TABLE roles(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	rol VARCHAR(30) NOT NULL,
	CONSTRAINT rol_uk UNIQUE (rol)
);

CREATE TABLE clientes(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	contraseña VARCHAR(1024) NOT NULL,
	id_personas_fisicas INT,
	id_personas_morales INT,
	id_roles INT NOT NULL,
	id_domicilio INT,
	tipo_persona ENUM('Moral', 'Física')  NOT NULL,
	CONSTRAINT id_personas_fisicas_FK FOREIGN KEY (id_personas_fisicas) REFERENCES personas_fisicas(id),
	CONSTRAINT id_personas_morales_FK FOREIGN KEY (id_personas_morales) REFERENCES personas_morales(id),
	CONSTRAINT id_roles_FK FOREIGN KEY (id_roles) REFERENCES roles(id),
	CONSTRAINT id_domicilio_FK FOREIGN KEY (id_domicilio) REFERENCES ubicaciones(id),
	CONSTRAINT fisica_or_moral_CK CHECK (
     (tipo_persona = 'Moral' AND id_personas_morales IS NOT NULL AND id_personas_fisicas IS NULL) OR
     (tipo_persona = 'Física' AND id_personas_fisicas IS NOT NULL AND id_personas_morales IS NULL)
   )
);

CREATE TABLE modelo_maquinas(
	id INT NOT NULL PRIMARY KEY  AUTO_INCREMENT,
	modelo VARCHAR(30) NOT NULL,
	capacidad INT NOT NULL,
	CONSTRAINT capacidad_CK CHECK(capacidad>0),
	CONSTRAINT modelo_UK  UNIQUE (modelo)
);

CREATE TABLE maquinas(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_clientes INT,
	id_modelo_maquinas INT NOT NULL,
	tipo_maquina BOOL NOT NULL,
	CONSTRAINT id_clientes_FK FOREIGN KEY (id_clientes) REFERENCES clientes(id),
	CONSTRAINT id_modelo_maquinas  FOREIGN KEY (id_modelo_maquinas) REFERENCES modelo_maquinas(id)
);

CREATE TABLE tipos_servicios(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	servicio VARCHAR(50) NOT NULL,
	descripcion VARCHAR(300) NOT NULL,
	CONSTRAINT servicio_UK UNIQUE (servicio)
);

CREATE TABLE areas(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	nombre_area VARCHAR(20) NOT NULL,
	CONSTRAINT nombre_area_uk UNIQUE (nombre_area)
);

CREATE TABLE empleados(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	contraseña VARCHAR(1024) NOT NULL,
	id_jefe INT,
	id_areas INT NOT NULL,
	id_personas_fisicas INT NOT NULL,
	estatus BOOL NOT NULL,
	CONSTRAINT id_jefe_FK FOREIGN KEY (id_jefe) REFERENCES empleados(id),
	CONSTRAINT id_areas_FK FOREIGN KEY (id_areas) REFERENCES areas(id),
	CONSTRAINT id_personas_fisicas_empleados_FK FOREIGN KEY (id_personas_fisicas) REFERENCES personas_fisicas(id),
	CONSTRAINT empleado_UK UNIQUE (id_jefe,id_areas,id_personas_fisicas)
);

CREATE TABLE roles_empleados(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_empleados INT NOT NULL,
	id_roles INT NOT NULL,
	CONSTRAINT id_empleados_roles_FK FOREIGN KEY (id_empleados) REFERENCES empleados(id),
	CONSTRAINT id_roles_empleados_FK FOREIGN KEY (id_roles) REFERENCES roles(id)
);

CREATE TABLE servicios(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_maquinas INT NOT NULL,
	id_empleados INT,
	id_tipos_servicios INT NOT NULL,
	fecha_inicio DATE,
	fecha_fin DATE,
	CONSTRAINT id_maquinas_FK FOREIGN KEY (id_maquinas) REFERENCES maquinas(id),
	CONSTRAINT id_empleados_servicios_FK FOREIGN KEY (id_empleados) REFERENCES empleados(id),
	CONSTRAINT id_tipos_servicios_FK FOREIGN KEY (id_tipos_servicios) REFERENCES tipos_servicios(id),
	CONSTRAINT servicio_UK UNIQUE (id_maquinas,id_empleados,id_tipos_servicios,fecha_inicio,fecha_fin),
	CONSTRAINT fechas_servicio_CK CHECK (
		(fecha_fin IS NOT NULL AND fecha_inicio IS NOT NULL) OR 
		(fecha_fin IS NULL AND fecha_inicio IS NOT NULL) OR
		(fecha_fin IS NULL AND fecha_inicio IS NULL)
		)
);

CREATE TABLE productos(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	producto VARCHAR(50) NOT NULL,
	cantidad FLOAT NOT NULL,
	unidad VARCHAR(20) NOT NULL,
	sellos BOOL NOT NULL,
	cantidad_sellos INT NOT NULL,
	precio FLOAT NOT NULL,
	CONSTRAINT cantidad_CK CHECK (cantidad > 0.0),
	CONSTRAINT producto_UK UNIQUE (producto, cantidad, unidad),
	CONSTRAINT check_sellos_cantidad_sellos CHECK (
     (sellos = TRUE AND cantidad_sellos >= 0) OR
     (sellos = FALSE AND cantidad_sellos = 0)
    )
);

CREATE TABLE envios(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
	id_empleados INT,
	id_productos INT NOT NULL,
	id_ubicaciones INT NOT NULL,
	clave_producto VARCHAR(100) NOT NULL,
	cantidad INT NOT NULL,
	fecha_solicitud DATE NOT NULL,
	fecha_envio DATE NULL,
	fecha_entrega DATE NULL,
	estatus ENUM('Solicitud', 'Atención', 'Enviado', 'Entregado') NOT NULL, 
	CONSTRAINT id_empleados_FK FOREIGN KEY (id_empleados) REFERENCES empleados(id),
	CONSTRAINT id_productos_FK FOREIGN KEY (id_productos) REFERENCES productos(id),
	CONSTRAINT id_ubicaciones_FK FOREIGN KEY (id_ubicaciones) REFERENCES ubicaciones(id),
	CONSTRAINT cantidad_envios_CK CHECK (cantidad>0),
	CONSTRAINT fecha_status_CK CHECK(
		(estatus = 'Enviado' AND fecha_envio IS NOT NULL) OR 
		(estatus = 'Entregado' AND fecha_entrega IS NOT NULL) OR 
		(estatus IN ('Solicitud', 'Atención'))
	)
);
