package com.example.coworkingprojectback.DTO.In;

import lombok.Data;

@Data
public class UsuarioRequestDTO {
    private String email;
    private String nombre;
    private String apellido;
    private String password;
    private String rol;
}
