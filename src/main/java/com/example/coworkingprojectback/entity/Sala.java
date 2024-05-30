package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "salas")
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    private Categoria categoria;

    @Column(name = "capacidad", nullable = false, columnDefinition = "TINYINT UNSIGNED")
    private Integer capacidad;

    @Column(name = "precio", nullable = false, columnDefinition = "DECIMAL(4,2)")
    private BigDecimal precio;

    @OneToMany(mappedBy = "sala", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ImagenSala> imagenes = new ArrayList<>();

    // Constructor sin id para cuando la id se genere automáticamente
    public Sala(String nombre, String descripcion, int capacidad, double precio, Categoria categoria) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precio = BigDecimal.valueOf(precio);
        this.categoria = categoria;
    }
}
