package com.example.coworkingprojectback.DTO.Out;

import java.util.List;

public class AuthResponseDTO {
    private String token;
    private List<String> roles;
    private UsuarioResponseDTO usuario;

    public AuthResponseDTO(String token, UsuarioResponseDTO usuario) {
        this.token = token;
        this.usuario = usuario;
    }

    // Getters y Setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    public UsuarioResponseDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioResponseDTO usuario) {
        this.usuario = usuario;
    }
}
