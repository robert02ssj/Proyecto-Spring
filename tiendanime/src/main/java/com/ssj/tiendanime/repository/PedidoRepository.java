package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Interfaz que define el repositorio para la entidad Pedido.
 * Extiende JpaRepository para proporcionar operaciones CRUD.
 * Además, incluye métodos específicos para buscar pedidos por usuario y estado.
 */
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    /**
     * Busca un pedido por el usuario y el estado.
     *
     * @param usuario El usuario del cual se desea obtener el pedido.
     * @param estado  El estado del pedido.
     * @return El Pedido asociado al usuario y estado, o null si no existe.
     */
    Pedido findByUsuarioAndEstado(Usuario usuario, String estado);
    /**
     * Busca todos los pedidos de un usuario que no están en un estado específico.
     *
     * @param usuario El usuario del cual se desean obtener los pedidos.
     * @param estado  El estado que se desea excluir.
     * @return Una lista de Pedidos asociados al usuario y que no están en el estado especificado.
     */
    List<Pedido> findByUsuarioAndEstadoNot(Usuario usuario, String estado);
    /**
     * Busca todos los pedidos que no están en un estado específico.
     *
     * @param estado El estado que se desea excluir.
     * @return Una lista de Pedidos que no están en el estado especificado.
     */
    List<Pedido> findByEstadoNot(String estado);
    List<Pedido> findByUsuario(Usuario usuario);

}
