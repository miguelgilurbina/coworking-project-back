package com.example.coworkingprojectback.DTO.Update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CaracteristicaRequestToUpdate {
    private Long id;
    private String caracteristica;
    public Long getId() {return id;}

}
