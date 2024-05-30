package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CaracteristicaResponseDTO {
    private Long id;
    private Long categoriaId;
    private String caracteristica;
}
