package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private boolean esAdmin;

    public String getEmail() {
        return email;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }
}



