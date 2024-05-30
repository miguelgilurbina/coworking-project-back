package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "imagenes_salas")
public class ImagenSala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;

    @Lob
    @Column(name = "imagen_blob", columnDefinition = "MEDIUMBLOB")
    private byte[] imagenBlob;

    @Column(name = "imagen_principal", nullable = false, columnDefinition = "tinyint default 0")
    private boolean imagenPrincipal;






}
