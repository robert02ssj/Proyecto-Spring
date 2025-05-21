package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.DetallePedidoRepository;
import com.ssj.tiendanime.repository.PedidoRepository;
import com.ssj.tiendanime.repository.UsuarioRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.security.Principal;
import java.util.List;

/**
 * Controlador para la gestión de los pedidos del usuario.
 * Permite ver la lista de pedidos realizados y el detalle de cada pedido.
 */
@Controller
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePedidoRepository detallePedidoRepository;

    /**
     * Constructor para inyectar las dependencias necesarias.
     * @param pedidoRepository Repositorio de pedidos.
     * @param usuarioRepository Repositorio de usuarios.
     * @param detallePedidoRepository Repositorio de detalles de pedido.
     */
    public PedidoController(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.detallePedidoRepository = detallePedidoRepository;
    }

    /**
     * Muestra la lista de pedidos realizados por el usuario autenticado (excluyendo el carrito activo).
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado.
     * @return Vista con la lista de pedidos.
     */
    @GetMapping("/pedidos")
    public String verPedidos(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        // Excluye el carrito activo
        List<Pedido> pedidos = pedidoRepository.findByUsuarioAndEstadoNot(usuario, "carrito");
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }

    /**
     * Muestra el detalle de un pedido específico del usuario.
     * @param id ID del pedido a mostrar.
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado.
     * @return Vista con el detalle del pedido o redirección si no existe.
     */
    @GetMapping("/pedidos/{id}")
    public String verDetallePedido(@PathVariable("id") Integer id, Model model, Principal principal) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return "redirect:/pedidos";
        }
        List<DetallePedido> detalles = detallePedidoRepository.findByPedido(pedido);
        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", detalles);
        return "pedido-detalle";
    }
}