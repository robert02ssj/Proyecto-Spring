package com.ssj.tiendanime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;

import java.security.Principal;

@Controller
public class CuentaController {

    private final UsuarioRepository usuarioRepository;

    public CuentaController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @GetMapping("/cuenta")
    public String verCuenta(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "cuenta";
    }
}
