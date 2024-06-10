package com.example.coworkingprojectback.controller;
import com.example.coworkingprojectback.DTO.In.SalaDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.SalaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.ISalaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/salas")
public class SalaController {

    private final ISalaService salaService;

    public SalaController(ISalaService salaService) {
        this.salaService = salaService;
    }

    // Public endpoints
    @GetMapping("/{id}")
    public ResponseEntity<SalaResponseDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(salaService.buscarPorId(id));
    }

    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<SalaResponseDTO> buscarPorNombre(@PathVariable String nombre) throws ResourceNotFoundException {
        return ResponseEntity.ok(salaService.buscarPorNombre(nombre));
    }

    @GetMapping("/listar")
    public ResponseEntity<List<SalaResponseDTO>> listarSalas() {
        return ResponseEntity.ok(salaService.listarSalas());
    }

    // Private endpoints
    @PostMapping("/registrar")
    public ResponseEntity<SalaResponseDTO> registrarSala(@Valid @RequestBody SalaDTO salaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(salaService.registrarSala(salaDTO));
    }

    @Transactional
    @PutMapping("/actualizar")
    public ResponseEntity<SalaResponseDTO> actualizarSala(@Valid @RequestBody SalaRequestToUpdateDTO salaDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(salaService.actualizarSala(salaDTO));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarSala(@PathVariable Long id) throws ResourceNotFoundException {
        salaService.eliminarSala(id);
        return new ResponseEntity<>("Sala eliminada", HttpStatus.OK);
    }
}
