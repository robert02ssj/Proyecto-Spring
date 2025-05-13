package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Devuelve los 5 productos con más visitas
    List<Producto> findTop5ByOrderByVisitasDesc();
}
