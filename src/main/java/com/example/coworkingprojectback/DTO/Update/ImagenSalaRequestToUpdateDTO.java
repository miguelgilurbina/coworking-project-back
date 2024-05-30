package com.example.coworkingprojectback.DTO.Update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ImagenSalaRequestToUpdateDTO {
    private Long id;
    private byte[] imagenBlob;
    private boolean imagenPrincipal;

    public Long getId() {
        return id;
    }
}
