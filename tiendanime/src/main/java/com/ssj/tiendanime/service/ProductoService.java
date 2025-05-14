package com.ssj.tiendanime.service;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    public List<Producto> obtenerProductosMasVisitados() {
        return productoRepository.findTop5ByOrderByVisitasDesc();
    }
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    public List<Producto> obtenerProductosAleatorios(int cantidad) {
    List<Producto> todos = productoRepository.findAll();
    Collections.shuffle(todos);
    return todos.stream().limit(cantidad).toList();
}
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    public void borrar(Integer id) {
        productoRepository.deleteById(id);
    }
}
