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

@Setter
@Getter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Sala {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    private int capacidad;

    @Column(nullable = false)
    private double precio;

    // Constructor sin id para cuando la id se genere automáticamente
    public Sala(String nombre, String descripcion, int capacidad, double precio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.capacidad = capacidad;
        this.precio = precio;
    }
}
