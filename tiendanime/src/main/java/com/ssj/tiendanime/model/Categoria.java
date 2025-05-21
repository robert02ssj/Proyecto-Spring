package com.ssj.tiendanime.model;

import jakarta.persistence.*;

/**
 * Entidad que representa una categoría de productos en la tienda.
 * Cada categoría tiene un identificador y un nombre.
 */
@Entity
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_categoria;

    @Column(name = "nombre_categoria", nullable = false, length = 100)
    private String nombreCategoria;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Categoria() {}

    /**
     * Constructor para crear una categoría con nombre.
     * @param nombreCategoria Nombre de la categoría.
     */
    public Categoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    /**
     * Obtiene el ID de la categoría.
     * @return ID de la categoría.
     */
    public Integer getId_categoria() {
        return id_categoria;
    }

    /**
     * Establece el ID de la categoría.
     * @param id_categoria ID de la categoría.
     */
    public void setId_categoria(Integer id_categoria) {
        this.id_categoria = id_categoria;
    }

    /**
     * Obtiene el nombre de la categoría.
     * @return Nombre de la categoría.
     */
    public String getNombreCategoria() {
        return nombreCategoria;
    }

    /**
     * Establece el nombre de la categoría.
     * @param nombreCategoria Nombre de la categoría.
     */
    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }
}