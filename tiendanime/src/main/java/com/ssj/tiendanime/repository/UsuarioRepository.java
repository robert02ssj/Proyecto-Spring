package com.ssj.tiendanime.repository;

import com.ssj.tiendanime.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
/**
 * Interfaz que define el repositorio para la entidad Usuario.
 * Extiende JpaRepository para proporcionar operaciones CRUD.
 */
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email El correo electrónico del usuario a buscar.
     * @return El Usuario asociado al correo electrónico, o null si no existe.
     */
    Usuario findByEmail(String email);

}
