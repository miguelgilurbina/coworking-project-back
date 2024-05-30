package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @JoinColumn(name = "categoria_id", referencedColumnName = "id", nullable = false)
    private Categoria categoria;

    @Lob
    @Column(name = "imagen_blob", nullable = false)
    private byte[] imagenBlob;

    @Column(name = "imagen_principal", nullable = false, columnDefinition = "TINYINT(1) DEFAULT '0'")
    private boolean imagenPrincipal;
}
