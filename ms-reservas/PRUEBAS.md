# Pruebas ms-reservas

Puerto: `8083`

Base de datos: `prueba2`

Endpoints probados:

```text
GET http://localhost:8083/api/v1/reservas
GET http://localhost:8083/api/v1/estados-reserva
GET http://localhost:8083/api/v1/reservas/desde?fecha=2026-06-01
```

Comunicacion Feign:

- `ms-reservas` consulta `ms-clientes`.
- `ms-reservas` consulta `ms-vehiculos`.

Resultado: endpoints respondieron correctamente y la comunicacion Feign queda disponible al crear reservas.
