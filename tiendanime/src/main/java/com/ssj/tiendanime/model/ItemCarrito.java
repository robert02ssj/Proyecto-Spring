package com.ssj.tiendanime.model;

/**
 * Clase que representa un ítem dentro del carrito de compras.
 * Contiene el producto y la cantidad seleccionada por el usuario.
 */
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    /**
     * Constructor para crear un ítem de carrito con producto y cantidad.
     * @param producto Producto añadido al carrito.
     * @param cantidad Cantidad del producto.
     */
    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    /**
     * Obtiene el producto del ítem del carrito.
     * @return Producto.
     */
    public Producto getProducto() {
        return producto;
    }

    /**
     * Establece el producto del ítem del carrito.
     * @param producto Producto.
     */
    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    /**
     * Obtiene la cantidad del producto en el carrito.
     * @return Cantidad.
     */
    public int getCantidad() {
        return cantidad;
    }

    /**
     * Establece la cantidad del producto en el carrito.
     * @param cantidad Cantidad.
     */
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}