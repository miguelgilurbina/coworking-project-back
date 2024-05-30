package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class CategoriaResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;

}
