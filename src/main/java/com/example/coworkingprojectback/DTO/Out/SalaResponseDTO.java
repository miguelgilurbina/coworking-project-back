package com.example.coworkingprojectback.DTO.Out;

import com.example.coworkingprojectback.DTO.In.CategoriaDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter
@AllArgsConstructor
public class SalaResponseDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private BigDecimal precio;
    private CategoriaDTO categoria;

}
