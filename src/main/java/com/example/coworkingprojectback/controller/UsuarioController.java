package com.example.coworkingprojectback.controller;
import com.example.coworkingprojectback.DTO.In.UsuarioRequestDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<?> registrarUsuario(@RequestBody UsuarioRequestDTO usuario) {
        try {
            // Encripta la contraseña antes de pasarla al servicio
            usuarioService.registrarUsuario(usuario);
            return ResponseEntity.ok(usuario);

        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("El correo electrónico ya está registrado.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error durante el registro.");
        }

    }

    @GetMapping("/listar")
    public ResponseEntity<List<UsuarioResponseDTO>> listarUsuarios() {
        return ResponseEntity.ok(usuarioService.listarUsuarios());
    }

    @GetMapping("/info")
    public Map<String, Object> obtenerInfoUsuario(Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        String username = authentication.getName();
        userInfo.put("username", username);
        userInfo.put("roles", getRoles(authentication));
        return userInfo;
    }
    @GetMapping("/roles")
    private String getRoles(Authentication authentication) {
        StringBuilder roles = new StringBuilder();
        boolean first = true;
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            if (!first) {
                roles.append(", ");
            } else {
                first = false;
            }
            roles.append(authority.getAuthority());
        }
        return roles.toString();
    }
}