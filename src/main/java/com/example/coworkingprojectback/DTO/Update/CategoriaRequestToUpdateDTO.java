package com.example.coworkingprojectback.DTO.Update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CategoriaRequestToUpdateDTO {
    private Long id;
    private String nombre;
    private String descripcion;

    public Long getId() {
        return id;
    }
}
