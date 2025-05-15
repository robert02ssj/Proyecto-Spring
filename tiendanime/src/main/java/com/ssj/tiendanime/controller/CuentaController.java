package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@Controller
public class CuentaController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CuentaController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Vista principal de cuenta
    @GetMapping("/cuenta")
    public String verCuenta(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "cuenta";
    }

    // Mostrar formulario de edición de perfil
    @GetMapping("/cuenta/editar")
    public String mostrarEditarPerfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "editar-perfil";
    }

    // Guardar cambios de perfil (nombre y email)
    @PostMapping("/cuenta/editar")
    public String guardarEditarPerfil(@ModelAttribute Usuario usuarioEditado, Principal principal, Model model) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        if (usuario != null) {
            usuario.setNombre(usuarioEditado.getNombre());
            usuario.setEmail(usuarioEditado.getEmail());
            usuarioRepository.save(usuario);
            model.addAttribute("success", "Datos actualizados correctamente.");
        }
        return "login";
    }

    // Mostrar formulario para cambiar contraseña
    @GetMapping("/cuenta/cambiar-password")
    public String mostrarCambiarPassword() {
        return "cambiar-password";
    }

    // Cambiar contraseña
    @PostMapping("/cuenta/cambiar-password")
    public String cambiarPassword(Principal principal,
                                  @RequestParam String actual,
                                  @RequestParam String nueva,
                                  @RequestParam String repetir,
                                  Model model) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        if (usuario != null && passwordEncoder.matches(actual, usuario.getContraseña())) {
            if (nueva.equals(repetir)) {
                usuario.setContraseña(passwordEncoder.encode(nueva));
                usuarioRepository.save(usuario);
                model.addAttribute("success", "Contraseña cambiada correctamente.");
                return "login";
            } else {
                model.addAttribute("error", "Las contraseñas nuevas no coinciden.");
            }
        } else {
            model.addAttribute("error", "La contraseña actual no es correcta.");
        }
        return "cambiar-password";
    }
}
