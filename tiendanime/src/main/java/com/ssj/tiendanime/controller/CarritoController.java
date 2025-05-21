package com.ssj.tiendanime.controller;
import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.service.CarritoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.List;

/**
 * Controlador para la gestión del carrito de compras y el proceso de pago.
 * Permite ver el carrito, agregar/eliminar productos, confirmar el pedido y procesar el pago.
 */
@Controller
public class CarritoController {
    private final CarritoService carritoService;
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para inyectar las dependencias necesarias.
     * @param carritoService Servicio del carrito de compras.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public CarritoController(CarritoService carritoService, UsuarioRepository usuarioRepository) {
        this.carritoService = carritoService;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Muestra el contenido del carrito de compras del usuario autenticado.
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado.
     * @return Vista del carrito.
     */
    @GetMapping("/carrito")
    public String verCarrito(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        List<DetallePedido> detalles = carritoService.obtenerDetallesCarrito(carrito);

        BigDecimal total = detalles.stream()
            .map(item -> item.getProducto().getPrecio().multiply(BigDecimal.valueOf(item.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);

        model.addAttribute("carrito", detalles);
        model.addAttribute("total", total);
        return "carrito";
    }

    /**
     * Muestra el formulario de la pasarela de pago.
     * @return Vista del formulario de pago.
     */
    @GetMapping("/pago")
    public String mostrarFormularioPago() {
        return "pago";
    }

    /**
     * Agrega un producto al carrito del usuario autenticado.
     * @param idProducto ID del producto a agregar.
     * @param cantidad Cantidad del producto a agregar.
     * @param principal Usuario autenticado.
     * @return Redirección al carrito.
     */
    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("id_producto") Integer idProducto,
                                   @RequestParam("cantidad") Integer cantidad,
                                   Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        carritoService.agregarProductoAlCarrito(carrito, idProducto, cantidad);
        return "redirect:/carrito";
    }

    /**
     * Elimina un producto del carrito del usuario autenticado.
     * @param idProducto ID del producto a eliminar.
     * @param principal Usuario autenticado.
     * @return Redirección al carrito.
     */
    @PostMapping("/carrito/eliminar")
    public String eliminarDelCarrito(@RequestParam("id_producto") Integer idProducto, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        carritoService.eliminarProductoDelCarrito(carrito, idProducto);
        return "redirect:/carrito";
    }

    /**
     * Confirma el pedido y redirige al formulario de pago.
     * @param principal Usuario autenticado.
     * @return Redirección a la pasarela de pago.
     */
    @PostMapping("/pedido/confirmar")
    public String confirmarPedido(Principal principal) {
        return "redirect:/pago";
    }

    /**
     * Procesa el pago del pedido y confirma el carrito.
     * @param nombre Nombre del titular de la tarjeta.
     * @param tarjeta Número de la tarjeta.
     * @param caducidad Fecha de caducidad de la tarjeta.
     * @param cvc Código de seguridad de la tarjeta.
     * @param principal Usuario autenticado.
     * @return Redirección a la lista de pedidos con mensaje de éxito.
     */
    @PostMapping("/pago/procesar")
    public String procesarPago(@RequestParam String nombre,
                               @RequestParam String tarjeta,
                               @RequestParam String caducidad,
                               @RequestParam String cvc,
                               Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        // Aquí puedes guardar los datos de pago/envío si quieres
        carritoService.confirmarCarrito(carrito); // Ahora sí, confirma el pedido
        return "redirect:/pedidos?exito";
    }
}