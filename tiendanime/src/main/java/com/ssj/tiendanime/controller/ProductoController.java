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

@Controller
public class ProductoController {

    private final ProductoService productoService;
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoService productoService , ProductoRepository productoRepository) {
        this.productoService = productoService;
        this.productoRepository = productoRepository;
    }

    @GetMapping("/producto/{id}")
    public String detalleProducto(@PathVariable("id") Integer id, Model model) {
        Producto producto = productoService.obtenerPorId(id);
        producto.setVisitas(producto.getVisitas() + 1);
        productoRepository.save(producto);
        model.addAttribute("producto", producto);
        return "producto";
    }

    @GetMapping("/buscar")
public String buscarProductos(@RequestParam("q") String consulta, Model model) {
    List<Producto> resultados = productoRepository.findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(consulta, consulta);
    model.addAttribute("productos", resultados);
    model.addAttribute("busqueda", consulta);
    return "busqueda";
}
}
