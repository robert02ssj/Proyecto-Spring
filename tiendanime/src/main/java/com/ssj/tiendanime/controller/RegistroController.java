package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    @PostMapping("/registro")
    public String registrarUsuario(@RequestParam String nombre,
                                   @RequestParam String email,
                                   @RequestParam String contraseña) {
        if (usuarioRepository.findByEmail(email) != null) {
            // Ya existe un usuario con ese email
            return "redirect:/registro?error";
        }
        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setEmail(email);
        usuario.setContraseña(passwordEncoder.encode(contraseña));
        usuario.setFechaRegistro(LocalDateTime.now());

        usuarioRepository.save(usuario);
        return "redirect:/login?registroExitoso";
    }
}