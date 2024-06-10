package com.example.coworkingprojectback.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "usuarios_salas")
public class UsuarioSala {
    @Id
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "sala_id", nullable = false)
    private Sala sala;
}
