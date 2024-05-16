package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.entity.Imagen;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.ImagenService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagen")
public class ImagenController {
    @Autowired
    private ImagenService imagenService;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarImagen(@RequestBody Imagen imagen) {
        Optional<Imagen> imagenExistente = imagenService.buscarPorId(imagen.getId());
        if (imagenExistente.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Ya existe una imagen con el mismo ID");
        }
        return ResponseEntity.ok(imagenService.registrarImagen(imagen));
    }

    @GetMapping("/imagenes")
    public ResponseEntity<List<Imagen>> listarImagenes() {
        return ResponseEntity.ok(imagenService.listarImagen());
    }

    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarImagen(@RequestBody Imagen imagen) {
        Optional<Imagen> imagenBuscada = imagenService.buscarPorId(imagen.getId());
        if (imagenBuscada.isPresent()) {
            imagenService.actualizarImagen(imagen);
            return ResponseEntity.ok("Imagen actualizada");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontró la imagen");
        }
    }

    // Manejo global de excepciones
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
