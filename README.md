# Turismo TEC - Backend (API REST)

Este es el backend del proyecto Turismo TEC, desarrollado con **Spring Boot**. Expone los servicios REST para la gestión de entidades turísticas (Hoteles, Restaurantes, Lugares), reservaciones, foro, publicaciones, y maneja la lógica de negocio, integración con **Firebase** (notificaciones/almacenamiento), **OpenAI** (Chatbot) y **PayPal** (pasarela de pagos).

## 📌 Nota

Este proyecto es una copia/importación de otro repositorio en el que participé activamente. 
Lo mantengo aquí en mi perfil para tener control sobre el código y mostrar mi aporte, 
ya que el repositorio original podría ser eliminado en cualquier momento.

## Tecnologías Principales
- **Framework:** Spring Boot (Java 17)
- **Seguridad:** Spring Security con JSON Web Tokens (JWT)
- **Base de Datos:** JPA / Hibernate (MySQL/MariaDB)
- **Servicios Externos:** Firebase Admin SDK, OpenAI API, PayPal SDK, JavaMailSender

## Requisitos Previos
- Java JDK 17 o superior.
- Maven 3.8+ (o usar el wrapper `mvnw` incluido).
- Base de datos MySQL instalada y en ejecución en el puerto 3306.

## Configuración del Entorno

Asegúrate de no subir credenciales reales al repositorio. Modifica tu archivo `src/main/resources/application.properties` con los datos correspondientes a tu entorno:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/login
spring.datasource.username=root
spring.datasource.password=

# Credenciales de APIs Externas
openai.api.key=TU_API_KEY_AQUI
paypal.client.id=TU_CLIENT_ID
paypal.client.secret=TU_CLIENT_SECRET
paypal.mode=sandbox
```

Para las notificaciones push, el backend requiere el archivo de credenciales de servicio de Firebase. Debes colocar tu archivo `firebase-service.json` en `src/main/resources/` de forma segura en tu servidor de despliegue.

## Ejecución del Proyecto en Local

```bash
# 1. Navegar al directorio del backend
cd turismoTecBack-main

# 2. Instalar dependencias y compilar el proyecto
./mvnw clean install

# 3. Ejecutar la aplicación
./mvnw spring-boot:run
```

## Despliegue con Docker

El proyecto incluye un `Dockerfile` configurado para empaquetar la aplicación bajo una imagen de OpenJDK 17 en Alpine.

```bash
# Construir la imagen
docker build -t turismotec-backend:latest .

# Ejecutar el contenedor
docker run -p 8080:8080 turismotec-backend:latest
```

## Estructura de Paquetes Core
- `Config/`: Configuraciones de Firebase, Seguridad (Filtros JWT) y CORS.
- `Controller/`: Endpoints de la API REST organizados por dominios (Hoteles, Usuarios, Reservas, etc.).
- `Jwt/`: Generación, validación e interceptores de tokens.
- `model/entity/`: Entidades JPA de base de datos.
- `model/Dao/`: Interfaces de repositorios (Data Access Object).
- `model/service/`: Reglas de negocio e implementaciones de servicios externos.
