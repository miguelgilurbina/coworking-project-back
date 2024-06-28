package com.example.coworkingprojectback.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SeguridadConfig extends WebSecurityConfigurerAdapter {

    private final JwtAutenticacionDeEntrada jwtAutenticacionDeEntrada;

    public SeguridadConfig(JwtAutenticacionDeEntrada jwtAutenticacionDeEntrada) {
        this.jwtAutenticacionDeEntrada = jwtAutenticacionDeEntrada;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public JwtFiltroDeAutenticacion jwtFiltroDeAutenticacion() {
        return new JwtFiltroDeAutenticacion();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors().and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAutenticacionDeEntrada)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/api/auth/**", "/api/auth/verify").permitAll()
                .antMatchers(HttpMethod.POST, "/salas/registrar").authenticated()
                .antMatchers(HttpMethod.DELETE,"/salas/**").authenticated()
                .antMatchers(HttpMethod.GET, "/salas/listar").permitAll()
                .antMatchers(HttpMethod.PUT, "/salas/**").authenticated()
                .antMatchers(HttpMethod.POST, "/salas/listar").permitAll()
                .antMatchers(HttpMethod.POST, "/usuarios/registrar").permitAll()
                .antMatchers(HttpMethod.GET, "/usuarios/**").authenticated()
                .antMatchers(HttpMethod.DELETE, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.PUT, "/usuarios/**").hasAuthority("ADMINISTRADOR")
                .antMatchers(HttpMethod.POST, "/conectarse").permitAll()
                .antMatchers(HttpMethod.POST, "/registrarAdmin").permitAll()
                .antMatchers(HttpMethod.GET, "/caracteristicas/**").permitAll()
                .antMatchers(HttpMethod.GET, "/categorias/**").permitAll()
                .antMatchers(HttpMethod.POST, "/categorias/nuevo").authenticated()
                .anyRequest().authenticated();

        http.addFilterBefore(jwtFiltroDeAutenticacion(), UsernamePasswordAuthenticationFilter.class);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOrigin("http://localhost:5173");
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}