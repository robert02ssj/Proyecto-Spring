package com.ssj.tiendanime.service;

import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import com.ssj.tiendanime.repository.PedidoRepository;
import com.ssj.tiendanime.repository.DetallePedidoRepository;
import com.ssj.tiendanime.repository.ProductoRepository;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.model.Producto;
import com.ssj.tiendanime.model.Usuario;

/**
 * Servicio para la gestión del carrito de compras.
 * Permite crear o recuperar el carrito, agregar/eliminar productos, obtener detalles y confirmar el pedido.
 */
@Service
public class CarritoService {
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;

    /**
     * Constructor para inyectar los repositorios necesarios.
     * @param pedidoRepository Repositorio de pedidos.
     * @param detallePedidoRepository Repositorio de detalles de pedido.
     * @param productoRepository Repositorio de productos.
     */
    public CarritoService(PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.productoRepository = productoRepository;
    }

    /**
     * Obtiene el carrito activo del usuario o lo crea si no existe.
     * @param usuario Usuario autenticado.
     * @return Pedido que representa el carrito.
     */
    public Pedido obtenerOCrearCarrito(Usuario usuario) {
        Pedido carrito = pedidoRepository.findByUsuarioAndEstado(usuario, "carrito");
        if (carrito == null) {
            carrito = new Pedido();
            carrito.setUsuario(usuario);
            carrito.setEstado("carrito");
            carrito.setTotal(BigDecimal.ZERO);
            pedidoRepository.save(carrito);
        }
        return carrito;
    }

    /**
     * Agrega un producto al carrito. Si ya existe, suma la cantidad.
     * @param carrito Carrito del usuario.
     * @param idProducto ID del producto a agregar.
     * @param cantidad Cantidad a agregar.
     */
    public void agregarProductoAlCarrito(Pedido carrito, Integer idProducto, int cantidad) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        DetallePedido detalle = detallePedidoRepository.findByPedidoAndProducto(carrito, producto);
        if (detalle == null) {
            detalle = new DetallePedido();
            detalle.setPedido(carrito);
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecioUnitario(producto.getPrecio());
        } else {
            detalle.setCantidad(detalle.getCantidad() + cantidad);
        }
        detallePedidoRepository.save(detalle);
    }

    /**
     * Obtiene la lista de detalles del carrito.
     * @param carrito Carrito del usuario.
     * @return Lista de detalles de pedido.
     */
    public List<DetallePedido> obtenerDetallesCarrito(Pedido carrito) {
        return detallePedidoRepository.findByPedido(carrito);
    }

    /**
     * Elimina un producto del carrito.
     * @param carrito Carrito del usuario.
     * @param idProducto ID del producto a eliminar.
     */
    public void eliminarProductoDelCarrito(Pedido carrito, Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        DetallePedido detalle = detallePedidoRepository.findByPedidoAndProducto(carrito, producto);
        if (detalle != null) {
            detallePedidoRepository.delete(detalle);
        }
    }

    /**
     * Confirma el carrito, descuenta stock, calcula el total y cambia el estado a "pendiente".
     * @param carrito Carrito a confirmar.
     */
    public void confirmarCarrito(Pedido carrito) {
        carrito.setEstado("pendiente");
        carrito.setFechaCreacion(LocalDateTime.now());

        List<DetallePedido> detalles = detallePedidoRepository.findByPedido(carrito);
        for (DetallePedido detalle : detalles) {
            Producto producto = detalle.getProducto();
            int cantidad = detalle.getCantidad();
            if (producto.getStock() >= cantidad) {
                producto.setStock(producto.getStock() - cantidad);
                productoRepository.save(producto);
            } else {
                // Si no hay suficiente stock, lanza excepción
                throw new IllegalStateException("No hay suficiente stock para el producto: " + producto.getNombre());
            }
        }

        // Calcula el total del pedido
        BigDecimal total = detallePedidoRepository.findByPedido(carrito).stream()
            .filter(d -> d.getPrecioUnitario() != null && d.getCantidad() != null)
            .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        carrito.setTotal(total);
        pedidoRepository.save(carrito);
    }
}