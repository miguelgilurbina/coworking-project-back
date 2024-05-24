package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.Sala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SalaRepository extends JpaRepository<Sala, Integer> {
    @Query("SELECT s FROM Sala s WHERE s.nombre = :nombre")
    Optional<Sala> buscarPorNombre(String nombre);
}
