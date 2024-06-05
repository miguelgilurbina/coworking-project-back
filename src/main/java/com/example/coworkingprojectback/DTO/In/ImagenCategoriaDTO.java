package com.example.coworkingprojectback.DTO.In;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ImagenCategoriaDTO {
    private byte[] imagenBlob;
    private boolean imagenPrincipal;
}
