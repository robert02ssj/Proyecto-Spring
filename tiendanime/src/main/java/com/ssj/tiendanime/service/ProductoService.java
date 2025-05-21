package com.ssj.tiendanime.service;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Servicio para la gestión de productos.
 * Permite obtener productos destacados, aleatorios, todos, guardar y borrar productos.
 */
@Service
public class ProductoService {

    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyectar el repositorio de productos.
     * @param productoRepository Repositorio de productos.
     */
    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene los 5 productos más visitados.
     * @return Lista de productos más visitados.
     */
    public List<Producto> obtenerProductosMasVisitados() {
        return productoRepository.findTop5ByOrderByVisitasDesc();
    }

    /**
     * Obtiene un producto por su ID.
     * @param id ID del producto.
     * @return Producto encontrado o null si no existe.
     */
    public Producto obtenerPorId(Integer id) {
        return productoRepository.findById(id).orElse(null);
    }

    /**
     * Obtiene una lista de productos aleatorios.
     * @param cantidad Número de productos a devolver.
     * @return Lista de productos aleatorios.
     */
    public List<Producto> obtenerProductosAleatorios(int cantidad) {
        List<Producto> todos = productoRepository.findAll();
        Collections.shuffle(todos);
        return todos.stream().limit(cantidad).toList();
    }

    /**
     * Obtiene todos los productos.
     * @return Lista de todos los productos.
     */
    public List<Producto> obtenerTodos() {
        return productoRepository.findAll();
    }

    /**
     * Guarda un producto en la base de datos.
     * @param producto Producto a guardar.
     */
    public void guardar(Producto producto) {
        productoRepository.save(producto);
    }

    /**
     * Elimina un producto por su ID.
     * @param id ID del producto a eliminar.
     */
    public void borrar(Integer id) {
        productoRepository.deleteById(id);
    }
}