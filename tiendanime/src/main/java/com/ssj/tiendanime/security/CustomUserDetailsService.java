package com.ssj.tiendanime.security;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;

/**
 * Servicio personalizado para la autenticación de usuarios con Spring Security.
 * Implementa la interfaz UserDetailsService para cargar los detalles del usuario desde la base de datos.
 */
@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    /**
     * Constructor para inyectar el repositorio de usuarios.
     * @param usuarioRepository Repositorio de usuarios.
     */
    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /**
     * Carga los detalles del usuario por su email.
     * @param email Email del usuario.
     * @return UserDetails con la información del usuario autenticado.
     * @throws UsernameNotFoundException Si el usuario no existe.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) throw new UsernameNotFoundException("Usuario no encontrado");
        return new User(
            usuario.getEmail(),
            usuario.getContraseña(),
            Collections.singletonList(new SimpleGrantedAuthority("USER"))
        );
    }
}