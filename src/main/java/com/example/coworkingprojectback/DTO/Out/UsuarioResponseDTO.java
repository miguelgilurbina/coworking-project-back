package com.example.coworkingprojectback.DTO.Out;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasena;
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



