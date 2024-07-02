package com.example.coworkingprojectback.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import javax.crypto.SecretKey;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtGenerador {


    @Value("${app.jwtSecret}")
    private String secretKey;

    @Value("${app.jwtExpirationInMs}")
    private long expirationTime;

    // Método para crear el token
    public String generarToken(Authentication autenticacion) {
        String email = autenticacion.getName();
        String rol = autenticacion.getAuthorities().iterator().next().getAuthority();
        Date horaActual = new Date();
        Date expiracionToken = new Date(horaActual.getTime() + expirationTime);

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(horaActual)
                .setExpiration(expiracionToken)
                .signWith(generarKey())
                .compact();
    }

    // Método para extraer el email del token
    public String obtenerEmailUsuario(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(generarKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
        return claims.getSubject();
    }

    // Genera la clave secreta
    public SecretKey generarKey() {
        return Keys.hmacShaKeyFor(secretKey.getBytes());
    }

    // Método para validar token
    public Boolean validarToken(String token) {
        try {
            Jwts.parser()
                    .setSigningKey(generarKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            throw new AuthenticationCredentialsNotFoundException("Token expirado o incorrecto.");
        }
    }

    // Nuevo método para obtener todos los claims del token
    public Claims obtenerTodosLosClaims(String token) {
        return Jwts.parser()
                .setSigningKey(generarKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }


    public String renovarToken(String token) {
        Claims claims = obtenerTodosLosClaims(token);
        Date now = new Date();
        Date expirationDate = claims.getExpiration();
        long tiempoRestante = expirationDate.getTime() - now.getTime();

        if (tiempoRestante < (expirationTime / 2)) {
            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(now)
                    .setExpiration(new Date(now.getTime() + expirationTime))
                    .signWith(generarKey())
                    .compact();
        }

        return token;
    }



}