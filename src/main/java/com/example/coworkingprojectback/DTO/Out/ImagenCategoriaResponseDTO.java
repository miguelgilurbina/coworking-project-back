package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class ImagenCategoriaResponseDTO {
    private Long id;
    private byte[] imagenBlob;
    private boolean imagenPrincipal;

}

