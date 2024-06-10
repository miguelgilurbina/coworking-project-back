package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.ImagenCategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenCategoriaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenCategoriaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.IImagenCategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/imagenes-categorias")
public class ImagenCategoriaController {

    private final IImagenCategoriaService imagenCategoriaService;

    public ImagenCategoriaController(IImagenCategoriaService imagenCategoriaService) {
        this.imagenCategoriaService = imagenCategoriaService;
    }


    @PostMapping("/registrar")
    public ResponseEntity<ImagenCategoriaResponseDTO> createImagenCategoria( @RequestBody ImagenCategoriaDTO imagenCategoriaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenCategoriaService.createImagenCategoria(imagenCategoriaDTO));

    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenCategoriaResponseDTO> getImagenCategoriaById(@PathVariable Long id) {
        ImagenCategoriaResponseDTO imagenCategoria = imagenCategoriaService.getImagenCategoriaById(id);
        return ResponseEntity.ok(imagenCategoria);
    }

    @GetMapping("/listar")
    public ResponseEntity<List<ImagenCategoriaResponseDTO>> getAllImagenesCategoria() {
        List<ImagenCategoriaResponseDTO> imagenesCategoria = imagenCategoriaService.getAllImagenesCategoria();
        return ResponseEntity.ok(imagenesCategoria);
    }

    @PutMapping("actualizar")
    public ResponseEntity<ImagenCategoriaResponseDTO> updateImagenCategoria(@RequestBody ImagenCategoriaRequestToUpdateDTO imagenCategoriaDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(imagenCategoriaService.updateImagenCategoria(imagenCategoriaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteImagenCategoria(@PathVariable Long id) {
        imagenCategoriaService.deleteImagenCategoria(id);
        return ResponseEntity.noContent().build();
    }
}