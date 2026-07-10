# Matias_urrejola_Prueba2

Proyecto backend desarrollado con Spring Boot para un sistema de arriendo de vehiculos.

El proyecto esta separado en microservicios, cada uno con su propia responsabilidad. En esta entrega se trabajo principalmente sobre clientes, vehiculos y reservas, ya que esas fueron las entidades solicitadas para la preparacion.

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
| ms-eureka-server | 8761 | Registro y descubrimiento de servicios |

## Trabajo realizado

En los microservicios `ms-clientes`, `ms-vehiculos` y `ms-reservas` se aplicaron las mejoras pedidas:

- Separacion por controller, service y repository.
- Uso de entidades JPA y repositories.
- DTOs para recibir y devolver datos.
- Validaciones en las peticiones.
- Manejo de errores.
- Comunicacion entre servicios usando Feign.
- Documentacion de endpoints con Swagger.
- Respuestas con enlaces usando HATEOAS.
- Pruebas unitarias con JUnit y Mockito.
- Configuracion usando archivos `application.yml`.

Tambien se agrego un gateway para centralizar las rutas hacia los microservicios.
El proyecto usa Eureka para registrar los servicios y permitir que el gateway y Feign los encuentren por nombre.

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

Para usar Eureka, primero se levanta:

```powershell
java -jar ms-eureka-server/target/ms-eureka-server-0.0.1-SNAPSHOT.jar
```

Luego se levantan los demas microservicios y se pueden revisar registrados en:

```text
http://localhost:8761
```

## API Gateway

El gateway corre en el puerto `8080`. Desde ahi se pueden probar rutas como:
Las rutas usan Eureka, por eso apuntan a nombres como `ms-clientes`, `ms-vehiculos` y `ms-reservas`.

Ejemplos:

```text
GET http://localhost:8080/api/v1/clientes
GET http://localhost:8080/api/v1/vehiculos
GET http://localhost:8080/api/v1/reservas
```

## Swagger UI

Con cada servicio levantado, Swagger se puede revisar en:

```text
http://localhost:8081/swagger-ui/index.html
http://localhost:8082/swagger-ui/index.html
http://localhost:8083/swagger-ui/index.html
```

## HATEOAS

Los endpoints principales de los tres modulos trabajados devuelven enlaces `_links`, por ejemplo en reservas:

Ejemplo:

```text
GET http://localhost:8083/api/v1/reservas
```
