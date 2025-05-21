package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Categoria;



import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfaz que define el repositorio para la entidad Categoria.
 * Extiende JpaRepository para proporcionar operaciones CRUD.
 */
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    // Inherit findById from JpaRepository without overriding
}
