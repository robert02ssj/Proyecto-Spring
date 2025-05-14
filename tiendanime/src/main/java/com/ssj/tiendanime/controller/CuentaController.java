package com.ssj.tiendanime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

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
    // Mostrar formulario de edición de perfil
    @GetMapping("/cuenta/editar")
public String mostrarEditarPerfil(Model model, Principal principal) {
    Usuario usuario = usuarioRepository.findByEmail(principal.getName());
    model.addAttribute("usuario", usuario);
    return "editar-perfil";
}

// Guardar cambios
@PostMapping("/cuenta/editar")
public String guardarEditarPerfil(@ModelAttribute Usuario usuarioEditado, Principal principal) {
    Usuario usuario = usuarioRepository.findByEmail(principal.getName());
    if (usuario != null) {
        usuario.setNombre(usuarioEditado.getNombre());
        // Si quieres permitir cambiar el email, añade aquí la lógica
        usuario.setEmail(usuarioEditado.getEmail());
        usuarioRepository.save(usuario);
    }
    return "redirect:/cuenta";
}
}
