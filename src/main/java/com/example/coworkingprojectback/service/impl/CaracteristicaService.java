package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.CaracteristicaDTO;
import com.example.coworkingprojectback.DTO.Out.CaracteristicaResponseDTO;
import com.example.coworkingprojectback.entity.Caracteristica;
import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.CaracteristicaRepository;
import com.example.coworkingprojectback.repository.CategoriaRepository;
import com.example.coworkingprojectback.repository.SalaRepository;
import com.example.coworkingprojectback.service.ICaracteristicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CaracteristicaService implements ICaracteristicaService {

    @Autowired
    private CaracteristicaRepository caracteristicaRepository;

    @Autowired
    private SalaRepository salaRepository;

    @Override
    public CaracteristicaResponseDTO createCaracteristica(CaracteristicaDTO caracteristicaDTO) {
        Sala sala = salaRepository.findById(caracteristicaDTO.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        Caracteristica caracteristica = new Caracteristica();
        caracteristica.setSala(sala);
        caracteristica.setCaracteristica(caracteristicaDTO.getCaracteristica());
        caracteristica = caracteristicaRepository.save(caracteristica);
        return new CaracteristicaResponseDTO(caracteristica.getId(), caracteristica.getSala().getId(), caracteristica.getCaracteristica());
    }

    @Override
    public CaracteristicaResponseDTO getCaracteristicaById(Long id) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caracteristica no encontrada"));
        return new CaracteristicaResponseDTO(caracteristica.getId(), caracteristica.getSala().getId(), caracteristica.getCaracteristica());
    }

    @Override
    public List<CaracteristicaResponseDTO> getAllCaracteristicas() {
        return caracteristicaRepository.findAll().stream()
                .map(caracteristica -> new CaracteristicaResponseDTO(caracteristica.getId(), caracteristica.getSala().getId(), caracteristica.getCaracteristica()))
                .collect(Collectors.toList());
    }

    @Override
    public CaracteristicaResponseDTO updateCaracteristica(Long id, CaracteristicaDTO caracteristicaDTO) {
        Caracteristica caracteristica = caracteristicaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Caracteristica no encontrada"));

        Sala sala = salaRepository.findById(caracteristicaDTO.getSalaId())
                .orElseThrow(() -> new ResourceNotFoundException("Categoria no encontrada"));

        caracteristica.setSala(sala);
        caracteristica.setCaracteristica(caracteristicaDTO.getCaracteristica());
        caracteristica = caracteristicaRepository.save(caracteristica);
        return new CaracteristicaResponseDTO(caracteristica.getId(), caracteristica.getSala().getId(), caracteristica.getCaracteristica());
    }

    @Override
    public void deleteCaracteristica(Long id) {
        caracteristicaRepository.deleteById(id);
    }
}