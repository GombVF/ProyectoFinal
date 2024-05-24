USE proyecto_final_Fernando_Santiago_Villegas;

INSERT INTO estados (estado) VALUES 
('Estado1'),
('Estado2'),
('Estado3'),
('Estado4'),
('Estado5'),
('Estado6'),
('Estado7'),
('Estado8'),
('Estado9'),
('Estado10'),
('Estado11'),
('Estado12'),
('Estado13'),
('Estado14'),
('Estado15');

INSERT INTO municipios (municipio) VALUES 
('Municipio1'),
('Municipio2'),
('Municipio3'),
('Municipio4'),
('Municipio5'),
('Municipio6'),
('Municipio7'),
('Municipio8'),
('Municipio9'),
('Municipio10'),
('Municipio11'),
('Municipio12'),
('Municipio13'),
('Municipio14'),
('Municipio15');

INSERT INTO codigos_postales (id_estados, id_municipios, codigo_postal, colonia) VALUES 
(1, 1, '12345', 'Colonia1'),
(2, 2, '23456', 'Colonia2'),
(3, 3, '34567', 'Colonia3'),
(4, 4, '45678', 'Colonia4'),
(5, 5, '56789', 'Colonia5'),
(6, 6, '67890', 'Colonia6'),
(7, 7, '78901', 'Colonia7'),
(8, 8, '89012', 'Colonia8'),
(9, 9, '90123', 'Colonia9'),
(10, 10, '01234', 'Colonia10'),
(11, 11, '11223', 'Colonia11'),
(12, 12, '22334', 'Colonia12'),
(13, 13, '33445', 'Colonia13'),
(14, 14, '44556', 'Colonia14'),
(15, 15, '55667', 'Colonia15');

INSERT INTO ubicaciones (id_codigos_postales, calle, exterior, interior, referencias) VALUES 
(1, 'Calle1', 'Ext1', 'Int1', 'Referencia1'),
(2, 'Calle2', 'Ext2', 'N/A', 'Referencia2'),
(3, 'Calle3', 'Ext3', 'Int3', 'Referencia3'),
(4, 'Calle4', 'Ext4', 'Int4', 'Referencia4'),
(5, 'Calle5', 'Ext5', 'N/A', 'Referencia5'),
(6, 'Calle6', 'Ext6', 'Int6', 'Referencia6'),
(7, 'Calle7', 'Ext7', 'N/A', 'Referencia7'),
(8, 'Calle8', 'Ext8', 'Int8', 'Referencia8'),
(9, 'Calle9', 'Ext9', 'Int9', 'Referencia9'),
(10, 'Calle10', 'Ext10', 'N/A', 'Referencia10'),
(11, 'Calle11', 'Ext11', 'Int11', 'Referencia11'),
(12, 'Calle12', 'Ext12', 'N/A', 'Referencia12'),
(13, 'Calle13', 'Ext13', 'Int13', 'Referencia13'),
(14, 'Calle14', 'Ext14', 'N/A', 'Referencia14'),
(15, 'Calle15', 'Ext15', 'Int15', 'Referencia15');


INSERT INTO personas_fisicas (nombre, paterno, materno, rfc) VALUES 
('Nombre1', 'Paterno1', 'Materno1', 'XDHL400909XT4'),
('Nombre2', 'Paterno2', 'Materno2', 'NWGJ271208KG9'),
('Nombre3', 'Paterno3', '', 'SRFC560214JM7'),
('Nombre4', 'Paterno4', 'Materno4', 'VKUH730414XB7'),
('Nombre5', 'Paterno5', '', 'XDFF210808DO3'),
('Nombre6', 'Paterno6', 'Materno6', 'OOUP870416HA7'),
('Nombre7', 'Paterno7', 'Materno7', 'REBM410408GV4'),
('Nombre8', 'Paterno8', '', 'ZQEW770213BB6'),
('Nombre9', 'Paterno9', 'Materno9', 'CREU210226HH9'),
('Nombre10', 'Paterno10', 'Materno10', 'BWUV870205BY0');


INSERT INTO personas_morales (razon_social, rfc) VALUES 
('RazonSocial1', 'AKQ861227EH9'),
('RazonSocial2', 'KZW351108CE5'),
('RazonSocial3', 'HLO970517DE8'),
('RazonSocial4', 'FBY080422JJ9'),
('RazonSocial5', 'VTY280711JR2');

INSERT INTO roles (rol) VALUES 
('Cliente'),
('Servicios'),
('Ventas'),
('Manager'),
('Support'),
('Developer'),
('Tester'),
('Analyst'),
('Consultant'),
('Engineer'),
('Recursos Humanos'),
('Sales'),
('Marketing'),
('Finance'),
('Operations');

-- contraseña = contraseña

INSERT INTO clientes (contraseña, id_personas_fisicas, id_personas_morales, id_roles, id_domicilio, tipo_persona) VALUES 
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 1, NULL, 1, 1, 'Física'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 1, 1, 2, 'Moral'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 2, NULL, 1, 3, 'Física'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 3, NULL, 1, 4, 'Física'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 2, 1, 5, 'Moral'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 4, NULL, 1, 6, 'Física'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 3, 1, 7, 'Moral'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 5, NULL, 1, 8, 'Física'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 4, 1, 9, 'Moral'),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 5, 1, 11, 'Moral');

INSERT INTO modelo_maquinas (modelo, capacidad) VALUES 
('Modelo1', 100),
('Modelo2', 200),
('Modelo3', 300),
('Modelo4', 400),
('Modelo5', 500),
('Modelo6', 600),
('Modelo7', 700),
('Modelo8', 800),
('Modelo9', 900),
('Modelo10', 1000),
('Modelo11', 1100),
('Modelo12', 1200),
('Modelo13', 1300),
('Modelo14', 1400),
('Modelo15', 1500);

INSERT INTO maquinas (id_clientes, id_modelo_maquinas, tipo_maquina) VALUES 
(1, 1, TRUE),
(2, 2, FALSE),
(3, 3, TRUE),
(4, 4, FALSE),
(5, 5, TRUE),
(6, 6, FALSE),
(7, 7, TRUE),
(8, 8, FALSE),
(9, 9, TRUE),
(10, 10, FALSE);

INSERT INTO tipos_servicios (servicio, descripcion) VALUES 
('Servicio1', 'Descripcion1'),
('Servicio2', 'Descripcion2'),
('Servicio3', 'Descripcion3'),
('Servicio4', 'Descripcion4'),
('Servicio5', 'Descripcion5'),
('Servicio6', 'Descripcion6'),
('Servicio7', 'Descripcion7'),
('Servicio8', 'Descripcion8'),
('Servicio9', 'Descripcion9'),
('Servicio10', 'Descripcion10'),
('Servicio11', 'Descripcion11'),
('Servicio12', 'Descripcion12'),
('Servicio13', 'Descripcion13'),
('Servicio14', 'Descripcion14'),
('Servicio15', 'Descripcion15');

INSERT INTO areas (nombre_area) VALUES 
('Pedidos'),
('Servicios'),
('Recursos Humanos'),
('Sin Area'),
('Area5'),
('Area6'),
('Area7'),
('Area8'),
('Area9'),
('Area10'),
('Area11'),
('Area12'),
('Area13'),
('Area14'),
('Area15');

-- contraseña = contraseña

INSERT INTO empleados (contraseña, id_jefe, id_areas, id_personas_fisicas, estatus) VALUES 
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 2, 6, TRUE),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 1, 2, 7, TRUE),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 1, 8, TRUE),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', NULL, 1, 9, TRUE),
('$2a$12$s9MvM.2p0wemWSXO/C46YuXfNvJouWnrZTS1CD82kgaWFntVAHk4G', 3, 3, 10, TRUE);

INSERT INTO roles_empleados (id_empleados, id_roles) VALUES 
(1, 2),
(2, 2),
(3, 3),
(4, 3),
(5, 11);

INSERT INTO servicios (id_maquinas, id_empleados, id_tipos_servicios, fecha_inicio, fecha_fin) VALUES 
(1, null, 1, null, null),
(2, 2, 2, null, null),
(3, 2, 3, '2023-03-01', null),
(4, 2, 4, '2023-04-01', '2023-04-05'),
(5, 1, 5, '2023-05-01', '2023-05-06'),
(6, 1, 6, '2023-06-01', null),
(7, null, 7, null, null),
(8, 3, 8, '2023-08-01', '2023-08-09'),
(9, 3, 9, '2023-09-01', '2023-09-10'),
(10, 3, 10, '2023-10-01', '2023-10-11'),
(1, 4, 11, '2023-11-01', '2023-11-12'),
(2, 4, 12, '2023-12-01', null),
(3, 5, 13, null, null),
(4, 5, 14, null, null),
(5, 5, 15, null, null);

INSERT INTO productos (producto, cantidad, unidad, sellos, cantidad_sellos, precio) VALUES 
('Producto1', 1.5, 'kg', TRUE, 2, 100.0),
('Producto2', 2.0, 'litros', FALSE, 0, 150.0),
('Producto3', 3.5, 'piezas', TRUE, 5, 200.0),
('Producto4', 1.0, 'gramos', TRUE, 1, 50.0),
('Producto5', 2.5, 'mililitros', FALSE, 0, 75.0),
('Producto6', 4.0, 'kilogramos', TRUE, 3, 120.0),
('Producto7', 5.0, 'piezas', FALSE, 0, 90.0),
('Producto8', 1.2, 'kg', TRUE, 4, 110.0),
('Producto9', 3.0, 'litros', FALSE, 0, 140.0),
('Producto10', 2.7, 'gramos', TRUE, 2, 130.0),
('Producto11', 1.8, 'mililitros', FALSE, 0, 60.0),
('Producto12', 4.5, 'kilogramos', TRUE, 5, 160.0),
('Producto13', 2.2, 'piezas', FALSE, 0, 80.0),
('Producto14', 1.1, 'kg', TRUE, 3, 115.0),
('Producto15', 3.4, 'litros', FALSE, 0, 145.0);

INSERT INTO envios (id_empleados, id_productos, id_ubicaciones, clave_producto, cantidad, fecha_solicitud, fecha_envio, fecha_entrega, estatus) VALUES 
(1, 1, 1, 'Clave1', 1, '2023-01-01', '2023-01-02', '2023-01-03', 'Entregado'),
(1, 2, 1, 'Clave1', 2, '2023-01-01', '2023-01-02', '2023-01-03', 'Entregado'),
(1, 3, 1, 'Clave1', 3, '2023-01-01', '2023-01-02', '2023-01-03', 'Entregado');
INSERT INTO envios (id_empleados, id_productos, id_ubicaciones, clave_producto, cantidad, fecha_solicitud, fecha_envio, fecha_entrega, estatus) VALUES 
(2, 4, 2, 'Clave2', 4, '2023-04-01', '2023-04-05', NULL, 'Enviado'),
(2, 5, 2, 'Clave2', 5, '2023-04-01', '2023-04-05', NULL, 'Enviado'),
(2, 6, 2, 'Clave2', 6, '2023-04-01', '2023-04-05', NULL, 'Enviado');
INSERT INTO envios (id_empleados, id_productos, id_ubicaciones, clave_producto, cantidad, fecha_solicitud, fecha_envio, fecha_entrega, estatus) VALUES 
(3, 7, 3, 'Clave3', 7, '2023-07-01', NULL, NULL, 'Atención'),
(3, 8, 3, 'Clave3', 8, '2023-07-01', NULL, NULL, 'Atención'),
(3, 9, 3, 'Clave3', 9, '2023-07-01', NULL, NULL, 'Atención');
INSERT INTO envios (id_empleados, id_productos, id_ubicaciones, clave_producto, cantidad, fecha_solicitud, fecha_envio, fecha_entrega, estatus) VALUES 
(4, 10, 4, 'Clave4', 10, '2023-10-01', '2023-10-11', '2023-10-13', 'Entregado'),
(4, 11, 4, 'Clave4', 11, '2023-10-01', '2023-10-11', '2023-10-13', 'Entregado'),
(4, 12, 4, 'Clave4', 12, '2023-10-01', '2023-10-11', '2023-10-13', 'Entregado');
INSERT INTO envios (id_empleados, id_productos, id_ubicaciones, clave_producto, cantidad, fecha_solicitud, fecha_envio, fecha_entrega, estatus) VALUES 
(null, 13, 5, 'Clave5', 13, '2024-01-01', NULL, NULL, 'Solicitud'),
(null, 14, 5, 'Clave5', 14, '2024-01-01', NULL, NULL, 'Solicitud'),
(null, 15, 5, 'Clave5', 15, '2024-01-01', NULL, NULL, 'Solicitud');



