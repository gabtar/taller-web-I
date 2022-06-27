-- Datos de prueba
USE spring;

-- Localidades
INSERT INTO `localidad` (`id`, `nombre`) VALUES
(1, 'Ramos Mejía'),
(2, 'Castelar'),
(3, 'Haedo'),
(4, 'San Justo'),
(5, 'Ciudadela');

-- Sucursales
INSERT INTO `sucursal` (`id`, `nombre`, `localidad_id`) VALUES
(1, 'Rent Lock Ramos I', 1),
(2, 'Rent Lock Ramos II', 1),
(3, 'Rent Lock Haedo', 3),
(4, 'Rent Lock Ciudadela I', 5),
(5, 'Rent Lock Ciudadela II', 5);

-- Lockers
INSERT INTO `locker` (`id`, `fecha`, `fechaDeAlta`, `idSucursal`, `ocupado`, `tamano`, `textoDelUsuario`, `usuarioId`, `sucursal_id`) VALUES
(1, NULL, NULL, 1, b'0', '60x50x50', NULL, NULL, 1),
(2, NULL, NULL, 1, b'0', '60x50x50', NULL, NULL, 1),
(3, NULL, NULL, 1, b'0', '60x50x50', NULL, NULL, 1),
(4, NULL, NULL, 1, b'0', '60x50x50', NULL, NULL, 1),
(5, NULL, NULL, 1, b'0', '90x80x80', NULL, NULL, 1),
(6, NULL, NULL, 1, b'0', '90x80x80', NULL, NULL, 1),
(7, NULL, NULL, 1, b'0', '120x100x100', NULL, NULL, 1),
(8, NULL, NULL, 1, b'0', '120x100x100', NULL, NULL, 1),
(9, NULL, NULL, 2, b'0', '60x50x50', NULL, NULL, 2),
(10, NULL, NULL, 2, b'0', '90x80x80', NULL, NULL, 2),
(11, NULL, NULL, 2, b'0', '90x80x80', NULL, NULL, 2),
(12, NULL, NULL, 2, b'0', '90x80x80', NULL, NULL, 2),
(13, NULL, NULL, 2, b'0', '120x100x100', NULL, NULL, 2),
(14, NULL, NULL, 2, b'0', '90x80x80', NULL, NULL, 2),
(15, NULL, NULL, 3, b'0', '120x100x100', NULL, NULL, 3),
(16, NULL, NULL, 3, b'0', '120x100x100', NULL, NULL, 3),
(17, NULL, NULL, 3, b'0', '120x100x100', NULL, NULL, 3),
(18, NULL, NULL, 3, b'0', '120x100x100', NULL, NULL, 3),
(19, NULL, NULL, 4, b'0', '90x80x80', NULL, NULL, 4),
(20, NULL, NULL, 4, b'0', '60x50x50', NULL, NULL, 4),
(21, NULL, NULL, 5, b'0', '60x50x50', NULL, NULL, 5),
(22, NULL, NULL, 5, b'0', '60x50x50', NULL, NULL, 5);