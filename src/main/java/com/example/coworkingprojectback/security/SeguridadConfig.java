package com.example.coworkingprojectback.security;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.bind.annotation.CrossOrigin;

// Clase que contiene la configuración relacionada con la seguridad.
@Configuration
@EnableWebSecurity // Indica que se active la seguridad web en la app.
@AllArgsConstructor
@CrossOrigin("*")
public class SeguridadConfig {
    private JwtAutenticacionDeEntrada jwtAutenticacionDeEntrada;

    // Bean para verificar la información del usuario que va a loguearse.
    @Bean
    AuthenticationManager asistenteDeAutenticacion(AuthenticationConfiguration configuracionDeAutenticacion) throws Exception {
        return configuracionDeAutenticacion.getAuthenticationManager();
    }

    // Bean para encriptar contraseña.
    @Bean
    PasswordEncoder encriptarContrasenia() {
        return new BCryptPasswordEncoder();
    }

    // Bean para incorporar filtro de seguridad JWT desarrollado en la clase JwtFiltroDeAutenticacion.
    @Bean
    JwtFiltroDeAutenticacion jwtFiltroDeAutenticacion() {
        return new JwtFiltroDeAutenticacion();
    }

    // Bean para establecer una cadena de filtros de seguridad en la app. Aquí se determinan los permisos según rol del usuario.
    @Bean
    SecurityFilterChain filtrar(HttpSecurity peticion) throws Exception {
        peticion.csrf(csrf -> csrf.disable())
                .exceptionHandling(exceptionHandling -> exceptionHandling
                        .authenticationEntryPoint(jwtAutenticacionDeEntrada)
                )
                .sessionManagement(sessionManagement -> sessionManagement
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .antMatchers(HttpMethod.POST, "/salas/registrar").authenticated()
                        .antMatchers(HttpMethod.DELETE,"/videojuegos/**" ).authenticated()
                        .antMatchers(HttpMethod.GET, "/salas/listar").permitAll()
                        .antMatchers(HttpMethod.PUT, "/videojuegos/**").authenticated()
                        .antMatchers(HttpMethod.POST, "/salas/listar").permitAll()
                        .antMatchers(HttpMethod.GET, "/usuarios/**").authenticated()
                        .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                        .antMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                        .antMatchers(HttpMethod.POST, "/conectarse").permitAll()
                        .antMatchers(HttpMethod.POST, "/registrarAdmin").permitAll()
                        .antMatchers(HttpMethod.GET, "/caracteristicas/**").permitAll()
                        .antMatchers(HttpMethod.GET, "/categorias/**" ).permitAll()
                        .antMatchers(HttpMethod.POST, "/categorias/nuevo").authenticated()
                        .anyRequest().authenticated()
                );
        peticion.addFilterBefore(jwtFiltroDeAutenticacion(), UsernamePasswordAuthenticationFilter.class);
        return peticion.build();
    }
}