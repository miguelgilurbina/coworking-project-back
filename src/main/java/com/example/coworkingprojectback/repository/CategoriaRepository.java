package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT s FROM Categoria s WHERE s.nombre = :nombre")
    Optional<Categoria> buscarPorNombre(String nombre);
}
