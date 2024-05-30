package com.example.coworkingprojectback.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @GetMapping()
    public String getInfoUsuario(Authentication authentication){
        Collection<? extends GrantedAuthority> roles = authentication.getAuthorities();

        String primerRol = roles.iterator().next().getAuthority();

        return "{\"rol\": \"" + primerRol + "\"}";
    }

}
