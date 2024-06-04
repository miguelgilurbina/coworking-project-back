package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.SalaDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.SalaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;

import java.util.List;

public interface ISalaService {
    SalaResponseDTO registrarSala(SalaDTO salaDTO);
    SalaResponseDTO actualizarSala(SalaRequestToUpdateDTO salaRequestToUpdateDTO);
    List<SalaResponseDTO> listarSalas();
    SalaResponseDTO buscarPorNombre(String nombre);
    SalaResponseDTO buscarPorId(Long id);
    void eliminarSala(Long id) throws ResourceNotFoundException;
}
