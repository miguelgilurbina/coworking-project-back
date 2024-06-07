package com.example.coworkingprojectback.DTO.In;

import javax.persistence.*;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
public class ReservaDTO {
    private Date fechaInicio;
    private Date fechaFin;
}