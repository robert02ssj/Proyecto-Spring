package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
 List<DetallePedido> findByPedido(Pedido pedido);
    DetallePedido findByPedidoAndProducto(Pedido pedido, Producto producto);
    
}
