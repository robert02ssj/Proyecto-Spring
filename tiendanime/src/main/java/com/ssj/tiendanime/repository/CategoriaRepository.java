package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Override
    default Optional<Categoria> findById(Integer id) {
        return Optional.empty();
    }
}
