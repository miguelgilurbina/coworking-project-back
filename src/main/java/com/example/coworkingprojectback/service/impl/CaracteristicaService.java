package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.CaracteristicaDTO;
import com.example.coworkingprojectback.DTO.Out.CaracteristicaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.CaracteristicaRequestToUpdate;
import com.example.coworkingprojectback.entity.Caracteristica;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.CaracteristicaRepository;
import com.example.coworkingprojectback.service.ICaracteristicaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CaracteristicaService implements ICaracteristicaService {

    private final CaracteristicaRepository caracteristicaRepository;
    private final ObjectMapper objectMapper;

    public CaracteristicaService(CaracteristicaRepository caracteristicaRepository, ObjectMapper objectMapper) {
        this.caracteristicaRepository = caracteristicaRepository;
        this.objectMapper = objectMapper;
    }

    private final String NOT_FOUND_MESSAGE = "No se encontró caracteristica solicitada";

    @Override
    public CaracteristicaResponseDTO createCaracteristica(CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristica =mapToEntity(caracteristicaDTO);
        caracteristicaRepository.save(caracteristica);
        return mapToDTO(caracteristica);
    }

    private CaracteristicaResponseDTO mapToDTO(Caracteristica caracteristica) {
        return objectMapper.convertValue(caracteristica, CaracteristicaResponseDTO.class);

    }

    private Caracteristica mapToEntity(CaracteristicaDTO caracteristicaDTO) {
        return objectMapper.convertValue(caracteristicaDTO, Caracteristica.class);
    }

    @Override
    public CaracteristicaResponseDTO getCaracteristicaById(Long id) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(caracteristica);
    }

    @Override
    public List<CaracteristicaResponseDTO> getAllCaracteristicas() {
        return caracteristicaRepository.findAll().stream().map(this::mapToDTO).toList();
    }

    @Override
    public CaracteristicaResponseDTO updateCaracteristica(CaracteristicaRequestToUpdate caracteristicaRequestToUpdate) {
        getCaracteristicaById(caracteristicaRequestToUpdate.getId());
        return mapToDTO(caracteristicaRepository.save(mapToEntity(caracteristicaRequestToUpdate)));
    }
    public Caracteristica mapToEntity(CaracteristicaRequestToUpdate caracteristicaRequestToUpdate){
        return objectMapper.convertValue(caracteristicaRequestToUpdate, Caracteristica.class);
    }

    @Override
    public void deleteCaracteristica(Long id) {
        getCaracteristicaById(id);
        caracteristicaRepository.deleteById(id);

    }
}