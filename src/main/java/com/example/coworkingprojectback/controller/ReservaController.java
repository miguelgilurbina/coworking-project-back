package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.ReservaDTO;
import com.example.coworkingprojectback.DTO.Out.ReservaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ReservaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Reserva;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.IReservaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
@AllArgsConstructor
@RestController
@RequestMapping("/Reserva")
public class ReservaController {

    private final IReservaService reservaService;

    @PostMapping("/registrar")
    @Transactional
    public ResponseEntity<ReservaResponseDTO> registrarReserva(@Valid @RequestBody ReservaDTO reservaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservaService.registrarReserva(reservaDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ReservaResponseDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return ResponseEntity.ok(reservaService.buscarPorId(id));
    }
    @GetMapping("/{nombre}")
    public ResponseEntity<ReservaResponseDTO> buscarPorNombre(@PathVariable String nombre) throws ResourceNotFoundException {
        return ResponseEntity.ok(reservaService.buscarPorNombre(nombre));
    }
    @GetMapping("/listar")
    public ResponseEntity<List<ReservaResponseDTO>> listarReserva() {
        return ResponseEntity.ok(reservaService.listarReserva());
    }
    @Transactional
    @PutMapping("actualizar")
    public ResponseEntity<ReservaResponseDTO> actualizarReserva(@Valid @RequestBody ReservaRequestToUpdateDTO reservaDTO) throws ResourceNotFoundException{
        return ResponseEntity.ok(reservaService.actualizarReserva(reservaDTO));
    }
    @DeleteMapping("/eliminar{id}")
    public ResponseEntity<String> eliminarReserva(@PathVariable Long id) throws ResourceNotFoundException{
        reservaService.eliminarReserva(id);
        return new ResponseEntity<>("Reserva eliminada", HttpStatus.OK);
    }
}
