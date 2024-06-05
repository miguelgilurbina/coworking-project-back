package com.example.coworkingprojectback.DTO.Update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioRequestToUpdateDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String contrasena;
    private boolean esAdmin;

    public Long getId() {
        return id;
    }
}
