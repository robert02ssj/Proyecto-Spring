package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Interfaz que define el repositorio para la entidad Producto.
 * Extiende JpaRepository para proporcionar operaciones CRUD.
 * Además, incluye métodos específicos para buscar productos por nombre o descripción.
 */
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    /**
     * Busca los 5 productos más visitados.
     * @return Una lista de los 5 productos más visitados.
     */
    List<Producto> findTop5ByOrderByVisitasDesc();

    /**
     * Busca productos por nombre o descripción que contengan la consulta.
     *
     * @param nombre      El nombre del producto a buscar.
     * @param descripcion La descripción del producto a buscar.
     * @return Una lista de productos que coinciden con la consulta.
     */
    List<Producto> findByNombreContainingIgnoreCaseOrDescripcionContainingIgnoreCase(String nombre, String descripcion);
}
