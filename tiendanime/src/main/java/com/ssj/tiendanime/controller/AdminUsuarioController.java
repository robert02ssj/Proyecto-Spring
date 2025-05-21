package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.repository.UsuarioRepository;
import com.ssj.tiendanime.repository.DetallePedidoRepository;
import com.ssj.tiendanime.repository.PedidoRepository;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AdminUsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PedidoRepository pedidoRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    public AdminUsuarioController(UsuarioRepository usuarioRepository, PedidoRepository pedidoRepository, DetallePedidoRepository detallePedidoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.pedidoRepository = pedidoRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    /**
     * Muestra la lista de usuarios registrados.
     */
    @GetMapping("/admin/usuarios")
    public String listarUsuarios(Model model) {
        model.addAttribute("usuarios", usuarioRepository.findAll());
        return "gestionar_usuarios";
    }

    /**
     * Elimina un usuario por su ID.
     */
    @GetMapping("/admin/usuarios/eliminar/{id}")
    public String eliminarUsuario(@PathVariable Integer id) {
        // Elimina los pedidos del usuario
        List<Pedido> pedidos = pedidoRepository.findByUsuario(usuarioRepository.findById(id).orElse(null));
        for (Pedido pedido : pedidos) {
            detallePedidoRepository.deleteByPedido(pedido);
            pedidoRepository.delete(pedido);
        }
        usuarioRepository.deleteById(id);
        return "redirect:/admin/usuarios";
    }
}
