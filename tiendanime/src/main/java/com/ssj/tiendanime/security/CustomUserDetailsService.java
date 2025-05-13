package com.ssj.tiendanime.security;

import com.ssj.tiendanime.model.Usuario;
import com.ssj.tiendanime.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

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
