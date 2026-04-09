GimnasioFit API - Sistema de Gestión de Membresías

⚠️ Estado del Proyecto: En Desarrollo

Este proyecto se encuentra actualmente en construcción. Estoy trabajando activamente en la implementación de la lógica de negocio, validaciones de seguridad y la integración completa de los servicios de pago.

📝 API REST profesional desarrollada con Java y Spring Boot para la administración integral de un gimnasio. El sistema está diseñado para gestionar el registro de socios, el control de membresías activas y el historial de pagos, utilizando estándares modernos de desarrollo backend.

🛠️ Stack Tecnológico

Lenguaje: Java.

Framework Principal: Spring Boot.

Base de Datos: H2 Database - Configurada para almacenar la información de forma local y permanente en archivos físicos del sistema.

Persistencia de Datos: Spring Data JPA / Hibernate.

Gestión de Dependencias: Maven.

Arquitectura: Diseño basado en capas (Controller, Service, Repository, DTO) para asegurar un código limpio y mantenible.

💾 Persistencia de Datos Local

A diferencia de las configuraciones volátiles, este proyecto utiliza H2 en modo archivo:

Persistencia Real: Los datos de socios, pagos y membresías se guardan localmente, garantizando que la información se mantenga disponible tras reiniciar la aplicación.

Cero configuración Externa: Ideal para entornos de prueba/evaluación, ya que permite la persistencia de datos sin necesidad de instalar motores de base de datos

Escalabilidad: Gracias al uso de Spring Data JPA, el sistema está listo para migrar a PostgreSQL o MySQL simplemente modificando el archivo de propiedades, manteniendo intacta la lógica de negocio.

🧪 Pruebas y Validación

Postman: Utilizo Postman para el testeo de todos los endpoints de la API, verificando los métodos GET, POST, PUT, PATCH y DELETE, así como la correcta estructura de las respuestas JSON.

AUN NO IMPLEMENTADO --> Manejo de Excepciones: Implementación de un controlador global de excepciones para asegurar respuestas de error claras y profesionales (ManejoExcepciones.java).

📂 Estructura del Código

controlador: Definición de los puntos de entrada (endpoints) de la API.

dto: Objetos de transferencia de datos para desacoplar la base de datos de la capa de presentación.

modelo: Entidades que representan la lógica del negocio (Socio, Pago, Membresía, Instructor, Clase).

repositorio: Interfaces para la comunicación con la base de datos mediante JPA.

servicio: Capa donde reside toda la lógica de negocio y reglas del sistema.
