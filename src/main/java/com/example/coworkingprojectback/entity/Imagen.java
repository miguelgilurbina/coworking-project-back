package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Blob;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Imagen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Lob
    @Column(name = "imagen_blob", nullable = false)
    private Blob imagenBlob;

    @Column(name = "imagen_principal", nullable = false, columnDefinition = "tinyint default 0")
    private boolean imagenPrincipal;

}

