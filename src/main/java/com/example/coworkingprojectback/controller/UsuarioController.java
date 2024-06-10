package com.example.coworkingprojectback.controller;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @GetMapping("/info")
    public String obtenerInfoUsuario(Authentication authentication) {
        // Obtiene los roles del usuario
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        String primerRol = roles.iterator().next().getAuthority();

        // Puedes hacer lo que quieras con esta información, por ejemplo, devolverla como JSON
        return "{\"rol\": \"" + primerRol + "\"}";
    }
}