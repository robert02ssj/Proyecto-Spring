package com.ssj.tiendanime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
/**
 * Controlador para la gestión de la página de inicio de sesión.
 */
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
