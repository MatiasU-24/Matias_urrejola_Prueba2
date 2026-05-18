# Pruebas ms-vehiculos

Puerto: `8082`

Base de datos: `prueba1`

Endpoints probados:

```text
GET http://localhost:8082/api/v1/vehiculos
GET http://localhost:8082/api/v1/categorias
GET http://localhost:8082/api/v1/vehiculos/disponibles/precio?precio=60000
```

Resultado: endpoints respondieron correctamente con vehiculos, categorias y filtro por disponibilidad/precio.
