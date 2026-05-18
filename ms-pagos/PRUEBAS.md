# Pruebas ms-pagos

Puerto: `8084`

Base de datos: `prueba3`

Endpoints probados:

```text
GET http://localhost:8084/api/v1/pagos
GET http://localhost:8084/api/v1/pagos/rango-monto?min=100000&max=250000
```

Comunicacion Feign:

- `ms-pagos` consulta `ms-reservas` para validar la reserva asociada al pago.

Resultado: endpoints respondieron correctamente con pagos y filtro por rango de monto.
