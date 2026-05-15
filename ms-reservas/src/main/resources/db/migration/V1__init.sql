CREATE TABLE IF NOT EXISTS estados_reserva (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(80) NOT NULL,
    descripcion VARCHAR(200) NOT NULL,
    prioridad INT NOT NULL,
    permite_cancelacion BIT NOT NULL,
    fecha_creacion DATE NOT NULL,
    orden_visual INT NOT NULL
);

CREATE TABLE IF NOT EXISTS reservas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo VARCHAR(30) NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    dias_arriendo INT NOT NULL,
    monto_total DECIMAL(10,2) NOT NULL,
    activa BIT NOT NULL,
    fecha_creacion DATE NOT NULL,
    cliente_id INT NOT NULL,
    vehiculo_id INT NOT NULL,
    estado_reserva_id INT NOT NULL,
    CONSTRAINT fk_reserva_estado FOREIGN KEY (estado_reserva_id) REFERENCES estados_reserva(id)
);

INSERT INTO estados_reserva (nombre, descripcion, prioridad, permite_cancelacion, fecha_creacion, orden_visual) VALUES
('Pendiente', 'Reserva creada y pendiente de confirmacion', 1, true, CURRENT_DATE, 1),
('Confirmada', 'Reserva validada por el sistema', 2, true, CURRENT_DATE, 2),
('Finalizada', 'Reserva cerrada con entrega completada', 3, false, CURRENT_DATE, 3);

INSERT INTO reservas (codigo, fecha_inicio, fecha_fin, dias_arriendo, monto_total, activa, fecha_creacion, cliente_id, vehiculo_id, estado_reserva_id) VALUES
('RES-1001', DATE_ADD(CURRENT_DATE, INTERVAL 10 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 13 DAY), 3, 96000.00, true, CURRENT_DATE, 1, 1, 1),
('RES-1002', DATE_ADD(CURRENT_DATE, INTERVAL 15 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 19 DAY), 4, 236000.00, true, CURRENT_DATE, 2, 2, 2),
('RES-1003', DATE_ADD(CURRENT_DATE, INTERVAL 25 DAY), DATE_ADD(CURRENT_DATE, INTERVAL 27 DAY), 2, 190000.00, true, CURRENT_DATE, 3, 3, 1);
