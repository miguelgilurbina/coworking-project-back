package com.example.coworkingprojectback.DTO.In;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
@Setter
@Getter
@AllArgsConstructor
public class SalaDTO {
    private String nombre;
    private String descripcion;
    private int capacidad;
    private BigDecimal precio;
    private CategoriaDTO categoria;

}
