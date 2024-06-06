package com.example.coworkingprojectback.security;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity


public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        // Public endpoints
                        .requestMatchers("/salas/listar", "/salas/{id}", "/salas/nombre/{nombre}").permitAll()
                        .requestMatchers("/usuarios/listar", "/usuarios/{id}").permitAll()
                        .requestMatchers("/caracteristicas/listar", "/caracteristicas/{id}").permitAll()
                        .requestMatchers("/categorias/listar", "/categorias/{id}").permitAll()
                        // Private endpoints
                        .requestMatchers("/salas/registrar", "/salas/actualizar", "/salas/eliminar/**").authenticated()
                        .requestMatchers("/usuarios/registrar", "/usuarios/actualizar", "/usuarios/eliminar/**").authenticated()
                        .requestMatchers("/caracteristicas/registrar", "/caracteristicas/actualizar", "/caracteristicas/eliminar/**").authenticated()
                        .requestMatchers("/categorias/registrar", "/categorias/actualizar", "/categorias/eliminar/**").authenticated()
                        //.anyRequest().permitAll() // Optional: adjust according to your needs

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("adminpassword")
                .roles("ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}




