# Tienda Anime

Proyecto de tienda online de productos de anime realizado en Java con Spring Boot, Thymeleaf y JPA/Hibernate.

## Características

- Registro y login de usuarios
- Gestión de productos (CRUD) para administradores
- Carrito de la compra y pedidos
- Pasarela de pago simulada
- Gestión de stock automática
- Panel de administración para productos y pedidos
- Páginas de error personalizadas
- Diseño responsive y moderno con CSS personalizado

## Estructura del proyecto

```
tiendanime/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/ssj/tiendanime/
│   │   │       ├── controller/
│   │   │       ├── model/
│   │   │       ├── repository/
│   │   │       ├── service/
│   │   │       └── config/
│   │   ├── resources/
│   │   │   ├── static/
│   │   │   │   └── css/
│   │   │   └── templates/
│   │   │       ├── *.html
│   │   │       └── error.html
│   └── test/
├── pom.xml
└── README.md
```

## Tecnologías utilizadas

- Java 17+
- Spring Boot
- Spring Security
- Spring Data JPA (Hibernate)
- Thymeleaf
- H2/MySQL (según configuración)
- CSS personalizado

## Instalación y ejecución

1. **Clona el repositorio:**
   ```bash
   git clone https://github.com/robert02ssj/Proyecto-Spring.git
   ```

2. **Configura la base de datos**  
   Edita `src/main/resources/application.properties` para tu motor de base de datos (H2/MySQL).

3. **Ejecuta la aplicación:**
   ```bash
   ./mvnw spring-boot:run
   ```
   o desde tu IDE favorito.

4. **Accede a la web:**  
   [http://localhost:8081](http://localhost:8081)

## Usuarios de prueba

- **Admin:**  
  Usuario: `root`  
  Contraseña: ``  
  (o el que hayas configurado en la base de datos)

- **Usuario normal:**  
  Regístrate desde la web.

## Funcionalidades principales

- **Usuarios:** Registro, login, edición de perfil, cambio de contraseña.
- **Productos:** Listado, búsqueda, detalle, añadir al carrito.
- **Carrito:** Añadir/eliminar productos, confirmar pedido.
- **Pedidos:** Visualización de pedidos propios, detalle de pedido.
- **Admin:** Gestión de productos y pedidos, cambio de estado, eliminación.

## Estructura de carpetas destacada

- `controller/` — Controladores MVC de la aplicación.
- `model/` — Entidades JPA.
- `repository/` — Interfaces de acceso a datos.
- `service/` — Lógica de negocio.
- `templates/` — Vistas Thymeleaf.
- `static/css/` — Hojas de estilo CSS.

## Personalización

- Puedes modificar los estilos en `src/main/resources/static/css/`.
- Las vistas están en `src/main/resources/templates/`.
- Para añadir productos o categorías, puedes hacerlo desde el panel de administración.

## Licencia

Proyecto educativo para prácticas de Programación de 1º DAW.  
Puedes usarlo y modificarlo libremente.

---

**¡Gracias por visitar Tienda Anime!**
