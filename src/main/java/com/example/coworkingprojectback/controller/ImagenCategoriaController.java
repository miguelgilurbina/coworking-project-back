package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.ImagenCategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenCategoriaResponseDTO;
import com.example.coworkingprojectback.service.IImagenCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/imagenes-categorias")
public class ImagenCategoriaController {

    @Autowired
    private IImagenCategoriaService imagenCategoriaService;

    @PostMapping
    public ResponseEntity<ImagenCategoriaResponseDTO> createImagenCategoria( @RequestBody ImagenCategoriaDTO imagenCategoriaDTO) {
        ImagenCategoriaResponseDTO createdImagenCategoria = imagenCategoriaService.createImagenCategoria(imagenCategoriaDTO);
        return ResponseEntity.ok(createdImagenCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenCategoriaResponseDTO> getImagenCategoriaById(@PathVariable Long id) {
        ImagenCategoriaResponseDTO imagenCategoria = imagenCategoriaService.getImagenCategoriaById(id);
        return ResponseEntity.ok(imagenCategoria);
    }

    @GetMapping
    public ResponseEntity<List<ImagenCategoriaResponseDTO>> getAllImagenesCategoria() {
        List<ImagenCategoriaResponseDTO> imagenesCategoria = imagenCategoriaService.getAllImagenesCategoria();
        return ResponseEntity.ok(imagenesCategoria);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ImagenCategoriaResponseDTO> updateImagenCategoria(@PathVariable Long id,@RequestBody ImagenCategoriaDTO imagenCategoriaDTO) {
        ImagenCategoriaResponseDTO updatedImagenCategoria = imagenCategoriaService.updateImagenCategoria(id, imagenCategoriaDTO);
        return ResponseEntity.ok(updatedImagenCategoria);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteImagenCategoria(@PathVariable Long id) {
        imagenCategoriaService.deleteImagenCategoria(id);
        return ResponseEntity.noContent().build();
    }
}