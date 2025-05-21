package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

/**
 * Controlador para la página principal de la tienda.
 * Muestra productos destacados, productos aleatorios y el nombre del usuario si está autenticado.
 */
@Controller
public class HomeController {

    private final ProductoService productoService;
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para inyectar las dependencias necesarias.
     * @param productoService Servicio de productos.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public HomeController(ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Muestra la página principal con productos destacados y aleatorios.
     * Si el usuario está autenticado, añade su nombre al modelo.
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado (puede ser null si no hay sesión).
     * @return Vista "index" de la página principal.
     */
    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("productos", productoService.obtenerProductosMasVisitados());
        model.addAttribute("aleatorios", productoService.obtenerProductosAleatorios(50));

        if (principal != null) {
            Usuario usuario = usuarioRepository.findByEmail(principal.getName());
            if (usuario != null) {
                model.addAttribute("nombreUsuario", usuario.getNombre());
            }
        }
        return "index";
    }
}