package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.Imagen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IImagenRepository extends JpaRepository <Imagen, Long> {
}
