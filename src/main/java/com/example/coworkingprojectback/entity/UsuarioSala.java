package com.example.coworkingprojectback.entity;
import java.io.Serializable;
import javax.persistence.Embeddable;

@Embeddable
public class UsuarioSala implements Serializable {
    private Long idUsuario;
    private Long idSala;

    // Constructor, getters y setters

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UsuarioSala that = (UsuarioSala) o;
        if (!idUsuario.equals(that.idUsuario)) return false;
        return idSala.equals(that.idSala);
    }

    @Override
    public int hashCode() {
        int result = idUsuario.hashCode();
        result = 31 * result + idSala.hashCode();
        return result;
    }
}
