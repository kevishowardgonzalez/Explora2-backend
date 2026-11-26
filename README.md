# Explora2 — Aplicación Móvil Android y Backend Spring Boot

Proyecto desarrollado para **2.° Básico** (**Explora2**), compuesto por una aplicación móvil en Android (Jetpack Compose / Kotlin) y un microservicio backend en **Spring Boot** conectado a **MySQL (XAMPP)** para la gestión de tareas (`Tarea`).

## Integrantes
- Javier Cruz
- Kevis Gonzalez

## Arquitectura y Alcance
- **App Android (`Explora2`):** Interfaz con Material 3, navegación estructurada, gestión de estado, almacenamiento local y consumo de APIs.
- **Microservicio Backend (`explora2-backend`):** API REST en Spring Boot (`com.explora2.explora2_backend`) con JPA/Hibernate y base de datos MySQL en XAMPP (`explora2_db`).

## Endpoints Disponibles (`/api/tareas`)
Base URL local: `http://localhost:8080/api/tareas` (desde el emulador Android: `http://10.0.2.2:8080/api/tareas`)

- `GET /api/tareas` — Lista todas las tareas registradas.
- `POST /api/tareas` — Crea una nueva tarea en formato JSON (`titulo`, `asignatura`, `descripcion`, `entregada`).
- `PUT /api/tareas/{id}` — Actualiza una tarea existente por `id`.
- `DELETE /api/tareas/{id}` — Elimina una tarea por `id`.

## Requisitos Previos
- **JDK 17** o **21** instalado.
- **XAMPP** con el servicio **MySQL** activo.
- **Android Studio** (con emulador Android o dispositivo configurado).

## Pasos para Ejecutar

### 1. Levantar el Backend (Spring Boot + MySQL en XAMPP)
1. Abre **XAMPP Control Panel** e inicia **MySQL**.
2. Abre el proyecto backend y revisa `src/main/resources/application.properties` (configurado con `createDatabaseIfNotExist=true` y `spring.jpa.hibernate.ddl-auto=update` para `explora2_db`).
3. Ejecuta la clase principal `Explora2BackendApplication` (o corre `.\gradlew.bat bootRun` en Windows / `./gradlew bootRun` en Mac/Linux).
4. Verifica que el servidor inicie en el puerto `8080` visitando `http://localhost:8080/api/tareas`.

### 2. Ejecutar la App Android
1. Abre el proyecto Android en **Android Studio**.
2. Sincroniza Gradle y asegúrate de tener el backend corriendo en el puerto `8080` si probarás las operaciones CRUD conectadas.
3. Ejecuta la aplicación en el emulador o dispositivo.