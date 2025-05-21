package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.repository.CategoriaRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.security.Principal;

@Controller
public class AdminController {

    private final UsuarioRepository usuarioRepository;
    private final CategoriaRepository categoriaRepository;
    private final ProductoService productoService;


    
    public AdminController(UsuarioRepository usuarioRepository, ProductoService productoService, CategoriaRepository categoriaRepository) {
        this.usuarioRepository = usuarioRepository;
        this.categoriaRepository = categoriaRepository;
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
public String guardarProducto(@ModelAttribute Producto producto,
                             @RequestParam(value = "imagenArchivo", required = false) MultipartFile imagenArchivo,
                             Model model) throws IOException {
    if (imagenArchivo != null && !imagenArchivo.isEmpty()) {
        String nombreArchivo = imagenArchivo.getOriginalFilename();
        String ruta = new File("tiendanime\\src\\main\\resources\\static\\img").getAbsolutePath();
        File carpetaImg = new File(ruta);
        if (!carpetaImg.exists()) {
            carpetaImg.mkdirs();
        }
        File archivoDestino = new File(ruta + "/" + nombreArchivo);
        imagenArchivo.transferTo(archivoDestino);
        producto.setImagenUrl(nombreArchivo);
    } else if (producto.getId_producto() != null) {
        Producto productoExistente = productoService.obtenerPorId(producto.getId_producto());
        if (productoExistente != null) {
            producto.setImagenUrl(productoExistente.getImagenUrl());
        }
    }
    productoService.guardar(producto);

    // Recarga el producto actualizado y añade el mensaje
    Producto productoActualizado = productoService.obtenerPorId(producto.getId_producto());
    model.addAttribute("producto", productoActualizado);
    model.addAttribute("categorias", categoriaRepository.findAll());
    model.addAttribute("mensaje", "Producto actualizado correctamente.");
    return "form-producto";
}

    @GetMapping("/admin/producto/borrar/{id}")
    public String borrarProducto(@PathVariable Integer id) {
        productoService.borrar(id);
        return "redirect:/admin/productos";
    }
}
