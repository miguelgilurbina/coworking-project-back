package com.example.coworkingprojectback.DTO.Update;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor

public class ReservaRequestToUpdateDTO{
    private Long id;
    private Date fechaInicio;
    private Date fechaFin;

    public Long getId() {
        return id;
    }
}