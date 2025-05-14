package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class HomeController {

    private final ProductoService productoService;
    private final UsuarioRepository usuarioRepository;

    public HomeController(ProductoService productoService, UsuarioRepository usuarioRepository) {
        this.productoService = productoService;
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/")
    public String home(Model model, Principal principal) {
        model.addAttribute("productos", productoService.obtenerProductosMasVisitados());
        model.addAttribute("aleatorios", productoService.obtenerProductosAleatorios(50)); // <-- Añade esta línea

        
        if (principal != null) {
            Usuario usuario = usuarioRepository.findByEmail(principal.getName());
            if (usuario != null) {
                model.addAttribute("nombreUsuario", usuario.getNombre());
            }
        }
        return "index";
    }
}
