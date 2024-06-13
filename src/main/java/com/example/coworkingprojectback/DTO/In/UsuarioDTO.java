package com.example.coworkingprojectback.DTO.In;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDTO {
    private String email;
    private String nombre;
    private String apellido;
    private String rol;
}
