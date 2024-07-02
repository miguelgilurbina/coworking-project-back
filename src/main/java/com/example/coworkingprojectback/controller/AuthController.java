package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.payload.JwtAuthenticationResponse;
import com.example.coworkingprojectback.payload.LoginRequest;
import com.example.coworkingprojectback.payload.VerificationResponse;
import com.example.coworkingprojectback.security.JwtGenerador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;
import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtGenerador jwtGenerador;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getUsername(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtGenerador.generarToken(authentication);
        List<String> roles = jwtGenerador.obtenerRolesDesdeToken(jwt);

        Cookie cookie = new Cookie("jwt", jwt);
        cookie.setHttpOnly(true);
        cookie.setSecure(true); // si estás usando HTTPS
        cookie.setPath("/");
        cookie.setMaxAge(3600); // expira en 1 hora, ajusta según necesites

        response.addCookie(cookie);

        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, roles));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);

        response.addCookie(cookie);

        return ResponseEntity.ok("Logout successful");
    }

    @GetMapping("/verify")
    public ResponseEntity<?> verificarToken(@RequestHeader("Authorization") String token) {
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7);
            if (jwtGenerador.validarToken(token)) {
                String emailUsuario = jwtGenerador.obtenerEmailUsuario(token);
                List<String> roles = jwtGenerador.obtenerRolesDesdeToken(token);
                return ResponseEntity.ok(new VerificationResponse(emailUsuario, roles, true));
            }
        }
        return ResponseEntity.badRequest().body(new VerificationResponse(null, null, false));
    }
}
