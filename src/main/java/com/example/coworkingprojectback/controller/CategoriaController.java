package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.CategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.CategoriaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.CategoriaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.ICategoriaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/api/categorias")
public class CategoriaController {


    private ICategoriaService categoriaService;

    @PostMapping("/registrar")
    public ResponseEntity<CategoriaResponseDTO> createCategoria(@RequestBody CategoriaDTO categoriaDTO) {
        CategoriaResponseDTO createdCategoria = categoriaService.createCategoria(categoriaDTO);
        return ResponseEntity.ok(createdCategoria);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaResponseDTO> getCategoriaById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaService.getCategoriaById(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CategoriaResponseDTO>> getAllCategorias() {
        return ResponseEntity.ok(categoriaService.getAllCategorias());
    }

    @PutMapping("actualizar")
    public ResponseEntity<CategoriaResponseDTO> updateCategoria(@RequestBody CategoriaRequestToUpdateDTO categoriaDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(categoriaService.updateCategoria(categoriaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> deleteCategoria(@PathVariable Long id) {
        categoriaService.deleteCategoria(id);
        return ResponseEntity.noContent().build();
    }


}


