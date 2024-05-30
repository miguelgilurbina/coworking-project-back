package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CaracteristicaResponseDTO {
    private int id;
    private Long salaId;
    private String caracteristica;
}
