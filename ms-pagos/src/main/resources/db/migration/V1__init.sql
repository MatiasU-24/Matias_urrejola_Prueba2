CREATE TABLE IF NOT EXISTS pagos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    codigo_transaccion VARCHAR(80) NOT NULL,
    metodo_pago VARCHAR(40) NOT NULL,
    monto DECIMAL(10,2) NOT NULL,
    numero_cuotas INT NOT NULL,
    pagado BIT NOT NULL,
    fecha_pago DATETIME NOT NULL,
    reserva_id INT NOT NULL
);

INSERT INTO pagos (codigo_transaccion, metodo_pago, monto, numero_cuotas, pagado, fecha_pago, reserva_id) VALUES
('TRX-90001', 'Tarjeta credito', 96000.00, 3, true, NOW(), 1),
('TRX-90002', 'Debito', 236000.00, 1, true, NOW(), 2),
('TRX-90003', 'Transferencia', 190000.00, 1, true, NOW(), 3);
