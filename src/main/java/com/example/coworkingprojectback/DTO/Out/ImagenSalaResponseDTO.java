package com.example.coworkingprojectback.DTO.Out;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenSalaResponseDTO {
    private Long id;
    private byte[] imagenBlob;
    private boolean imagenPrincipal;
}