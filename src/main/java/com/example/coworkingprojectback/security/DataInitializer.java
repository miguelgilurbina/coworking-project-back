package com.example.coworkingprojectback.security;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.entity.UsuarioRole;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (!usuarioRepository.existsByEmail("admin@example.com")) {
            Usuario admin = new Usuario();
            admin.setEmail("admin@example.com");
            admin.setNombre("Admin");
            admin.setApellido("Admin");
            admin.setContrasena(bCryptPasswordEncoder.encode("password"));
            admin.setEsAdmin(true);
            admin.setUsuarioRole(UsuarioRole.ROLE_ADMIN);
            usuarioRepository.save(admin);
        }
    }
}
