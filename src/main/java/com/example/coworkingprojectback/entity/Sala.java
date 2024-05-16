package com.example.coworkingprojectback.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class Salas {
    @Id
    @GeneratedValue
    private Long id;
    private String nombre;
    private String descripcion;
    private int capacidad;
    private double precio;

}
