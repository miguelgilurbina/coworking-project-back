package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.SalaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sala")
public class SalaController {
    @Autowired
    private SalaService salaService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarSala(@RequestBody Sala sala) {
        Optional<Sala> salaExistente = salaService.buscarPorNombre(sala.getNombre());
        if (salaExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una sala con el mismo nombre");
        }
        return ResponseEntity.ok(salaService.registrarSala(sala));
    }

    @GetMapping("/salas")
    public ResponseEntity<List<Sala>> listarSalas() {
        return ResponseEntity.ok(salaService.listarSalas());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarSala(@RequestBody Sala sala) {
        Optional<Sala> salaBuscada = salaService.buscarPorNombre(sala.getNombre());
        if (salaBuscada.isPresent()) {
            salaService.actualizarSala(sala);
            return ResponseEntity.ok("Sala actualizada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la sala");
        }
    }

    // Manejo global de excepciones
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
