package com.example.coworkingprojectback.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "imagenes_categorias")
public class ImagenCategoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Lob
    @Column(name = "imagen_blob", columnDefinition="MEDIUMBLOB", nullable = false)
    private byte[] imagenBlob;

    @Column(nullable = false)
    private boolean imagenPrincipal;

}
