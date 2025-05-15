package com.ssj.tiendanime.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AsistenciaController {

    @GetMapping("/asistencia")
    public String mostrarAsistencia() {
        return "asistencia";
    }
}
