package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Blob;
import java.util.Optional;

@Repository
public interface ImagenRepository extends JpaRepository <Imagen, Integer> {
}
