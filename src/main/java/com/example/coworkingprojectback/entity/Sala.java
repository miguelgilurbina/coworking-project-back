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

    @Column(unique = true, nullable = false, length = 50)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String descripcion;

    @Column(nullable = false, columnDefinition="TINYINT UNSIGNED")
    private int capacidad;

    @Column(nullable = false, precision = 4, scale = 2)
    private BigDecimal precio;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
}
