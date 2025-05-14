package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final ProductoService productoService;

    public AdminController(UsuarioRepository usuarioRepository, ProductoService productoService) {
        this.usuarioRepository = usuarioRepository;
        this.productoService = productoService;
    }

    @GetMapping("/admin")
    public String adminPanel(Model model, Principal principal) {
        if (principal == null) {
            return "redirect:/login";
        }
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        if (usuario == null || !"ADMIN".equalsIgnoreCase(usuario.getNombre())) {
            return "redirect:/";
        }
        model.addAttribute("productos", productoService.obtenerTodos());
        return "admin";
    }
    @GetMapping("/admin/producto/nuevo")
    public String nuevoProducto(Model model, Principal principal) {
        model.addAttribute("producto", new Producto());
        return "form-producto";
    }

    @GetMapping("/admin/producto/editar/{id}")
    public String editarProducto(@PathVariable Integer id, Model model, Principal principal) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        return "form-producto";
    }

    @PostMapping("/admin/producto/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        productoService.guardar(producto);
        return "redirect:/admin";
    }

    @GetMapping("/admin/producto/borrar/{id}")
    public String borrarProducto(@PathVariable Integer id) {
        productoService.borrar(id);
        return "redirect:/admin";
    }
}
