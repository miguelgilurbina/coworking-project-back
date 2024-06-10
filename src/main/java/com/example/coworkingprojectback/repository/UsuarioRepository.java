package com.example.coworkingprojectback.repository;

import com.example.coworkingprojectback.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
@Transactional
public interface UsuarioRepository extends JpaRepository<Usuario, Long>{
    Optional<Usuario> findByEmail(String email);

}
