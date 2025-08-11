# 📚 Literalura - Catálogo de Libros

Literalura es una aplicación Java con Spring Boot que interactúa con la API de [Gutendex](https://gutendex.com/) para buscar, registrar y consultar libros y autores.  
El proyecto incluye funcionalidades para guardar información en una base de datos y realizar consultas utilizando **Streams**, **Lambdas** y **Spring Data JPA**.

---

## 🚀 Funcionalidades

- Buscar libros en la API de Gutendex.
- Guardar libros y autores en una base de datos.
- Evitar duplicados de autores y libros.
- Consultar cantidad de libros por idioma.
- Mostrar estadísticas con Streams y Lambdas.
- Manejo de autores desconocidos.

---

## 🛠️ Tecnologías Utilizadas

- **Java 24**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Gutendex API**
- **Maven**

---

## 📦 Instalación

1. **Clonar el repositorio**
   ```bash
   git clone https://github.com/tuusuario/literalura.git
   cd literalura
   ```

2. **Configurar la base de datos**  
   Edita el archivo `application.properties` para definir el motor de base de datos y credenciales.

3. **Compilar y ejecutar**
   ```bash
   mvn spring-boot:run
   ```

---

## 📂 Estructura del Proyecto

```
src/main/java/com/challenge/literalura/
│
├── model/           # Entidades (Libro, Autor, DatosLibro...)
├── repository/      # Interfaces de repositorio (Spring Data JPA)
├── service/         # Lógica de negocio
├── principal/       # Menú principal y flujo de la app
└── LiteraluraApp.java
```

---
