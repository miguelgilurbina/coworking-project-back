package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.entity.ImagenSala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.Impl.ImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/imagen")
public class ImagenSalaController {

    @Autowired
    private ImagenService imagenService;

    @PostMapping("/upload")
    public ResponseEntity<com.example.coworkingprojectback.entity.ImagenSala> cargarImagen(@RequestParam("file") MultipartFile file) {
        try {
            ImagenSala imagenSala = new ImagenSala();
            imagenSala.setImagenBlob(file.getBytes());
            Imagen nuevaImagenSala = imagenService.(imagenSala);
            return ResponseEntity.status(HttpStatus.CREATED).body(nuevaImagenSala);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/imagenes")
    public ResponseEntity<List<Imagen>> listarImagenes() {
        List<Imagen> imagenes = imagenService.listarImagen();
        return ResponseEntity.ok(imagenes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Imagen> actualizarImagen(@PathVariable Long id, @RequestBody Imagen imagenSala) throws ResourceNotFoundException {
        Optional<Imagen> imagenBuscada = imagenService.buscarPorId(id);
        if (imagenBuscada.isPresent()) {
            imagenSala.setId(id);
            Imagen imagenSalaActualizada = imagenService.actualizarImagen(imagenSala);
            return ResponseEntity.ok(imagenSalaActualizada);
        } else {
            throw new ResourceNotFoundException("No se encontró la imagen con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarImagen(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Imagen> imagen = imagenService.buscarPorId(id);
        if (imagen.isPresent()) {
            imagenService.eliminarImagen(id);
            return ResponseEntity.noContent().build();
        } else {
            throw new ResourceNotFoundException("No se encontró la imagen con ID: " + id);
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
