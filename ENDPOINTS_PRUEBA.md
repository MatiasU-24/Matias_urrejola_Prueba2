# Endpoints de prueba

## CRUD base

Cada recurso implementa:

```text
GET    /api/v1/{recurso}
GET    /api/v1/{recurso}/{id}
POST   /api/v1/{recurso}
PUT    /api/v1/{recurso}/{id}
DELETE /api/v1/{recurso}/{id}
```

## Recursos por microservicio

| Microservicio | Recursos |
|---|---|
| `ms-clientes` | `/api/v1/clientes`, `/api/v1/direcciones` |
| `ms-vehiculos` | `/api/v1/vehiculos`, `/api/v1/categorias` |
| `ms-reservas` | `/api/v1/reservas`, `/api/v1/estados-reserva` |
| `ms-pagos` | `/api/v1/pagos` |
| `ms-sucursales` | `/api/v1/sucursales`, `/api/v1/regiones` |
| `ms-empleados` | `/api/v1/empleados` |
| `ms-reportes` | `/api/v1/reportes` |

## Queries solicitadas

```text
GET http://localhost:8081/api/v1/clientes/buscar/email?texto=mail
GET http://localhost:8082/api/v1/vehiculos/disponibles/precio?precio=60000
GET http://localhost:8083/api/v1/reservas/desde?fecha=2026-06-01
GET http://localhost:8084/api/v1/pagos/rango-monto?min=50000&max=250000
GET http://localhost:8085/api/v1/sucursales/operativas
GET http://localhost:8086/api/v1/empleados/activos/anio/2024
```

## Integracion Feign

```text
POST http://localhost:8083/api/v1/reservas
```

Valida cliente en `ms-clientes` y disponibilidad de vehiculo en `ms-vehiculos`.

```text
POST http://localhost:8084/api/v1/pagos
```

Consulta la reserva en `ms-reservas` y valida que el monto coincida con el monto total.

```text
POST http://localhost:8087/api/v1/reportes/consolidado
```

Consulta reservas y pagos para generar un reporte consolidado.
