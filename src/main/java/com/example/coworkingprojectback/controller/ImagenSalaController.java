package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.ImagenSalaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenSalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenSalaRequestToUpdateDTO;
import com.example.coworkingprojectback.service.IImagenSalaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/imagenes_salas")
public class ImagenSalaController {
    private final IImagenSalaService imagenSalaService;


    public ImagenSalaController(IImagenSalaService imagenSalaService) {
        this.imagenSalaService = imagenSalaService;
    }
    @PostMapping
    public ResponseEntity<ImagenSalaResponseDTO> registrarImagenSala(@RequestBody ImagenSalaDTO imagenSalaDTO){
        return ResponseEntity.status(HttpStatus.CREATED).body(imagenSalaService.registrarImagenSala(imagenSalaDTO));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ImagenSalaResponseDTO> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok(imagenSalaService.buscarPorId(id));
    }
    @GetMapping
    public ResponseEntity<ImagenSalaResponseDTO> actualizarImagenSala(@RequestBody ImagenSalaRequestToUpdateDTO ima)
}
