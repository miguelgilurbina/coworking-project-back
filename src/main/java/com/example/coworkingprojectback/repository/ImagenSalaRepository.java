package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.ImagenSala;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagenSalaRepository extends JpaRepository <ImagenSala, Long> {
}
