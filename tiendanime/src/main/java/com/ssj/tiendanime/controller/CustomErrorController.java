package com.ssj.tiendanime.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            int statusCode = Integer.parseInt(status.toString());
            if (statusCode == 404) {
                model.addAttribute("mensaje", "La página que buscas no existe (Error 404).");
            } else if (statusCode == 500) {
                model.addAttribute("mensaje", "Ha ocurrido un error interno en el servidor (Error 500).");
            } else {
                model.addAttribute("mensaje", "Ha ocurrido un error inesperado (Error " + statusCode + ").");
            }
        }
        return "error";
    }
}
