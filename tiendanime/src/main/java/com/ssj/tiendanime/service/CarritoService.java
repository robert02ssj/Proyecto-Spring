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

@Service
public class CarritoService {
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;
    private final ProductoRepository productoRepository;

    public CarritoService(PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository, ProductoRepository productoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
        this.productoRepository = productoRepository;
    }

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

    public List<DetallePedido> obtenerDetallesCarrito(Pedido carrito) {
        return detallePedidoRepository.findByPedido(carrito);
    }

    public void eliminarProductoDelCarrito(Pedido carrito, Integer idProducto) {
        Producto producto = productoRepository.findById(idProducto).orElseThrow();
        DetallePedido detalle = detallePedidoRepository.findByPedidoAndProducto(carrito, producto);
        if (detalle != null) {
            detallePedidoRepository.delete(detalle);
        }
    }

    public void confirmarCarrito(Pedido carrito) {
        carrito.setEstado("pendiente");
        carrito.setFechaCreacion(LocalDateTime.now()); // <-- Añade esta línea

        // Calcula el total
        BigDecimal total = detallePedidoRepository.findByPedido(carrito).stream()
    .filter(d -> d.getPrecioUnitario() != null && d.getCantidad() != null)
    .map(d -> d.getPrecioUnitario().multiply(BigDecimal.valueOf(d.getCantidad())))
    .reduce(BigDecimal.ZERO, BigDecimal::add);
        carrito.setTotal(total);
        pedidoRepository.save(carrito);
    }
}
