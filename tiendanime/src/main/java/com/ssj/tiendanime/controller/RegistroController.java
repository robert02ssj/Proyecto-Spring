package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador para el registro de nuevos usuarios.
 * Permite mostrar el formulario de registro y procesar el alta de usuarios.
 */
@Controller
public class RegistroController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * Muestra el formulario de registro de usuario.
     * @return Vista del formulario de registro.
     */
    @GetMapping("/registro")
    public String mostrarFormularioRegistro() {
        return "registro";
    }

    /**
     * Procesa el registro de un nuevo usuario.
     * Si el email ya existe, redirige con error.
     * Si el registro es exitoso, redirige al login con mensaje de éxito.
     * @param nombre Nombre del usuario.
     * @param email Email del usuario.
     * @param contraseña Contraseña del usuario.
     * @return Redirección según el resultado del registro.
     */
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