package com.example.coworkingprojectback.DTO.Out;

import com.example.coworkingprojectback.entity.Usuario;
import lombok.Data;

@Data
public class UsuarioResponseDTO {
    private Long id;
    private String email;
    private String nombre;
    private String apellido;
    private String rol;




}




