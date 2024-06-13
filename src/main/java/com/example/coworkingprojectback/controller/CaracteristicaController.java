package com.example.coworkingprojectback.controller;
import com.example.coworkingprojectback.DTO.In.CaracteristicaDTO;
import com.example.coworkingprojectback.DTO.Out.CaracteristicaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.CaracteristicaRequestToUpdate;
import com.example.coworkingprojectback.DTO.Update.CategoriaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.ICaracteristicaService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


import java.util.List;
@AllArgsConstructor
@RestController
@RequestMapping("/caracteristicas")
public class CaracteristicaController {

    @Autowired
    private ICaracteristicaService caracteristicaService;

    @Secured("ROLE_ADMIN")
    @PostMapping("/registrar")
    public ResponseEntity<CaracteristicaResponseDTO> createCaracteristica( @RequestBody CaracteristicaDTO caracteristicaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(caracteristicaService.createCaracteristica(caracteristicaDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CaracteristicaResponseDTO> getCaracteristicaById(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(caracteristicaService.getCaracteristicaById(id));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<CaracteristicaResponseDTO>> getAllCaracteristicas() {
        return ResponseEntity.ok(caracteristicaService.getAllCaracteristicas());
    }

    @PutMapping("/{id}")
    public ResponseEntity<CaracteristicaResponseDTO> updateCaracteristica(@RequestBody CaracteristicaRequestToUpdate caracteristicaDTO) {
        return ResponseEntity.ok(caracteristicaService.updateCaracteristica(caracteristicaDTO)); }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCaracteristica(@PathVariable Long id) {
        caracteristicaService.deleteCaracteristica(id);
        return ResponseEntity.noContent().build();
    }
}