package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.SalaDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.SalaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.ISalaService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/salas")
public class SalaController {
    private final ISalaService salaService;

    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }
    @PostMapping
    @Transactional
    public ResponseEntity<SalaResponseDTO> registrarSala(@Validated @RequestBody SalaDTO salaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.registrarSala(salaDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<SalaResponseDTO> buscarPorNombre(@PathVariable String nombre) throws ResourceNotFoundException {
        return ResponseEntity.ok(salaService.buscarPorNombre(nombre));
    }
    @GetMapping("/salas")
    public ResponseEntity<List<SalaResponseDTO>> listarSalas() {
        return ResponseEntity.ok(salaService.listarSalas());
    }
    @PutMapping
    public ResponseEntity<SalaResponseDTO> actualizarSala(@RequestBody SalaRequestToUpdateDTO salaDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(salaService.actualizarSala(salaDTO));
    }
    @DeleteMapping
    public ResponseEntity<Void> eliminarSala(@PathVariable Long id) throws ResourceNotFoundException{
        salaService.eliminarSala(id);
        return ResponseEntity.noContent().build();
    }
}
