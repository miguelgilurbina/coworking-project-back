package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.ImagenSalaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenSalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenSalaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.IImagenSalaService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/imagenes_salas")
public class ImagenSalaController {

    private IImagenSalaService imagenSalaService;

    @PostMapping("/registrar")
    public ResponseEntity<ImagenSalaResponseDTO> registrarImagenSala(@RequestBody ImagenSalaDTO imagenSalaDTO) {
        ImagenSalaResponseDTO responseDTO = imagenSalaService.registrarImagenSala(imagenSalaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ImagenSalaResponseDTO> buscarPorId(@PathVariable Long id) {
        ImagenSalaResponseDTO responseDTO = imagenSalaService.buscarPorId(id);
        return ResponseEntity.ok(responseDTO);
    }
    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity<ImagenSalaResponseDTO> actualizarImagenSala(@RequestBody ImagenSalaRequestToUpdateDTO imagenSalaDTO) throws ResourceNotFoundException {
        return ResponseEntity.ok(imagenSalaService.actualizarImagenSala(imagenSalaDTO));
    }
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> borrarImagenSala(@PathVariable Long id) {
        imagenSalaService.borrarImagenSala(id);
        return ResponseEntity.noContent().build();
    }


}
