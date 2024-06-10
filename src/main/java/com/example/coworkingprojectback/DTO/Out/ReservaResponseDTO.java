package com.example.coworkingprojectback.DTO.Out;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor

public class ReservaResponseDTO{
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;
}