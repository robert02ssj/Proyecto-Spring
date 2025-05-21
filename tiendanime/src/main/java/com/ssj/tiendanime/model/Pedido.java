package com.ssj.tiendanime.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.math.BigDecimal;

/**
 * Entidad que representa un pedido realizado por un usuario.
 * Incluye información sobre el usuario, fecha de creación, estado y total del pedido.
 */
@Entity
@Table(name = "pedidos")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pedido;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    @Column(nullable = false, length = 50)
    private String estado = "carrito"; // 'carrito', 'pendiente', 'enviado', 'entregado'

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal total = BigDecimal.ZERO;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Pedido() {}

    /**
     * Constructor para crear un pedido con todos los campos.
     * @param usuario Usuario que realiza el pedido.
     * @param fechaCreacion Fecha de creación del pedido.
     * @param estado Estado del pedido.
     * @param total Total del pedido.
     */
    public Pedido(Usuario usuario, LocalDateTime fechaCreacion, String estado, BigDecimal total) {
        this.usuario = usuario;
        this.fechaCreacion = fechaCreacion;
        this.estado = estado;
        this.total = total;
    }

    /**
     * Obtiene el ID del pedido.
     * @return ID del pedido.
     */
    public Integer getId_pedido() {
        return id_pedido;
    }

    /**
     * Establece el ID del pedido.
     * @param id_pedido ID del pedido.
     */
    public void setId_pedido(Integer id_pedido) {
        this.id_pedido = id_pedido;
    }

    /**
     * Obtiene el usuario que realizó el pedido.
     * @return Usuario.
     */
    public Usuario getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que realizó el pedido.
     * @param usuario Usuario.
     */
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene la fecha de creación del pedido.
     * @return Fecha de creación.
     */
    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    /**
     * Establece la fecha de creación del pedido.
     * @param fechaCreacion Fecha de creación.
     */
    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    /**
     * Obtiene el estado del pedido.
     * @return Estado.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Establece el estado del pedido.
     * @param estado Estado.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el total del pedido.
     * @return Total.
     */
    public BigDecimal getTotal() {
        return total;
    }

    /**
     * Establece el total del pedido.
     * @param total Total.
     */
    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}