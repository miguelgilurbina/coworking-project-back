package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String apellido;

    @Column(nullable = false)
    private String contraseña;

    // Constructor sin id para cuando la id se genere automáticamente

    public Usuario(String nombre, String email, String apellido, String contraseña) {
        this.nombre = nombre;
        this.email = email;
        this.apellido = apellido;
        this.contraseña = contraseña;
    }
}
