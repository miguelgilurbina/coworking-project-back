package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.CaracteristicaDTO;
import com.example.coworkingprojectback.DTO.Out.CaracteristicaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.CaracteristicaRequestToUpdate;

import java.util.List;

public interface ICaracteristicaService {
    CaracteristicaResponseDTO createCaracteristica(CaracteristicaDTO caracteristicaDTO);
    CaracteristicaResponseDTO getCaracteristicaById(Long id);
    List<CaracteristicaResponseDTO> getAllCaracteristicas();
    CaracteristicaResponseDTO updateCaracteristica(CaracteristicaRequestToUpdate caracteristicaRequestToUpdate);
    void deleteCaracteristica(Long id);
}
