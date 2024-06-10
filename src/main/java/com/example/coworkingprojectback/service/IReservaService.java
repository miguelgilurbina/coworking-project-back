package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.ReservaDTO;
import com.example.coworkingprojectback.DTO.Out.ReservaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ReservaRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;

import java.util.List;

public interface IReservaService {
    ReservaResponseDTO registrarReserva(ReservaDTO reservaDTO);
    ReservaResponseDTO actualizarReserva(ReservaRequestToUpdateDTO reservaRequestToUpdateDTO);
    List<ReservaResponseDTO> listarReservas();
    ReservaResponseDTO buscarPorNombre(String nombre);
    ReservaResponseDTO buscarPorId(Long id);
    void eliminarReserva(Long id) throws ResourceNotFoundException;
}