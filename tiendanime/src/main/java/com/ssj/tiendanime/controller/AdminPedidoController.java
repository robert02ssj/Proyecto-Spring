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

/**
 * Controlador para la gestión administrativa de pedidos.
 * Permite listar, ver detalles, eliminar y cambiar el estado de los pedidos.
 */
@Controller
@RequestMapping("/admin/pedidos")
public class AdminPedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private DetallePedidoRepository detallePedidoRepository;

    /**
     * Lista todos los pedidos que no están en estado "carrito".
     * @param model Modelo para la vista.
     * @return Vista de gestión de pedidos.
     */
    @GetMapping
    public String listarPedidos(Model model) {
        model.addAttribute("pedidos", pedidoRepository.findByEstadoNot("carrito"));
        return "gestionar_pedidos";
    }

    /**
     * Muestra el detalle de un pedido específico.
     * @param id ID del pedido a mostrar.
     * @param model Modelo para la vista.
     * @return Vista con el detalle del pedido o redirección si no existe.
     */
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

    /**
     * Elimina un pedido y sus detalles asociados.
     * @param id ID del pedido a eliminar.
     * @return Redirección a la lista de pedidos.
     */
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

    /**
     * Cambia el estado de un pedido.
     * @param id ID del pedido.
     * @param estado Nuevo estado a asignar.
     * @return Redirección a la lista de pedidos.
     */
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