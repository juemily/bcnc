Aquí tienes un ejemplo de **README.md** detallado para el microservicio, enfatizando la arquitectura hexagonal:

---

# Microservicio de Precios con Arquitectura Hexagonal

Este microservicio permite consultar los precios de productos para el core ecommerce de Inditex. La aplicación está desarrollada utilizando Spring Boot, con una base de datos H2 en memoria, y está implementada siguiendo una **arquitectura hexagonal** (ports & adapters) para separar la lógica de negocio de la infraestructura.

---

## Características

- **Endpoint REST**: Consulta de precios basado en:
    - Fecha de aplicación.
    - ID del producto.
    - ID de la marca.

- **Base de Datos en Memoria**: Se utiliza H2 con scripts de inicialización (`schema.sql` y `data.sql`) para la tabla `PRICES`.

- **Arquitectura Hexagonal**:
    - **Dominio**: Contiene el modelo (`Price`) y sus puertos (interfaces) que definen la lógica de negocio.
    - **Aplicación**: Caso de uso (`PriceQueryService`) que orquesta la consulta de precios.
    - **Infraestructura**: Adaptadores que implementan el repositorio (persistencia con Spring Data JPA) y exponen el endpoint REST.

- **Pruebas de Integración**: Se han implementado tests que validan múltiples escenarios basados en rangos de fechas y prioridades.

---

## Estructura del Proyecto

La organización de paquetes es la siguiente:

```
com.inditex.pricing
├── application
│   └── PriceService.java       // Lógica de aplicación que consulta precios
├── domain
│   ├── Price.java                   // Modelo de dominio
│   └── ports
│       └── PriceRepository.java     // Interfaz para acceder a los precios
├── infrastructure
│   ├── adapter
│   │   └── PriceRepositoryAdapter.java    // Implementación del puerto de persistencia
│   ├── persistence
│   │   ├── PriceJpaEntity.java             // Entidad JPA para la tabla PRICES
│   │   └── PriceJpaRepository.java         // Repositorio Spring Data JPA
│   └── web
│   |    └── PriceController.java            // Controlador REST que expone el endpoint
|   |
|   └── config
│   |    └── JacksonConfig.java              //configuracioens dto 
|   |    └── ModelMapperConfig.java          // configuraciones mapper
|   └── Mapper
│       └── PriceMapper.java              //mappa de objetos
|       
|    
└── InditexApplication.java           // Clase principal que inicia la aplicación Spring Boot
```

Además, se incluyen los siguientes archivos de configuración y datos:

- **application.properties**: Configuraciones del datasource (H2), logging SQL, consola H2, entre otras.
- **schema.sql**: Definición de la tabla `PRICES`.
- **data.sql**: Inserción de datos de ejemplo en la tabla `PRICES`.

---

## Requisitos y Tecnologías

- **Java 11 o superior**
- **Maven** o **Gradle** para la gestión de dependencias y compilación.
- **Frameworks y librerías**:
    - Spring Boot
    - Spring Data JPA
    - H2 Database
    - JUnit y Spring Boot Test para pruebas

---

## Configuración y Ejecución

1. **Clonar el repositorio y ubicarse en la raíz** del proyecto.

2. **Construir el proyecto** con Maven:

   ```bash
   mvn clean install
   ```

3. **Ejecutar la aplicación**:

   ```bash
   mvn spring-boot:run
   ```

   También puedes ejecutar la clase principal `PricingApplication.java` desde tu IDE.

4. La base de datos H2 se inicializa con los scripts `schema.sql` y `data.sql`. Puedes acceder a la consola de H2 en [http://localhost:8080/h2-console](http://localhost:8080/h2-console) (asegúrate de tener configurado `spring.h2.console.enabled=true` en `application.properties`).

---

## Endpoints y Uso

### Consulta de Precio

- **URL**: `[GET] /api/prices`
- **Parámetros**:
    - `applicationDate`: Fecha de aplicación en formato **`yyyy-MM-dd-HH.mm.ss`** (ejemplo: `2020-06-14-10.00.00`).
    - `productId`: ID del producto (por ejemplo, `35455`).
    - `brandId`: ID de la marca (por ejemplo, `1`).

**Ejemplo de petición**:

```http
GET http://localhost:8080/api/prices?applicationDate=2020-06-14-10.00.00&productId=35455&brandId=1
```

**Respuesta JSON**:

```json
{
  "productId": 35455,
  "brandId": 1,
  "priceList": 1,
  "startDate": "2020-06-14T00:00:00",
  "endDate": "2020-12-31T23:59:59",
  "price": 35.50,
  "currency": "EUR"
}
```

El endpoint retorna el precio final aplicable según la fecha, producto y marca, considerando la prioridad y el intervalo de fechas definido en la tabla.

---

## Pruebas de Integración

Los tests se encuentran en:

```
src/test/java/com/inditex/pricing/infrastructure/web/PriceControllerTest.java
```

Se han definido casos de prueba para distintos escenarios (por ejemplo, consultas a diferentes horas y fechas) que verifican que se devuelven correctamente los registros esperados.

---

## Arquitectura Hexagonal

La aplicación está estructurada en torno a la arquitectura hexagonal:

- **Dominio**: Incluye el modelo `Price` y la interfaz `PriceRepository`. Esta capa define la lógica y reglas de negocio sin depender de detalles técnicos.

- **Aplicación**: El servicio `PriceQueryService` orquesta la consulta utilizando el puerto definido en el dominio.

- **Infraestructura**:
    - **Persistencia**: La entidad `PriceJpaEntity` y el repositorio Spring Data JPA (`PriceJpaRepository`) se encargan de la comunicación con la base de datos. El adaptador `PriceRepositoryAdapter` convierte los datos de la base al modelo de dominio.
    - **API REST**: El `PriceController` expone el endpoint, recibiendo y validando parámetros, y delega en el servicio de aplicación.

Esta separación facilita el mantenimiento, la extensibilidad y la posibilidad de intercambiar implementaciones de infraestructura sin afectar la lógica central.

---

## Logging y Depuración

Para facilitar la depuración, el archivo `application.properties` incluye configuraciones para mostrar el SQL ejecutado por Hibernate:

```properties
spring.jpa.show-sql=true
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE
```

Estas directrices ayudan a ver los parámetros enviados en cada consulta y verificar la correcta ejecución del query.

---

## Contribuciones

Si deseas aportar mejoras o nuevas funcionalidades:

1. **Haz un fork** del repositorio.
2. **Crea una rama** y desarrolla los cambios.
3. **Envía un Pull Request** para revisión.

---

## Licencia

Este microservicio de ejemplo se distribuye bajo una licencia de uso libre, ideal para fines educativos y como base para desarrollos más complejos en microservicios y arquitectura hexagonal.

---

Espero que esta guía te ayude a entender, ejecutar y extender el microservicio. Si deseas profundizar en algún aspecto o tienes sugerencias, ¡no dudes en contribuir o comentar!
