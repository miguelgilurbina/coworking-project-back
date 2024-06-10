package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.ReservaDTO;
import com.example.coworkingprojectback.DTO.Out.ReservaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ReservaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Reserva;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.ReservaRepository;
import com.example.coworkingprojectback.service.IReservaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReservaService implements IReservaService {

    private final ReservaRepository reservaRepository;
    private final ObjectMapper objectMapper;

    public ReservaService(ReservaRepository reservaRepository, ObjectMapper objectMapper) {
        this.reservaRepository = reservaRepository;
        this.objectMapper = objectMapper;
    }


    @Override
    public ReservaResponseDTO registrarReserva(ReservaDTO reservaDTO) {
        Reserva reserva = mapToEntity(reservaDTO);
        reservaRepository.save(reserva);
        return mapToDto(reserva);
    }

    private Reserva mapToEntity(ReservaDTO reservaDTO) {
        return null;
    }

    private ReservaResponseDTO mapToDto(Reserva reserva) {
        return null;
    }

    @Override
    public ReservaResponseDTO actualizarReserva(ReservaRequestToUpdateDTO reservaRequestToUpdateDTO) {
        return null;
    }

    @Override
    public List<ReservaResponseDTO> listarReservas() {
        return List.of();
    }

    @Override
    public ReservaResponseDTO buscarPorNombre(String nombre) {
        return null;
    }

    @Override
    public ReservaResponseDTO buscarPorId(Long id) {
        return null;
    }

    @Override
    public void eliminarReserva(Long id) throws ResourceNotFoundException {

    }
}
