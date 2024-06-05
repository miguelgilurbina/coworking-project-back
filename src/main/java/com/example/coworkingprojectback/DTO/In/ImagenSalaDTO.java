package com.example.coworkingprojectback.DTO.In;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenSalaDTO {
    private byte[] imagenBlob;
    private boolean imagenPrincipal;
}
