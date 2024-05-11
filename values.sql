USE proyecto_final_Fernando_Santiago_Villegas;

INSERT INTO roles (rol) VALUES
('Ejecutivo'),
('Analista'),
('Coordinador'),
('Jefe de área'),
('Subdirector'),
('Director');

INSERT INTO personas_fisicas (nombre, paterno, materno, rfc) VALUES
('Juan', 'González', 'López', 'GOJL920915123');

INSERT INTO personas_morales (razon_social, rfc) VALUES
('Empresa XYZ', 'XYZ123456789');

INSERT INTO clientes (contraseña, id_personas_fisicas, id_personas_morales, id_roles, tipo_persona) VALUES
('contraseña1', 1, NULL, 1, 'Física'),
('contraseña2', NULL, 1, 2, 'Moral');

INSERT INTO personas_fisicas (nombre, paterno, materno, rfc) VALUES
('María', 'Sánchez', 'García', 'SAGM890710123'),
('Carlos', 'López', 'Hernández', 'LOHC880502123');

INSERT INTO areas (nombre_area) VALUES
('Ventas'),
('Operaciones');

INSERT INTO empleados (contraseña, id_jefe, id_areas, id_personas_fisicas, estatus, tipo_empleado) VALUES
('contraseña3', NULL, 1, 1, TRUE, 'Ejecutivo'),
('contraseña4', 1, 2, 2, TRUE, 'Analista');

INSERT INTO roles_empleados (id_empleados, id_roles) VALUES
(1, 1),
(2, 2);
