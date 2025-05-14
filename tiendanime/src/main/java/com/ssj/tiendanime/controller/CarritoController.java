package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.ItemCarrito;
import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.service.ProductoService;
import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.service.CarritoService;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class CarritoController {
    private final ProductoService productoService;
    private final CarritoService carritoService;
    private final UsuarioRepository usuarioRepository;

    public CarritoController(ProductoService productoService, CarritoService carritoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.carritoService = carritoService;
        this.usuarioRepository = usuarioRepository;
    }

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

    @PostMapping("/carrito/agregar")
    public String agregarAlCarrito(@RequestParam("id_producto") Integer idProducto,
                                   @RequestParam("cantidad") Integer cantidad,
                                   Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        carritoService.agregarProductoAlCarrito(carrito, idProducto, cantidad);
        return "redirect:/carrito";
    }

    @PostMapping("/carrito/eliminar")
    public String eliminarDelCarrito(@RequestParam("id_producto") Integer idProducto, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        carritoService.eliminarProductoDelCarrito(carrito, idProducto);
        return "redirect:/carrito";
    }

    @PostMapping("/pedido/confirmar")
    public String confirmarPedido(Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        Pedido carrito = carritoService.obtenerOCrearCarrito(usuario);
        carritoService.confirmarCarrito(carrito);
        return "redirect:/";
    }
}
