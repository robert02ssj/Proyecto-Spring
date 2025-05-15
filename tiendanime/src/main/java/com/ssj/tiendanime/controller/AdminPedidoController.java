package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Pedido;
import com.ssj.tiendanime.model.DetallePedido;
import com.ssj.tiendanime.repository.PedidoRepository;
import com.ssj.tiendanime.repository.DetallePedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/pedidos")
public class AdminPedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    // Listar todos los pedidos
    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findAll());
        return "gestionar_pedidos";
    }

    // Ver detalle de un pedido
    @GetMapping("/{id}")
    public String verDetallePedido(@PathVariable("id") Integer id, Model model) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido == null) {
            return "redirect:/admin/pedidos";
        }
        List<DetallePedido> detalles = detallePedidoRepository.findByPedido(pedido);
        model.addAttribute("pedido", pedido);
        model.addAttribute("detalles", detalles);
        return "pedido-detalle-admin";
    }

    // Eliminar pedido
    @PostMapping("/eliminar/{id}")
    public String eliminarPedido(@PathVariable("id") Integer id) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
    if (pedido != null) {
        // Elimina primero los detalles del pedido
        detallePedidoRepository.deleteByPedido(pedido);
        // Ahora elimina el pedido
        pedidoRepository.deleteById(id);
    }
        return "redirect:/admin/pedidos";
    }

    // Cambiar estado del pedido
    @PostMapping("/cambiar-estado/{id}")
    public String cambiarEstadoPedido(@PathVariable("id") Integer id, @RequestParam("estado") String estado) {
        Pedido pedido = pedidoRepository.findById(id).orElse(null);
        if (pedido != null) {
            pedido.setEstado(estado);
            pedidoRepository.save(pedido);
        }
        return "redirect:/admin/pedidos";
    }
}
