package com.example.coworkingprojectback.security;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Collections;

@Service
@Transactional
@AllArgsConstructor
public class DetallesDeUsuarioServicio implements UserDetailsService {
    private static final Logger logger = LoggerFactory.getLogger(DetallesDeUsuarioServicio.class);

    private final UsuarioRepository usuarioRepository;

    // Método para traer lista de autoridades basadas en el rol del usuario.
    public Collection<GrantedAuthority> mapearAutoridad(String rol) {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rol.toUpperCase()));
    }

    // Método para traernos los datos del usuario por medio de su email.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.debug("Attempting to load user by email: {}", email);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> {
                    logger.error("User not found with email: {}", email);
                    return new UsernameNotFoundException("Email no encontrado: " + email);
                });


        String password = usuario.getPassword();
        if (password == null || password.isEmpty()) {
            logger.error("Password for user {} is null or empty", email);
            throw new UsernameNotFoundException("Password no válida para el usuario: " + email);
        }

        logger.debug("User found: {}", usuario.getEmail());
        logger.debug("Stored password hash: {}", usuario.getPassword());

        return new User(usuario.getEmail(), usuario.getPassword(), mapearAutoridad(usuario.getRol()));
    }
}