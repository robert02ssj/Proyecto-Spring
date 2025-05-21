package com.ssj.tiendanime.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entidad que representa un usuario de la tienda.
 * Incluye información como nombre, email, contraseña y fecha de registro.
 */
@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_usuario;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Column(nullable = false, length = 255)
    private String contraseña;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;

    /**
     * Constructor vacío requerido por JPA.
     */
    public Usuario() {}

    /**
     * Constructor para crear un usuario con todos los campos.
     * @param nombre Nombre del usuario.
     * @param email Email del usuario.
     * @param contraseña Contraseña del usuario.
     * @param fechaRegistro Fecha de registro del usuario.
     */
    public Usuario(String nombre, String email, String contraseña, LocalDateTime fechaRegistro) {
        this.nombre = nombre;
        this.email = email;
        this.contraseña = contraseña;
        this.fechaRegistro = fechaRegistro;
    }

    /**
     * Obtiene el ID del usuario.
     * @return ID del usuario.
     */
    public Integer getId_usuario() {
        return id_usuario;
    }

    /**
     * Establece el ID del usuario.
     * @param id_usuario ID del usuario.
     */
    public void setId_usuario(Integer id_usuario) {
        this.id_usuario = id_usuario;
    }

    /**
     * Obtiene el nombre del usuario.
     * @return Nombre del usuario.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del usuario.
     * @param nombre Nombre del usuario.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el email del usuario.
     * @return Email del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el email del usuario.
     * @param email Email del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return Contraseña del usuario.
     */
    public String getContraseña() {
        return contraseña;
    }

    /**
     * Establece la contraseña del usuario.
     * @param contraseña Contraseña del usuario.
     */
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    /**
     * Obtiene la fecha de registro del usuario.
     * @return Fecha de registro.
     */
    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    /**
     * Establece la fecha de registro del usuario.
     * @param fechaRegistro Fecha de registro.
     */
    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}