package com.example.coworkingprojectback.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Blob imagenURL;

    @Column(nullable = false)
    private Boolean imagenPrincipal;

    // Constructor sin id para cuando la id se genere automáticamente
    public Imagen(Blob imagenURL, Boolean imagenPrincipal) {
        this.imagenURL = imagenURL;
        this.imagenPrincipal = imagenPrincipal;
    }
}
