package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.Usuario;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    Pedido findByUsuarioAndEstado(Usuario usuario, String estado);
    List<Pedido> findByUsuarioAndEstadoNot(Usuario usuario, String estado);
    
}
