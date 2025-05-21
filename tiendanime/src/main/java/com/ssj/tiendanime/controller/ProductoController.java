package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.service.ProductoService;
import com.ssj.tiendanime.repository.ProductoRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controlador para la gestión de productos en la tienda.
 * Permite ver el detalle de un producto y realizar búsquedas.
 */
@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyectar las dependencias necesarias.
     * @param productoService Servicio de productos.
     * @param productoRepository Repositorio de productos.
     */
    public ProductoController(ProductoService productoService , ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    /**
     * Muestra el detalle de un producto y suma una visita al contador.
     * @param id ID del producto a mostrar.
     * @param model Modelo para la vista.
     * @return Vista con el detalle del producto.
     */
    @GetMapping("/producto/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        producto.setVisitas(producto.getVisitas() + 1);
        productoRepository.save(producto);
        model.addAttribute("producto", producto);
        return "producto";
    }

    /**
     * Busca productos por nombre o descripción que contengan la consulta.
     * @param consulta Texto de búsqueda introducido por el usuario.
     * @param model Modelo para la vista.
     * @return Vista con los resultados de la búsqueda.
     */
    @GetMapping("/buscar")
    public String buscarProductos(@RequestParam("q") String consulta, Model model) {
        List<Producto> resultados = productoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(consulta, consulta);
        model.addAttribute("productos", resultados);
        model.addAttribute("busqueda", consulta);
        return "busqueda";
    }
}