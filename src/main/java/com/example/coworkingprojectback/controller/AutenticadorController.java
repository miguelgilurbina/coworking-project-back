package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.login.JwtUtil;
import com.example.coworkingprojectback.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutenticadorController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private UsuarioService usuarioService;
}
