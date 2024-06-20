package com.example.coworkingprojectback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

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
    @JoinColumn(name = "sala_id")
    private Sala sala;

    @Lob
    @Column(name = "imagen_blob", columnDefinition="MEDIUMBLOB", nullable = false)
    private byte[] imagenBlob;

    @Column(nullable = false)
    private boolean imagenPrincipal;

}
