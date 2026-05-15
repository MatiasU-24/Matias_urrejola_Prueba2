# Matias_urrejola_Prueba2

Proyecto Spring Boot basado en las instrucciones de la prueba de microservicios.

## Autor

- Matias Urrejola

## Contexto

El sistema digitaliza la operacion de una empresa de arriendo de vehiculos. La arquitectura esta separada en 7 microservicios independientes, cada uno con su propia capa controller, service, repository, DTOs, mappers, entidades JPA y configuracion de persistencia.

## Microservicios

| Microservicio | Puerto | Base de datos | Entidades |
|---|---:|---|---|
| `ms-clientes` | 8081 | `prueba1` | `Cliente`, `Direccion` |
| `ms-vehiculos` | 8082 | `prueba1` | `Categoria`, `Vehiculo` |
| `ms-reservas` | 8083 | `prueba2` | `EstadoReserva`, `Reserva` |
| `ms-pagos` | 8084 | `prueba3` | `Pago` |
| `ms-sucursales` | 8085 | `prueba1` | `Region`, `Sucursal` |
| `ms-empleados` | 8086 | `prueba1` | `Empleado` |
| `ms-reportes` | 8087 | `prueba4` | `Reporte` |

## Bases de datos

Crear las bases en MySQL antes de iniciar:

```sql
CREATE DATABASE IF NOT EXISTS prueba1;
CREATE DATABASE IF NOT EXISTS prueba2;
CREATE DATABASE IF NOT EXISTS prueba3;
CREATE DATABASE IF NOT EXISTS prueba4;
```

Los `application.properties` usan usuario `root` sin password. Si tu MySQL tiene clave, actualiza `spring.datasource.password` en cada microservicio.

## Ejecucion

Desde la raiz, con Maven instalado:

```bash
mvn clean package -DskipTests
```

Para ejecutar un microservicio:

```bash
cd ms-clientes
mvn spring-boot:run
```

Cada microservicio tambien puede compilarse de forma independiente entrando a su carpeta y ejecutando:

```bash
mvn clean package -DskipTests
```

Levanta primero los servicios base si vas a probar Feign:

1. `ms-clientes`
2. `ms-vehiculos`
3. `ms-reservas`
4. `ms-pagos`
5. `ms-reportes`

## Endpoints principales

Cada entidad tiene CRUD completo:

- `GET /api/v1/{recurso}`
- `GET /api/v1/{recurso}/{id}`
- `POST /api/v1/{recurso}`
- `PUT /api/v1/{recurso}/{id}`
- `DELETE /api/v1/{recurso}/{id}`

Endpoints de queries solicitadas:

- `GET http://localhost:8081/api/v1/clientes/buscar/email?texto=mail`
- `GET http://localhost:8082/api/v1/vehiculos/disponibles/precio?precio=60000`
- `GET http://localhost:8083/api/v1/reservas/desde?fecha=2026-06-01`
- `GET http://localhost:8084/api/v1/pagos/rango-monto?min=50000&max=250000`
- `GET http://localhost:8085/api/v1/sucursales/operativas`
- `GET http://localhost:8086/api/v1/empleados/activos/anio/2024`
- `POST http://localhost:8087/api/v1/reportes/consolidado`

## Datos iniciales

- `CommandLineRunner`: clientes, vehiculos, sucursales y empleados.
- Flyway: reservas y pagos.
- Liquibase: reportes.

## Componentes implementados

- Entidades JPA con relaciones `@OneToMany`, `@ManyToOne` y `@JoinColumn`.
- DTOs de request con Bean Validation.
- DTOs de response para no exponer relaciones completas.
- Mappers manuales entre entidad y DTO.
- Services con logs SLF4J, reglas de negocio y manejo de errores.
- Controllers REST con `ResponseEntity`.
- `GlobalExceptionHandler` para errores 404, validaciones y reglas de negocio.
- FeignClient para validar cliente, vehiculo, reserva, pagos y consolidacion de reportes.
