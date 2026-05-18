# Validacion de entrega

Fecha de validacion: 18-05-2026

Proyecto probado con Laragon/MySQL activo y los microservicios levantados en sus puertos correspondientes.

## Resultado general

- Compilacion Maven: `mvn test` finalizo con `BUILD SUCCESS`.
- Bases de datos verificadas: `prueba1`, `prueba2`, `prueba3`, `prueba4`.
- Endpoints CRUD principales respondieron correctamente.
- Queries personalizadas respondieron correctamente.
- Comunicacion Feign validada con generacion de reporte consolidado.

## Prueba Feign principal

Endpoint probado:

```text
POST http://localhost:8087/api/v1/reportes/consolidado
```

Resultado esperado:

- `ms-reportes` consulta reservas desde `ms-reservas`.
- `ms-reportes` consulta pagos desde `ms-pagos`.
- Se genera un nuevo reporte consolidado con total de reservas e ingresos.
