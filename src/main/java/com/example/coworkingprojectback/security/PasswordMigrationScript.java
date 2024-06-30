package com.example.coworkingprojectback.security;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class PasswordMigrationScript {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public PasswordMigrationScript(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostConstruct
    public void migratePasswords() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        for (Usuario usuario : usuarios) {

                String password = usuario.getPassword();

                if (password == null || password.isEmpty()) {
                    // Manejar el caso de contraseña nula o vacía
                    continue; // O bien, establecer una contraseña predeterminada
                }

                if (!usuario.getPassword().startsWith("$2a$")) { // Verifica si la contraseña no está codificada con BCrypt
                String encodedPassword = passwordEncoder.encode(usuario.getPassword());
                usuario.setPassword(encodedPassword);
                usuarioRepository.save(usuario);
            }
        }
    }
}
