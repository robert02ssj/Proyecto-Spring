package com.ssj.tiendanime.model;

import jakarta.persistence.*;
import java.math.BigDecimal;

/**
 * Entidad que representa el detalle de un pedido.
 * Incluye el producto, la cantidad, el precio unitario y la relación con el pedido.
 */
@Entity
@Table(name = "detalle_pedidos")
public class DetallePedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_detalle;

    @ManyToOne
    @JoinColumn(name = "id_pedido")
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @Column(nullable = false)
    private Integer cantidad = 1;

    @Column(name = "precio_unitario", nullable = false, precision = 10, scale = 2)
    private BigDecimal precioUnitario;

    /**
     * Constructor vacío requerido por JPA.
     */
    public DetallePedido() {}

    /**
     * Constructor para crear un detalle de pedido con todos los campos.
     * @param pedido Pedido al que pertenece el detalle.
     * @param producto Producto incluido en el pedido.
     * @param cantidad Cantidad del producto.
     * @param precioUnitario Precio unitario del producto.
     */
    public DetallePedido(Pedido pedido, Producto producto, Integer cantidad, BigDecimal precioUnitario) {
        this.pedido = pedido;
        this.producto = producto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
    }

    /**
     * Obtiene el ID del detalle de pedido.
     * @return ID del detalle.
     */
    public Integer getId_detalle() {
        return id_detalle;
    }

    /**
     * Establece el ID del detalle de pedido.
     * @param id_detalle ID del detalle.
     */
    public void setId_detalle(Integer id_detalle) {
        this.id_detalle = id_detalle;
    }

    /**
     * Obtiene el pedido asociado.
     * @return Pedido.
     */
    public Pedido getPedido() {
        return pedido;
    }

    /**
     * Establece el pedido asociado.
     * @param pedido Pedido.
     */
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    /**
     * Obtiene el producto asociado.
     * @return Producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto asociado.
     * @param producto Producto.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad de productos.
     * @return Cantidad.
     */
    public Integer getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad de productos.
     * @param cantidad Cantidad.
     */
    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el precio unitario del producto.
     * @return Precio unitario.
     */
    public BigDecimal getPrecioUnitario() {
        return precioUnitario;
    }

    /**
     * Establece el precio unitario del producto.
     * @param precioUnitario Precio unitario.
     */
    public void setPrecioUnitario(BigDecimal precioUnitario) {
        this.precioUnitario = precioUnitario;
    }
}