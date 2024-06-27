package com.example.coworkingprojectback.security;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class AdminUserInitializer {

    @Autowired
    private UsuarioRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Si estás encriptando las contraseñas

    @PostConstruct
    public void init() {
        // Verificar si ya existe un usuario admin
        if (!userRepository.findByEmail("admin@example.com").isPresent()) {
            Usuario admin = new Usuario();
            admin.setNombre("Admin");
            admin.setApellido("Admin");
            admin.setEmail("admin@example.com");
            admin.setContraseña(passwordEncoder.encode("admin")); // Encriptar la contraseña si es necesario
            admin.setRol("admin");
            userRepository.save(admin);
        }
    }
}