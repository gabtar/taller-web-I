-- Datos de prueba
USE spring;

-- Localidades
INSERT INTO `localidad` (`id`, `nombre`) VALUES
(1, 'Ramos Mejía'),
(2, 'Castelar'),
(3, 'Haedo'),
(4, 'San Justo'),
(5, 'Ciudadela');

-- Tamanios de Lockers
INSERT INTO `tamanio` (`id`, `tamanio`, `precio`) VALUES
(1, '60x50x50', 1),
(2, '90x80x80', 5),
(3, '120x100x100', 10);

-- Sucursales
INSERT INTO `sucursal` (`id`, `nombre`, `localidad_id`, `latitud`, `longitud`) VALUES
(1, 'Rent Lock Ramos I', 1, -34.6615981452468, -58.56162133337975),
(2, 'Rent Lock Ramos II', 1, -34.64107837481893, -58.566415959742),
(3, 'Rent Lock Haedo', 3, -34.65056503134718, -58.58316680170608),
(4, 'Rent Lock Ciudadela I', 5, -34.640134384365325, -58.54924123358824),
(5, 'Rent Lock Ciudadela II', 5, -34.64730161927227, -58.53894154937148);

-- Lockers
INSERT INTO `locker` (`id`, `ocupado`, `tamanio_id`, `sucursal_id`) VALUES
(1, b'0', 1, 1),
(2, b'0', 1, 1),
(3, b'0', 1, 1),
(4, b'0', 1, 1),
(5, b'0', 1, 1),
(6, b'0', 1, 1),
(7, b'0', 3, 1),
(8, b'0', 3, 1),
(9, b'0', 1, 2),
(10, b'0', 2, 2),
(11, b'0', 2, 2),
(12, b'0', 2, 2),
(13, b'0', 3, 2),
(14, b'0', 2, 2),
(15, b'0', 1, 3),
(16, b'0', 1, 3),
(17, b'0', 3, 3),
(18, b'0', 3, 3),
(19, b'0', 2, 4),
(20, b'0', 1, 4),
(21, b'0', 1, 5),
(22, b'0', 1, 5);