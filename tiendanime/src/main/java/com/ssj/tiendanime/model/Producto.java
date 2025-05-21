package com.ssj.tiendanime.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa un producto de la tienda.
 * Incluye información como nombre, descripción, precio, imagen, stock, visitas y categoría.
 */
@Entity
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_producto;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(columnDefinition = "TEXT")
    private String descripcion;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal precio;

    @Column(name = "imagen_url", length = 255)
    private String imagenUrl;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Integer visitas = 0;

    @ManyToOne
    @JoinColumn(name = "id_categoria")
    private Categoria categoria;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Producto() {}

    /**
     * Constructor para crear un producto con todos los campos.
     * @param nombre Nombre del producto.
     * @param descripcion Descripción del producto.
     * @param precio Precio del producto.
     * @param imagenUrl URL de la imagen del producto.
     * @param stock Stock disponible.
     * @param visitas Número de visitas.
     * @param categoria Categoría del producto.
     */
    public Producto(String nombre, String descripcion, BigDecimal precio, String imagenUrl, Integer stock, Integer visitas, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagenUrl = imagenUrl;
        this.stock = stock;
        this.visitas = visitas;
        this.categoria = categoria;
    }

    /**
     * Obtiene el ID del producto.
     * @return ID del producto.
     */
    public Integer getId_producto() {
        return id_producto;
    }

    /**
     * Establece el ID del producto.
     * @param id_producto ID del producto.
     */
    public void setId_producto(Integer id_producto) {
        this.id_producto = id_producto;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene la descripción del producto.
     * @return Descripción del producto.
     */
    public String getDescripcion() {
        return descripcion;
    }

    /**
     * Establece la descripción del producto.
     * @param descripcion Descripción del producto.
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    /**
     * Obtiene el precio del producto.
     * @return Precio del producto.
     */
    public BigDecimal getPrecio() {
        return precio;
    }

    /**
     * Establece el precio del producto.
     * @param precio Precio del producto.
     */
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    /**
     * Obtiene la URL de la imagen del producto.
     * @return URL de la imagen.
     */
    public String getImagenUrl() {
        return imagenUrl;
    }

    /**
     * Establece la URL de la imagen del producto.
     * @param imagenUrl URL de la imagen.
     */
    public void setImagenUrl(String imagenUrl) {
        this.imagenUrl = imagenUrl;
    }

    /**
     * Obtiene el stock disponible del producto.
     * @return Stock disponible.
     */
    public Integer getStock() {
        return stock;
    }

    /**
     * Establece el stock disponible del producto.
     * @param stock Stock disponible.
     */
    public void setStock(Integer stock) {
        this.stock = stock;
    }

    /**
     * Obtiene el número de visitas del producto.
     * @return Número de visitas.
     */
    public Integer getVisitas() {
        return visitas;
    }

    /**
     * Establece el número de visitas del producto.
     * @param visitas Número de visitas.
     */
    public void setVisitas(Integer visitas) {
        this.visitas = visitas;
    }

    /**
     * Obtiene la categoría del producto.
     * @return Categoría.
     */
    public Categoria getCategoria() {
        return categoria;
    }

    /**
     * Establece la categoría del producto.
     * @param categoria Categoría.
     */
    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}