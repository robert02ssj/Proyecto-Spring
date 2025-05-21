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
tiendanime
    │   .gitattributes
    │   .gitignore
    │   HELP.md
    │   mvnw
    │   mvnw.cmd
    │   pom.xml
    │
    ├───.mvn
    │   └───wrapper
    │           maven-wrapper.properties
    │
    ├───.vscode
    │       NEWLY_CREATED_BY_SPRING_INITIALIZR
    │
    ├───src
    │   ├───main
    │   │   ├───java
    │   │   │   └───com
    │   │   │       └───ssj
    │   │   │           └───tiendanime
    │   │   │               │   TiendanimeApplication.java
    │   │   │               │
    │   │   │               ├───config
    │   │   │               │       SecurityConfig.java
    │   │   │               │
    │   │   │               ├───controller
    │   │   │               │       AdminController.java
    │   │   │               │       AdminPedidoController.java
    │   │   │               │       AdminProductoController.java
    │   │   │               │       AsistenciaController.java
    │   │   │               │       CarritoController.java
    │   │   │               │       ContactoController.java
    │   │   │               │       CuentaController.java
    │   │   │               │       CustomErrorController.java
    │   │   │               │       HomeController.java
    │   │   │               │       LoginController.java
    │   │   │               │       PedidoController.java
    │   │   │               │       ProductoController.java
    │   │   │               │       RegistroController.java
    │   │   │               │       SobreNosotrosController.java
    │   │   │               │
    │   │   │               ├───model
    │   │   │               │       Categoria.java
    │   │   │               │       DetallePedido.java
    │   │   │               │       ItemCarrito.java
    │   │   │               │       Pedido.java
    │   │   │               │       Producto.java
    │   │   │               │       Usuario.java
    │   │   │               │
    │   │   │               ├───repository
    │   │   │               │       CategoriaRepository.java
    │   │   │               │       DetallePedidoRepository.java
    │   │   │               │       PedidoRepository.java
    │   │   │               │       ProductoRepository.java
    │   │   │               │       UsuarioRepository.java
    │   │   │               │
    │   │   │               ├───security
    │   │   │               │       CustomUserDetailsService.java
    │   │   │               │
    │   │   │               └───service
    │   │   │                       CarritoService.java
    │   │   │                       PedidoService.java
    │   │   │                       ProductoService.java
    │   │   │
    │   │   └───resources
    │   │       │   application.properties
    │   │       │
    │   │       ├───static
    │   │       │   ├───audio
    │   │       │   │       gato.mp3
    │   │       │   │
    │   │       │   ├───css
    │   │       │   │       admin.css
    │   │       │   │       asistencia.css
    │   │       │   │       busqueda.css
    │   │       │   │       carrito.css
    │   │       │   │       contactanos.css
    │   │       │   │       cuenta.css
    │   │       │   │       error.css
    │   │       │   │       form-producto.css
    │   │       │   │       gestionar_pedidos.css
    │   │       │   │       gestionar_productos.css
    │   │       │   │       Index-Style.css
    │   │       │   │       login.css
    │   │       │   │       pedido-detalle-admin.css
    │   │       │   │       pedido-detalle.css
    │   │       │   │       pedidos.css
    │   │       │   │       producto.css
    │   │       │   │       registro.css
    │   │       │   │       sobre-nosotros.css
    │   │       │   │
    │   │       │   ├───img
    │   │       │   │       1.jpg
    │   │       │   │       10.jpg
    │   │       │   │       11.jpg
    │   │       │   │       12.jpg
    │   │       │   │       13.jpg
    │   │       │   │       14.jpg
    │   │       │   │       15.jpg
    │   │       │   │       16.jpg
    │   │       │   │       2.jpg
    │   │       │   │       3.jpg
    │   │       │   │       33.jpg
    │   │       │   │       34.jpg
    │   │       │   │       35.jpg
    │   │       │   │       4.jpg
    │   │       │   │       40.jpg
    │   │       │   │       5.jpg
    │   │       │   │       50.jpg
    │   │       │   │       6.jpg
    │   │       │   │       60.jpg
    │   │       │   │       65.jpg
    │   │       │   │       7.jpg
    │   │       │   │       77.jpg
    │   │       │   │       78.jpg
    │   │       │   │       8.jpg
    │   │       │   │       86.jpg
    │   │       │   │       9.jpg
    │   │       │   │       93.jpg
    │   │       │   │       97.jpg
    │   │       │   │       banner dball.gif
    │   │       │   │       logo.png
    │   │       │   │
    │   │       │   └───js
    │   │       └───templates
    │   │               admin.html
    │   │               asistencia.html
    │   │               busqueda.html
    │   │               cambiar-password.html
    │   │               carrito.html
    │   │               contactanos.html
    │   │               cuenta.html
    │   │               editar-perfil.html
    │   │               error.html
    │   │               form-producto.html
    │   │               gestionar_pedidos.html
    │   │               gestionar_productos.html
    │   │               index.html
    │   │               login.html
    │   │               pedido-detalle-admin.html
    │   │               pedido-detalle.html
    │   │               pedidos.html
    │   │               producto.html
    │   │               registro.html
    │   │               sobre-nosotros.html
    │   │
    │   └───test
    │       └───java
    │           └───com
    │               └───ssj
    │                   └───tiendanime
    │                           TiendanimeApplicationTests.java
    │
    └───target
        │   tiendanime-0.0.1-SNAPSHOT.jar
        │   tiendanime-0.0.1-SNAPSHOT.jar.original
        │
        ├───classes
        │   │   application.properties
        │   │
        │   ├───com
        │   │   └───ssj
        │   │       └───tiendanime
        │   │           │   TiendanimeApplication.class
        │   │           │
        │   │           ├───config
        │   │           │       SecurityConfig.class
        │   │           │
        │   │           ├───controller
        │   │           │       AdminController.class
        │   │           │       AdminPedidoController.class
        │   │           │       AdminProductoController.class
        │   │           │       AsistenciaController.class
        │   │           │       CarritoController.class
        │   │           │       ContactoController.class
        │   │           │       CuentaController.class
        │   │           │       CustomErrorController.class
        │   │           │       HomeController.class
        │   │           │       LoginController.class
        │   │           │       PedidoController.class
        │   │           │       ProductoController.class
        │   │           │       RegistroController.class
        │   │           │       SobreNosotrosController.class
        │   │           │
        │   │           ├───model
        │   │           │       Categoria.class
        │   │           │       DetallePedido.class
        │   │           │       ItemCarrito.class
        │   │           │       Pedido.class
        │   │           │       Producto.class
        │   │           │       Usuario.class
        │   │           │
        │   │           ├───repository
        │   │           │       CategoriaRepository.class
        │   │           │       DetallePedidoRepository.class
        │   │           │       PedidoRepository.class
        │   │           │       ProductoRepository.class
        │   │           │       UsuarioRepository.class
        │   │           │
        │   │           ├───security
        │   │           │       CustomUserDetailsService.class
        │   │           │
        │   │           └───service
        │   │                   CarritoService.class
        │   │                   PedidoService.class
        │   │                   ProductoService.class
        │   │
        │   ├───static
        │   │   ├───audio
        │   │   │       gato.mp3
        │   │   │
        │   │   ├───css
        │   │   │       admin.css
        │   │   │       asistencia.css
        │   │   │       busqueda.css
        │   │   │       carrito.css
        │   │   │       contactanos.css
        │   │   │       cuenta.css
        │   │   │       error.css
        │   │   │       form-producto.css
        │   │   │       gestionar_pedidos.css
        │   │   │       gestionar_productos.css
        │   │   │       Index-Style.css
        │   │   │       login.css
        │   │   │       pedido-detalle-admin.css
        │   │   │       pedido-detalle.css
        │   │   │       pedidos.css
        │   │   │       producto.css
        │   │   │       registro.css
        │   │   │       sobre-nosotros.css
        │   │   │
        │   │   └───img
        │   │           1.jpg
        │   │           10.jpg
        │   │           11.jpg
        │   │           12.jpg
        │   │           13.jpg
        │   │           14.jpg
        │   │           15.jpg
        │   │           16.jpg
        │   │           2.jpg
        │   │           3.jpg
        │   │           33.jpg
        │   │           34.jpg
        │   │           35.jpg
        │   │           4.jpg
        │   │           40.jpg
        │   │           5.jpg
        │   │           50.jpg
        │   │           6.jpg
        │   │           60.jpg
        │   │           65.jpg
        │   │           7.jpg
        │   │           77.jpg
        │   │           78.jpg
        │   │           8.jpg
        │   │           86.jpg
        │   │           9.jpg
        │   │           93.jpg
        │   │           97.jpg
        │   │           banner dball.gif
        │   │           logo.png
        │   │
        │   └───templates
        │           admin.html
        │           asistencia.html
        │           busqueda.html
        │           cambiar-password.html
        │           carrito.html
        │           contactanos.html
        │           cuenta.html
        │           editar-perfil.html
        │           error.html
        │           form-producto.html
        │           gestionar_pedidos.html
        │           gestionar_productos.html
        │           index.html
        │           login.html
        │           pedido-detalle-admin.html
        │           pedido-detalle.html
        │           pedidos.html
        │           producto.html
        │           registro.html
        │           sobre-nosotros.html
        │
        ├───generated-sources
        │   └───annotations
        ├───generated-test-sources
        │   └───test-annotations
        ├───maven-archiver
        │       pom.properties
        │
        ├───maven-status
        │   └───maven-compiler-plugin
        │       ├───compile
        │       │   └───default-compile
        │       │           createdFiles.lst
        │       │           inputFiles.lst
        │       │
        │       └───testCompile
        │           └───default-testCompile
        │                   createdFiles.lst
        │                   inputFiles.lst
        │
        ├───surefire-reports
        │       com.ssj.tiendanime.TiendanimeApplicationTests.txt
        │       TEST-com.ssj.tiendanime.TiendanimeApplicationTests.xml
        │
        └───test-classes
            └───com
                └───ssj
                    └───tiendanime
                            TiendanimeApplicationTests.class
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
