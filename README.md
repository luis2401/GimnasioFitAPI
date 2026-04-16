# GimnasioFitAPI - Sistema Gestión de Gimnasios
Esta API REST permite gestionar el ciclo de vida completo de un gimnasio: desde la oferta de planes y membresías hasta el control de instructores, clases y pagos de socios.

## 🚀 Tecnologías y Herramientas

* **Lenguaje:** Java 17+
* **Framework:** Spring Boot 4.0.5
* **Acceso a Datos:** Spring Data JPA / Hibernate
* **Gestión de Dependencias:** Maven
* **Base de Datos:** H2 Database (Persistencia relacional en archivo local)
* **Arquitectura:** Patrón de capas (Controlador, Servicio, Repositorio, Modelo, DTO)
* **Documentación y Pruebas:** Postman

## 🛠️ Modelado del Negocio (Entidades)

La API gestiona cuatro pilares fundamentales interconectados:

1. **Membresías:** Configuración de planes, precios y duraciones.
2. **Socios:** Gestión de datos personales vinculados a una membresía activa.
3. **Instructores:** Registro de personal especializado por disciplina.
4. **Clases y Pagos:** Control de la oferta deportiva y seguimiento financiero mediante DTOs.

## 📈 Características Técnicas Destacadas

* **Mapeo Relacional Avanzado:** Uso de relaciones @OneToMany y @ManyToOne para garantizar la integridad referencial.
* **Transformación de Datos:** Implementación de DTOs con Java Stream API para optimizar el consumo de la API.
* **Lógica de Persistencia:** Sistema configurado para almacenamiento persistente en base de datos física.
* **Separación de Responsabilidades:** Arquitectura limpia diseñada para facilitar el escalamiento (ej. añadir seguridad JWT o Testing).

## 📂 Estructura de Paquetes

```text
src/main/java/com/gimnasio/fit/api
├── controlador/    # Endpoints REST
├── dto/            # DTO
├── modelo/         # Socio, Membresia, Instructor, Pago, Clase
├── repositorio/    # Persistencia JPA
└── servicios/      # Lógica transaccional

Archivo de configuración (application.properties):
En la primera ejecucion se recomienda cambiar "spring.jpa.hibernate.ddl-auto=create", luego para uso continuo cambiarlo a update.
spring.datasource.url=jdbc:h2:file:./data/gymdb
spring.h2.console.enabled=true
spring.jpa.hibernate.ddl-auto=update
