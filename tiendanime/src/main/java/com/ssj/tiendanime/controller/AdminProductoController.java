package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.repository.CategoriaRepository;
import com.ssj.tiendanime.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para la gestión administrativa de productos.
 * Permite listar, crear, editar, guardar y eliminar productos desde el panel de administración.
 */
@Controller
@RequestMapping("/admin/productos")
public class AdminProductoController {

    @Autowired
    private ProductoService productoService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    /**
     * Lista todos los productos disponibles.
     * @param model Modelo para la vista.
     * @return Vista de gestión de productos.
     */
    @GetMapping
    public String listarProductos(Model model) {
        model.addAttribute("productos", productoService.obtenerTodos());
        return "gestionar_productos";
    }

    /**
     * Muestra el formulario para crear un nuevo producto.
     * @param model Modelo para la vista.
     * @return Vista del formulario de producto.
     */
    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("producto", new Producto());
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "form-producto";
    }

    /**
     * Muestra el formulario para editar un producto existente.
     * @param id ID del producto a editar.
     * @param model Modelo para la vista.
     * @return Vista del formulario de producto con los datos cargados.
     */
    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("categorias", categoriaRepository.findAll());
        return "form-producto";
    }

    // Aquí puedes añadir los métodos de guardar producto y otros, con sus respectivos JavaDoc.

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar.
     * @return Redirección a la lista de productos.
     */
    @PostMapping("/eliminar/{id}")
    public String eliminarProducto(@PathVariable("id") Integer id) {
        productoService.borrar(id);
        return "redirect:/admin/productos";
    }
}