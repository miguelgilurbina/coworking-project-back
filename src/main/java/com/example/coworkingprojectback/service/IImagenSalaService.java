package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.ImagenSalaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenSalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenSalaRequestToUpdateDTO;

import java.util.List;

public interface IImagenSalaService {
    ImagenSalaResponseDTO registrarImagenSala(ImagenSalaDTO salaDTO);
    ImagenSalaResponseDTO buscarPorId(Long id);
    ImagenSalaResponseDTO actualizarImagenSala(ImagenSalaRequestToUpdateDTO imagenSalaRequestToUpdateDTO);
    List<ImagenSalaResponseDTO> listarImagenSala();
    void borrarImagenSala(Long id);
};
