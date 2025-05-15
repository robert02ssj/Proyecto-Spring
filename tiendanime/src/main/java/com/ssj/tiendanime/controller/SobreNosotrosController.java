package com.ssj.tiendanime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SobreNosotrosController {

    @GetMapping("/sobre-nosotros")
    public String mostrarSobreNosotros() {
        return "sobre-nosotros";
    }
}
