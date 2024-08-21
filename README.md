# POS System

POS es un sistema de punto de venta completo desarrollado para gestionar eficientemente las operaciones de ventas en un entorno comercial. Este proyecto está diseñado para proporcionar una solución integral para la administración de ventas, productos, categorías, clientes y compras.

## Funcionalidades principales

- **Gestión de ventas:** Realiza y administra transacciones.
- **Administración de productos:** Añade, edita y elimina productos del inventario.
- **Categorías de productos:** Organiza los productos en categorías.
- **Gestión de clientes:** Guarda y administra información de los clientes.
- **Generación de reportes:** Genera reportes en PDF utilizando iTextPDF.
- **Sistema de autenticación:** Implementación de seguridad con Spring Security.
- **Base de datos:** Manejo de datos con PostgreSQL.

## Tecnologías utilizadas

- **Java** 
- **Spring Boot**
- **Spring Security** 
- **Thymeleaf** 
- **PostgreSQL** 
- **Docker** 
- **iTextPDF**

## Capturas de pantalla

Aquí puedes ver algunas capturas de pantalla del proyecto para obtener una idea visual de cómo luce la aplicación en funcionamiento.

### Pantalla de Login
![Pantalla de Login](https://github.com/Angel-Raa/POS-Spring-Boot/blob/main/src/main/resources/static/img/login.png)
*Interfaz de la pantalla de inicio de sesión.*

### Pantalla de Inicio (Home)
![Pantalla de Inicio](https://github.com/Angel-Raa/POS-Spring-Boot/blob/main/src/main/resources/static/img/home.png)
*Vista general de la pantalla de inicio una vez que el usuario ha iniciado sesión.*

### Lista de Categorías
![Lista de Categorías](https://github.com/Angel-Raa/POS-Spring-Boot/blob/main/src/main/resources/static/img/list.png)
*Pantalla donde se muestran las categorías de productos disponibles en el sistema.*

### Reporte de Ventas
![Reporte de Ventas](https://github.com/Angel-Raa/POS-Spring-Boot/blob/main/src/main/resources/static/img/report.png)
*Interfaz del reporte de ventas, mostrando detalles de las transacciones realizadas.*


## Esquema de la Base de Datos

Esta sección proporciona una visión general del esquema de la base de datos utilizado en el proyecto. Incluye ejemplos de las tablas y sus relaciones para entender cómo se organiza la información.

### Tablas

#### Tabla `User`
- **user_id**: Identificador único del usuario (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **email**: Correo electrónico del usuario (VARCHAR(255), UNIQUE, NOT NULL)
- **username**: Nombre de usuario (VARCHAR(255), UNIQUE, NOT NULL)
- **password**: Contraseña del usuario (VARCHAR(255), NOT NULL)
- **role_id**: Identificador del rol del usuario (BIGINT, FOREIGN KEY a `Role(role_id)`)

#### Tabla `Role`
- **role_id**: Identificador único del rol (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **role_name**: Nombre del rol (VARCHAR(50), UNIQUE, NOT NULL)

#### Tabla `Product`
- **product_id**: Identificador único del producto (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **name**: Nombre del producto (VARCHAR(255), NOT NULL)
- **price**: Precio del producto (DECIMAL(10, 2), NOT NULL)
- **category_id**: Identificador de la categoría del producto (BIGINT, FOREIGN KEY a `Category(category_id)`)

#### Tabla `Category`
- **category_id**: Identificador único de la categoría (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **category_name**: Nombre de la categoría (VARCHAR(255), UNIQUE, NOT NULL)

#### Tabla `Purchase`
- **purchase_id**: Identificador único de la compra (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **user_id**: Identificador del usuario que realizó la compra (BIGINT, FOREIGN KEY a `User(user_id)`)
- **purchase_date**: Fecha de la compra (TIMESTAMP, DEFAULT CURRENT_TIMESTAMP)

#### Tabla `PurchaseDetail`
- **detail_id**: Identificador único del detalle de compra (BIGINT, AUTO_INCREMENT, PRIMARY KEY)
- **purchase_id**: Identificador de la compra (BIGINT, FOREIGN KEY a `Purchase(purchase_id)`)
- **product_id**: Identificador del producto (BIGINT, FOREIGN KEY a `Product(product_id)`)
- **quantity**: Cantidad del producto (INT, NOT NULL)
- **price**: Precio del producto (DECIMAL(10, 2), NOT NULL)

### Relaciones entre Tablas

- **User** tiene una relación de muchos a uno con **Role**.
- **Product** tiene una relación de muchos a uno con **Category**.
- **Purchase** tiene una relación de muchos a uno con **User**.
- **PurchaseDetail** tiene una relación de muchos a uno con **Purchase** y **Product**.

### Diagramas de Relación

Puedes incluir un diagrama ER (Entidad-Relación) para visualizar las relaciones entre las tablas. Aquí hay un enlace a un diagrama de ejemplo:

![Diagrama ER](https://github.com/Angel-Raa/POS-Spring-Boot-Thymeleaf/blob/main/src/main/resources/static/img/base-de-dato.png)


## Requisitos previos

- **Java 21** o superior.
- **Maven 3.6** o superior.
- **Docker** (opcional, si deseas ejecutar la aplicación en un contenedor).
- **PostgreSQL**.

## Instalación y ejecución

1. Clona el repositorio:
    ```bash
    git clone https://github.com/Angel-Raa/POS-Spring-Boot.git
    cd POS-System
    ```

2. Configura la base de datos PostgreSQL en `application.yml` o en las variables de entorno.

3. Construye el proyecto con Maven:
    ```bash
    mvn clean install
    ```

4. Ejecuta la aplicación:
    ```bash
    mvn spring-boot:run
    ```

5. (Opcional) Ejecuta en Docker:
    ```bash
    docker build -t pos-system .
    docker run -p 9090:9090 pos-system
    ```

## Contribuciones

¡Las contribuciones son bienvenidas! Siéntete libre de abrir un _issue_ o un _pull request_.

## Recursos Adicionales

Aquí hay algunos recursos adicionales que podrían ser útiles:
- [Documentacion de Spring Data](https://spring.io/projects/spring-data)
- [Documentacion de open jdk 21](https://docs.oracle.com/en/java/javase/21/)
- [Documentacion de Spring boot](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)
- [Documentacion de Maven](https://maven.apache.org/guides/getting-started/)
- [Documentacion de Docker](https://docs.docker.com/)
- [Documentacion de Thymeleaf](https://www.thymeleaf.org/documentation.html)
- [Documentacion de Spring Security](https://docs.spring.io/spring-security/reference/index.html)
- [Documentacion de iTextPDF](https://itextpdf.com/resources/api-documentation)
- [Documentacion de Git](https://git-scm.com/doc)


## Licencia

Este proyecto está licenciado bajo la Licencia MIT - consulta el archivo [LICENSE](LICENSE) para más detalles.
