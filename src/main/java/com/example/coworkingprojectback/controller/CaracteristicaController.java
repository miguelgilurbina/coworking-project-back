package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.CaracteristicaDTO;
import com.example.coworkingprojectback.DTO.Out.CaracteristicaResponseDTO;
import com.example.coworkingprojectback.service.ICaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@RestController
@RequestMapping("/api/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private ICaracteristicaService caracteristicaService;

    @PostMapping
    public ResponseEntity<CaracteristicaResponseDTO> createCaracteristica( @RequestBody CaracteristicaDTO caracteristicaDTO) {
        CaracteristicaResponseDTO createdCaracteristica = caracteristicaService.createCaracteristica(caracteristicaDTO);
        return ResponseEntity.ok(createdCaracteristica);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaResponseDTO> getCaracteristicaById(@PathVariable Long id) {
        CaracteristicaResponseDTO caracteristica = caracteristicaService.getCaracteristicaById(id);
        return ResponseEntity.ok(caracteristica);
    }

    @GetMapping
    public ResponseEntity<List<CaracteristicaResponseDTO>> getAllCaracteristicas() {
        List<CaracteristicaResponseDTO> caracteristicas = caracteristicaService.getAllCaracteristicas();
        return ResponseEntity.ok(caracteristicas);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaResponseDTO> updateCaracteristica(@PathVariable Long id, @RequestBody CaracteristicaDTO caracteristicaDTO) {
        CaracteristicaResponseDTO updatedCaracteristica = caracteristicaService.updateCaracteristica(id, caracteristicaDTO);
        return ResponseEntity.ok(updatedCaracteristica);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        caracteristicaService.deleteCaracteristica(id);
        return ResponseEntity.noContent().build();
    }
}