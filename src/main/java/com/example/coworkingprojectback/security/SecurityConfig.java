package com.example.coworkingprojectback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public SecurityConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
        return new InMemoryUserDetailsManager(
                org.springframework.security.core.userdetails.User
                        .withUsername("admin")
                        .password(bCryptPasswordEncoder.encode("password")) // Codifica la contraseña
                        .roles("ADMIN")
                        .build()
        );
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/salas/listar", "/salas/{id}", "/salas/nombre/{nombre}").permitAll()
                        .requestMatchers("/salas/registrar", "/salas/actualizar", "/salas/eliminar/**").authenticated()
                        .requestMatchers("/usuarios/{id}", "/usuarios/listar", "/usuarios/registrar", "/usuarios/actualizar", "/usuarios/eliminar/**").authenticated()
                        .requestMatchers("/caracteristicas/listar", "/caracteristicas/{id}", "/caracteristicas/registrar", "/caracteristicas/actualizar", "/caracteristicas/eliminar/**").authenticated()
                        .requestMatchers("/categorias/listar", "/categorias/{id}", "/categorias/registrar", "/categorias/actualizar", "/categorias/eliminar/**").authenticated()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF para pruebas; considerar habilitar en producción
        return http.build();
    }
}
