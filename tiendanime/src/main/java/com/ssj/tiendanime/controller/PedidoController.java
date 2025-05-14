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

@Controller
public class PedidoController {

    private final PedidoRepository pedidoRepository;
    private final UsuarioRepository usuarioRepository;
    private final DetallePedidoRepository detallePedidoRepository;


    public PedidoController(PedidoRepository pedidoRepository, UsuarioRepository usuarioRepository, DetallePedidoRepository detallePedidoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.usuarioRepository = usuarioRepository;
        this.detallePedidoRepository = detallePedidoRepository;

    }

    @GetMapping("/pedidos")
    public String verPedidos(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        // Excluye el carrito activo
        List<Pedido> pedidos = pedidoRepository.findByUsuarioAndEstadoNot(usuario, "carrito");
        model.addAttribute("pedidos", pedidos);
        return "pedidos";
    }
    
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
