# Checklist de entrega

## Arquitectura

- [x] 7 microservicios independientes.
- [x] Puertos configurados del 8081 al 8087.
- [x] Bases de datos `prueba1`, `prueba2`, `prueba3` y `prueba4`.
- [x] Separacion por paquetes: controller, service, repository, entity, dto, mapper y exception.

## Persistencia y modelo

- [x] Entidades JPA con `@Entity`, `@Table`, `@Id` y `@GeneratedValue`.
- [x] Relaciones `Cliente -> Direccion`, `Categoria -> Vehiculo` y `EstadoReserva -> Reserva`.
- [x] Claves foraneas declaradas con `@JoinColumn`.
- [x] Cada entidad tiene al menos 5 atributos propios.
- [x] Cada entidad incluye atributos String, numericos, booleanos y de fecha.

## API REST

- [x] CRUD completo para todas las entidades.
- [x] Uso de `ResponseEntity` en controllers.
- [x] Validacion de request con `@Valid`.
- [x] Manejo global de excepciones con `@RestControllerAdvice`.
- [x] Respuestas 201 para creacion y 204 para eliminacion.

## Datos iniciales

- [x] `CommandLineRunner` en clientes, vehiculos, sucursales y empleados.
- [x] Flyway en reservas y pagos.
- [x] Liquibase en reportes.

## Integracion

- [x] FeignClient en reservas para consultar clientes y vehiculos.
- [x] FeignClient en pagos para consultar reservas.
- [x] FeignClient en reportes para consultar reservas y pagos.
- [x] Endpoint de reporte consolidado.

## Verificacion

- [x] Compilacion completa con `mvn clean package -DskipTests`.
- [x] Bases de datos creadas en MySQL de Laragon.
