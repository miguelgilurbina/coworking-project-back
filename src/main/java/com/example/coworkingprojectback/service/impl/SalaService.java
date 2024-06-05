package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.SalaDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.SalaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.SalaRepository;
import com.example.coworkingprojectback.service.ISalaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
public class SalaService implements ISalaService {

    private final SalaRepository salaRepository;
    private final ObjectMapper objectMapper;
    public SalaService(SalaRepository salaRepository, ObjectMapper objectMapper) {
        this.salaRepository = salaRepository;
        this.objectMapper = objectMapper;
    }
    private final String NOT_FOUND_MESSAGE = "No se encontró la sala solicitada";


    @Override
    public SalaResponseDTO registrarSala(SalaDTO salaDTO) {
        Sala sala = mapToEntity(salaDTO);
        salaRepository.save(sala);
        return mapToDTO(sala);
    }
    @Override
    @Transactional
    public SalaResponseDTO actualizarSala(SalaRequestToUpdateDTO salaRequestToUpdateDTO){
        buscarPorId(salaRequestToUpdateDTO.getId());
        return mapToDTO(salaRepository.save(mapToEntity(salaRequestToUpdateDTO)));
    }
    @Override
    public List<SalaResponseDTO> listarSalas() {
        return salaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
     public SalaResponseDTO buscarPorNombre(String nombre){
         Sala sala = (Sala) salaRepository.findByNombre(nombre).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
         return mapToDTO(sala);
     }
    @Override
    public SalaResponseDTO buscarPorId(Long id)  {
        Sala sala = salaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(sala);
    }

    @Override
    public void eliminarSala(Long id) throws ResourceNotFoundException {
        buscarPorId(id);
        salaRepository.deleteById(id);

    }

    private SalaResponseDTO mapToDTO(Sala sala) {
        return objectMapper.convertValue(sala, SalaResponseDTO.class);
    }
    private Sala mapToEntity(SalaDTO salaDTO){
        return objectMapper.convertValue(salaDTO, Sala.class);
    }
    private Sala mapToEntity(SalaRequestToUpdateDTO salaRequestToUpdateDTO){
        return objectMapper.convertValue(salaRequestToUpdateDTO, Sala.class);
    }

}
