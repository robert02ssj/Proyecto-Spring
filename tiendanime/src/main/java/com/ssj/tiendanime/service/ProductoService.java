package com.ssj.tiendanime.service;

import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.repository.ProductoRepository;
import org.springframework.stereotype.Service;
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
}
