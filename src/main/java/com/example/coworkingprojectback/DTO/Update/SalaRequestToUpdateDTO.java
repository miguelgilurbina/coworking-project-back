package com.example.coworkingprojectback.DTO.Update;

import com.example.coworkingprojectback.DTO.In.CategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
@AllArgsConstructor
public class SalaRequestToUpdateDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private BigDecimal precio;
    private CategoriaDTO categoria;

    public Long getId() {
        return id;
    }
}
