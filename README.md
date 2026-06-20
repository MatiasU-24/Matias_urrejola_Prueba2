# Matias_urrejola_Prueba2

Proyecto backend con microservicios Spring Boot para arriendo de vehiculos.

## Microservicios del proyecto

| Microservicio | Puerto | Entidades principales |
| --- | ---: | --- |
| ms-clientes | 8081 | Cliente, Direccion |
| ms-vehiculos | 8082 | Vehiculo, Categoria |
| ms-reservas | 8083 | Reserva, EstadoReserva |
| ms-pagos | 8084 | Pago |
| ms-sucursales | 8085 | Sucursal, Region |
| ms-empleados | 8086 | Empleado |
| ms-reportes | 8087 | Reporte |
| ms-gateway | 8080 | Enrutamiento hacia los microservicios |

## Checklist aplicado

- Arquitectura Controller, Service y Repository separada.
- Repositories basados en JPA Repository.
- DTOs de entrada y salida.
- Mappers manuales Entity <-> DTO.
- Bean Validation con `@Valid` en controllers.
- Manejo de excepciones con `@RestControllerAdvice`.
- Logs con SLF4J en servicios.
- Feign Client entre reservas/clientes/vehiculos y otros flujos existentes.
- Swagger/OpenAPI agregado a `ms-clientes`, `ms-vehiculos` y `ms-reservas`.
- HATEOAS agregado a endpoints principales de Cliente, Direccion, Vehiculo, Categoria, Reserva y EstadoReserva.
- Tests unitarios de Service con JUnit y Mockito para las entidades trabajadas.
- Configuracion YAML aplicada en `ms-clientes`, `ms-vehiculos` y `ms-reservas`.
- API Gateway agregado en `ms-gateway`, con rutas a los 7 microservicios.

## Ejecucion

Iniciar MySQL en Laragon y ejecutar desde la raiz:

```powershell
mvn test
```

Para empaquetar:

```powershell
mvn package -DskipTests
```

Para levantar un microservicio:

```powershell
java -jar ms-clientes/target/ms-clientes-0.0.1-SNAPSHOT.jar
```

## API Gateway

El gateway corre en el puerto `8080` y enruta hacia los microservicios.

Ejemplos:

```text
GET http://localhost:8080/api/v1/clientes
GET http://localhost:8080/api/v1/vehiculos
GET http://localhost:8080/api/v1/reservas
```

## Swagger UI

Con cada servicio levantado:

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
```

## HATEOAS

Los endpoints principales de los tres modulos trabajados devuelven enlaces `_links`.

Ejemplo:

```text
GET http://localhost:8083/api/v1/reservas
```

La respuesta incluye enlaces `self`, `reservas`, `actualizar` y `eliminar`.
