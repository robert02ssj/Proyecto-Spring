package com.ssj.tiendanime.controller;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

/**
 * Controlador para la gestión de la cuenta de usuario.
 * Permite ver y editar el perfil, así como cambiar la contraseña.
 */
@Controller
public class CuentaController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Constructor para inyectar las dependencias necesarias.
     * @param usuarioRepository Repositorio de usuarios.
     * @param passwordEncoder Codificador de contraseñas.
     */
    @Autowired
    public CuentaController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Muestra la vista principal de la cuenta del usuario autenticado.
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado.
     * @return Vista de la cuenta.
     */
    @GetMapping("/cuenta")
    public String verCuenta(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "cuenta";
    }

    /**
     * Muestra el formulario para editar el perfil del usuario.
     * @param model Modelo para la vista.
     * @param principal Usuario autenticado.
     * @return Vista del formulario de edición de perfil.
     */
    @GetMapping("/cuenta/editar")
    public String mostrarEditarPerfil(Model model, Principal principal) {
        Usuario usuario = usuarioRepository.findByEmail(principal.getName());
        model.addAttribute("usuario", usuario);
        return "editar-perfil";
    }

    /**
     * Guarda los cambios realizados en el perfil del usuario (nombre y email).
     * @param usuarioEditado Objeto usuario con los datos editados.
     * @param principal Usuario autenticado.
     * @param model Modelo para la vista.
     * @return Redirección al login tras editar el perfil.
     */
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

    /**
     * Muestra el formulario para cambiar la contraseña del usuario.
     * @return Vista del formulario para cambiar contraseña.
     */
    @GetMapping("/cuenta/cambiar-password")
    public String mostrarCambiarPassword() {
        return "cambiar-password";
    }

    /**
     * Cambia la contraseña del usuario autenticado.
     * @param principal Usuario autenticado.
     * @param actual Contraseña actual.
     * @param nueva Nueva contraseña.
     * @param repetir Repetición de la nueva contraseña.
     * @param model Modelo para la vista.
     * @return Redirección al login si el cambio es exitoso, o vuelve al formulario con error.
     */
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