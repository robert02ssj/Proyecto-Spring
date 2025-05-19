package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Categoria;
import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.repository.CategoriaRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    // Listar todos los productos
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "gestionar_productos";
    }

    // Mostrar formulario para crear producto
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "form-producto";
    }

    // Mostrar formulario para editar producto
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "form-producto";
    }

    // Guardar o actualizar producto
    @PostMapping("/guardar")
    public String guardarProducto(@ModelAttribute Producto producto) {
        Categoria categoria = categoriaRepository.findById(producto.getCategoria().getId_categoria())
                .orElseThrow(() -> new IllegalArgumentException("Categoría no válida"));
        producto.setCategoria(categoria);
        productoService.guardar(producto);
        return "redirect:/admin/productos";
    }

    // Eliminar producto
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.borrar(id);
        return "redirect:/admin/productos";
    }

    
}