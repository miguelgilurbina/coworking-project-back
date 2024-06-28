package com.example.coworkingprojectback.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtGenerador {

    // Método para crear el token
    public String generarToken(Authentication autenticacion) {
        String email = autenticacion.getName();
        Date horaActual = new Date();
        Date expiracionToken = new Date(horaActual.getTime() + ConstantesDeSeguridad.EXPIRACION_TOKEN_JWT);

        return Jwts.builder()
                .subject(email)
                .issuedAt(horaActual)
                .expiration(expiracionToken)
                .signWith(generarKey())
                .compact();
    }

    // Método para extraer el email del token
    public String obtenerEmailUsuario(String token) {
        Claims claims = Jwts.parser()
                .verifyWith(generarKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return claims.getSubject();
    }

    // Genera la clave secreta
    public SecretKey generarKey() {
        return Keys.hmacShaKeyFor(ConstantesDeSeguridad.stringKeyParser().getBytes());
    }

    // Método para validar token
    public Boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .verifyWith(generarKey())
                    .build()
                    .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token expirado o incorrecto.");
        }
    }

    // Nuevo método para obtener todos los claims del token
    public Claims obtenerTodosLosClaims(String token) {
        return Jwts.parser()
                .verifyWith(generarKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }
}