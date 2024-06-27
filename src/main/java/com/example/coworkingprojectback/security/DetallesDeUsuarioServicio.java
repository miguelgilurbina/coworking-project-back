package com.example.coworkingprojectback.security;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.repository.UsuarioRepository;

import lombok.AllArgsConstructor;
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
    private UsuarioRepository usuarioRepository;

    // Método para traer lista de autoridades basadas en el rol del usuario.
    public Collection<GrantedAuthority> mapearAutoridad(String rol) {
        return Collections.singletonList(new SimpleGrantedAuthority(rol));
    }

    // Método para traernos los datos del usuario por medio de su email.
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow( () -> new UsernameNotFoundException("Email no encontrado."));
        return new User(usuario.getEmail(), usuario.getContraseña(), mapearAutoridad(usuario.getRol()));
    }
}