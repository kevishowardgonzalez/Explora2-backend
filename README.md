# Explora2 · Backend pedagógico

Javier Cruz y Kevis Gonzalez · Desarrollo de Aplicaciones Móviles DSY1105, Duoc UC.

Este servicio gestiona las planificaciones de segundo básico y la configuración global del examen. Los controladores reciben JSON, los servicios aplican la lógica y los repositorios JPA persisten las entidades en XAMPP. El servicio está integrado con la aplicación Android mediante Retrofit, permitiendo la gestión remota de planificaciones, autorización por UTP, asignación de actividades, entrega de respuestas y configuración de evaluaciones.

## Ejecución en Windows

1. Usa un **JDK 21** para el backend. Comprueba `java -version` y `.\gradlew.bat --version` desde este repositorio. El wrapper incluido usa Gradle 9.9.2. Para seleccionar otro JDK solo en esta consola, asigna su directorio a `$env:JAVA_HOME`. Android tiene su propia configuración de Java 17.
2. En **XAMPP Control Panel**, pulsa **Start** en la fila **MySQL** y comprueba que escucha en **3306**. En el equipo de verificación se utilizó MariaDB 10.4.32, distribuido con XAMPP. Apache no es necesario para servir la API.
3. Revisa `src/main/resources/application.properties`. La URL JDBC apunta a `localhost:3306/explora2_db` con `createDatabaseIfNotExist=true`. Hibernate usa `ddl-auto=update`. La cuenta local necesita permisos para crear el esquema si falta, actualizar sus tablas y operar sobre ellas. Si tu entorno requiere otras credenciales, suministra `SPRING_DATASOURCE_USERNAME` y `SPRING_DATASOURCE_PASSWORD` en la sesión de ejecución.
4. Con XAMPP activo, ejecuta `.\gradlew.bat test` y luego `.\gradlew.bat bootRun`. Para arrancar el ejecutable empaquetado, usa `.\gradlew.bat bootJar` y `java -jar build/libs/explora2-backend-0.0.1-SNAPSHOT.jar`. Espera el mensaje de arranque de Explora2BackendApplication.
5. Abre la lista de planificaciones. Una colección vacía devuelve HTTP 200 y `[]`. Para detener el backend en esa consola, pulsa Ctrl+C. [Planificaciones locales](http://localhost:8089/api/planificaciones)

| Acceso | URL |
|---|---|
| Base desde el computador | [http://localhost:8089/](http://localhost:8089/) |
| Base desde el emulador Android | [http://10.0.2.2:8089/](http://10.0.2.2:8089/) |
| Swagger UI | [Interfaz interactiva](http://localhost:8089/swagger-ui/index.html) |
| Especificación OpenAPI | [JSON](http://localhost:8089/v3/api-docs) |

En el emulador, 10.0.2.2 dirige la petición al computador anfitrión. Las rutas de ambos recursos empiezan por `api/`.

## Operaciones HTTP

Los cuerpos de escritura usan `Content-Type: application/json`. Los identificadores son enteros de 64 bits generados por la BD.

| Método y ruta | Respuesta |
|---|---|
| GET /api/planificaciones | 200, arreglo de planificaciones. |
| POST /api/planificaciones | 201, objeto guardado con su id. Omitir id en el cuerpo. |
| GET /api/planificaciones/{id} | 200, objeto. 404 si el id no existe. |
| PUT /api/planificaciones/{id} | 200, objeto actualizado. El servicio busca por el id de la ruta y copia todos los campos del cuerpo. La búsqueda usa 404 para id ausente. |
| DELETE /api/planificaciones/{id} | 204, cuerpo vacío. El servicio usa 404 para id ausente. |
| GET /api/configuracion-examen | 200, arreglo. La lectura inicial puede devolver `[]`. |
| PUT /api/configuracion-examen | 200, un objeto con id tanto al crear como al actualizar. Sin id en la ruta. Si hay filas, reutiliza la primera que devuelve el repositorio. |

### Planificacion

Ejemplo de respuesta tras crear una planificación ficticia. Para POST o PUT se envían los mismos campos de negocio y se omite id. Para PUT, enviar el objeto completo a la ruta con el id devuelto por POST.

```json
{
  "id": 1,
  "asignatura": "Lenguaje",
  "unidad": "Unidad ficticia",
  "objetivo": "OA lectura",
  "tipoRecurso": "Texto",
  "titulo": "DSY1105-EJEMPLO",
  "descripcion": "Lectura inicial",
  "validado": true,
  "enviadoUtp": true,
  "autorizado": false,
  "asignadoAlumno": false,
  "respuestaEntregada": false,
  "respuestaAlumno": ""
}
```

### ConfiguracionExamen

Ejemplo de respuesta del PUT. El cuerpo de solicitud contiene intentosPermitidos, fechaInicio, fechaFin y version. El GET envuelve los objetos en un arreglo.

```json
{
  "id": 1,
  "intentosPermitidos": 2,
  "fechaInicio": "01/11/2026",
  "fechaFin": "15/11/2026",
  "version": "DSY1105-EJEMPLO-A"
}
```

### Correspondencia con DTO Kotlin

| Recurso | Atributos JSON | Java en la entidad | Kotlin previsto |
|---|---|---|---|
| Ambos | id | Long, identidad JPA | Long para una fila persistida. Omitir en solicitudes o usar Long? si se comparte la clase. |
| Planificacion | asignatura, unidad, objetivo, tipoRecurso, titulo | String | String? al leer valores que la BD admite como nulos. En escritura enviar texto explícito. |
| Planificacion | descripcion, respuestaAlumno | String, columnas de longitud 1000 | String? al leer. Para una respuesta aún vacía se puede enviar la cadena vacía. |
| Planificacion | validado, enviadoUtp, autorizado, asignadoAlumno, respuestaEntregada | boolean | Boolean en cada atributo. |
| ConfiguracionExamen | intentosPermitidos | int | Int. |
| ConfiguracionExamen | fechaInicio, fechaFin, version | String | String? al leer. Las fechas son texto; los ejemplos usan día/mes/año. |

Mapear los GET de colección a `List<PlanificacionDto>` o `List<ConfiguracionExamenDto>`. En GET por id, POST y PUT se recibe un objeto. En DELETE no se intenta decodificar JSON. El id 1 de los ejemplos es ilustrativo; usar siempre el devuelto por el servidor.

## Límites observados

- Los cuerpos se enlazan directamente a entidades. No hay validación explícita de campos obligatorios, intentos positivos, formato ni orden de fechas. Las banderas de autorización y entrega llegan del cliente. Una edición parcial con PUT puede borrar textos o reiniciar banderas.
- La configuración se trata como global. GET devuelve una lista y el primer PUT crea una fila. La selección posterior carece de orden definido y la BD no impone una fila única. Dos primeras escrituras simultáneas podrían crear dos configuraciones.
- Los controladores permiten CORS de cualquier origen. El servicio está preparado para el laboratorio local. Hibernate comunicó la versión compatible como 5.5.5 con MySQLDialect sobre MariaDB 10.4.32 y emitió una advertencia de compatibilidad. Las operaciones de esta fase funcionaron.
- Para repetir el recorrido con el cliente REST de VS Code, usa [api-pruebas.http](api-pruebas.http). La configuración es compartida: los ejemplos de escritura deben ejecutarse en una base de prácticas vacía o con datos propios autorizados.
