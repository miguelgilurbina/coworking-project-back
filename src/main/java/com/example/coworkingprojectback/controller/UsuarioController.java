package com.example.coworkingprojectback.controller;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.service.impl.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@RequestBody ) {
        try {
            // Encripta la contraseña antes de pasarla al servicio
            usuarioResponseDTO.setPassword(passwordEncoder.encode(usuarioResponseDTO.getPassword()));

            UsuarioResponseDTO nuevoUsuario = usuarioService.registrarUsuario(usuarioResponseDTO);
            return ResponseEntity.ok(nuevoUsuario);
        } catch (Exception e) {
            // Considera crear un DTO específico para errores
            return ResponseEntity.badRequest().body(null);
        }
    }

    @GetMapping("/info")
    public Map<String, Object> obtenerInfoUsuario(Authentication authentication) {
        Map<String, Object> userInfo = new HashMap<>();
        String username = authentication.getName();
        userInfo.put("username", username);
        userInfo.put("roles", getRoles(authentication));
        return userInfo;
    }

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