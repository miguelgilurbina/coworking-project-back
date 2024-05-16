package com.example.coworkingprojectback.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Blob;

@Getter
@Setter
public class ReservaDTO {
    public Long id;
    private Long salId;
    private String salaNombre;
    private String salaDescripcion;
    private int salaCapacidad;
    private double salaPrecio;
    private Long imagenId;
    private Blob imagenImagenURL;
    private boolean imagenImagenPrincipal;

    public ReservaDTO(Long id, Long salId, String salaDescripcion, String salaNombre, int salaCapacidad, double salaPrecio, Long imagenId, Blob imagenImagenURL, boolean imagenImagenPrincipal) {
        this.id = id;
        this.salId = salId;
        this.salaDescripcion = salaDescripcion;
        this.salaNombre = salaNombre;
        this.salaCapacidad = salaCapacidad;
        this.salaPrecio = salaPrecio;
        this.imagenId = imagenId;
        this.imagenImagenURL = imagenImagenURL;
        this.imagenImagenPrincipal = imagenImagenPrincipal;
    }
}
