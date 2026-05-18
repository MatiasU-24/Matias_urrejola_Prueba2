# Pruebas ms-reportes

Puerto: `8087`

Base de datos: `prueba4`

Endpoints probados:

```text
GET http://localhost:8087/api/v1/reportes
POST http://localhost:8087/api/v1/reportes/consolidado
```

Comunicacion Feign:

- `ms-reportes` consulta `ms-reservas`.
- `ms-reportes` consulta `ms-pagos`.

Resultado: se genero reporte consolidado usando informacion obtenida desde otros microservicios.
