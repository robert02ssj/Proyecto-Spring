package com.ssj.tiendanime;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal de la aplicación Spring Boot para Tiendanime.
 * Inicia la aplicación y configura el contexto de Spring.
 */
@SpringBootApplication
public class TiendanimeApplication {

    /**
     * Método principal que inicia la aplicación Spring Boot.
     * @param args Argumentos de línea de comandos.
     */
    public static void main(String[] args) {
        SpringApplication.run(TiendanimeApplication.class, args);
    }

}