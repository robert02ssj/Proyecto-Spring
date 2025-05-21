package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.Producto;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que define el repositorio para la entidad DetallePedido.
 * Extiende JpaRepository para proporcionar operaciones CRUD.
 * Además, incluye métodos específicos para buscar detalles de pedidos por
 * pedido y producto.
 */
public interface DetallePedidoRepository extends JpaRepository<DetallePedido, Integer> {
    /**
     * Busca los detalles de pedido asociados a un pedido específico.
     *
     * @param pedido El pedido del cual se desean obtener los detalles.
     * @return Una lista de DetallePedido asociados al pedido.
     */
    List<DetallePedido> findByPedido(Pedido pedido);

    /**
     * Busca un detalle de pedido específico por su pedido y producto.
     *
     * @param pedido   El pedido del cual se desea obtener el detalle.
     * @param producto El producto del cual se desea obtener el detalle.
     * @return El DetallePedido asociado al pedido y producto, o null si no existe.
     */
    DetallePedido findByPedidoAndProducto(Pedido pedido, Producto producto);

    @Transactional
    /**
     * Elimina todos los detalles de pedido asociados a un pedido específico.
     *
     * @param pedido El pedido del cual se desean eliminar los detalles.
     */
    void deleteByPedido(Pedido pedido);
}
