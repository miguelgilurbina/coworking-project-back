package com.example.coworkingprojectback.security;
import com.example.coworkingprojectback.login.JwtRequestFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        //Endpoints públicos
                        .requestMatchers("/salas/listar", "/salas/{id}", "/salas/nombre/{nombre}").permitAll()
                        //Endpoints privados
                        .requestMatchers("/salas/registrar", "/salas/actualizar", "/salas/eliminar/**").authenticated()
                        .requestMatchers("/usuarios/{id}","/usuarios/listar","/usuarios/registrar", "/usuarios/actualizar", "/usuarios/eliminar/**").authenticated()
                        .requestMatchers("/caracteristicas/listar", "/caracteristicas/{id}","/caracteristicas/registrar", "/caracteristicas/actualizar", "/caracteristicas/eliminar/**").authenticated()
                        .requestMatchers("/categorias/listar", "/categorias/{id}","/categorias/registrar", "/categorias/actualizar", "/categorias/eliminar/**").authenticated()

                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){return new BCryptPasswordEncoder();}

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


