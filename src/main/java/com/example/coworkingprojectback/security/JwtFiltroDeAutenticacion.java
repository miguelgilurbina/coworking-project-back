package com.example.coworkingprojectback.security;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Data
public class JwtFiltroDeAutenticacion extends OncePerRequestFilter {

    @Autowired
    private DetallesDeUsuarioServicio detallesDeUsuarioServicio;

    @Autowired
    private JwtGenerador jwtGenerador;

    private String obtenerTokenDeSolicitud(HttpServletRequest solicitud) {
        String bearerToken = solicitud.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest solicitud, HttpServletResponse respuesta, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String token = obtenerTokenDeSolicitud(solicitud);
            if (StringUtils.hasText(token) && jwtGenerador.validarToken(token)) {
                token = jwtGenerador.renovarToken(token); // Renovar token si está cerca de expirar
                String emailUsuario = jwtGenerador.obtenerEmailUsuario(token);
                UserDetails detallesDeUsuario = detallesDeUsuarioServicio.loadUserByUsername(emailUsuario);

                UsernamePasswordAuthenticationToken autenticacion = new UsernamePasswordAuthenticationToken(
                        detallesDeUsuario, null, detallesDeUsuario.getAuthorities());
                autenticacion.setDetails(new WebAuthenticationDetailsSource().buildDetails(solicitud));

                SecurityContextHolder.getContext().setAuthentication(autenticacion);
                respuesta.setHeader("Authorization", "Bearer " + token); // Enviar nuevo token en la respuesta
            }
        } catch (Exception e) {
            logger.error("No se pudo establecer la autenticación del usuario en el contexto de seguridad", e);
        }

        filterChain.doFilter(solicitud, respuesta);
    }
}