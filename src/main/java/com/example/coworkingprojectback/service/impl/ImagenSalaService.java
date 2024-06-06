package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.ImagenSalaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenSalaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenSalaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.ImagenSala;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.ImagenSalaRepository;
import com.example.coworkingprojectback.service.IImagenSalaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ImagenSalaService implements IImagenSalaService {
    private final ImagenSalaRepository imagenSalaRepository;
    private final ObjectMapper objectMapper;

    public ImagenSalaService(ImagenSalaRepository imagenSalaRepository, ObjectMapper objectMapper) {
        this.imagenSalaRepository = imagenSalaRepository;
        this.objectMapper = objectMapper;
    }
    private final String NOT_FOUND_MESSAGE = "No se encontró imagen de sala";



    @Override
    public ImagenSalaResponseDTO registrarImagenSala(ImagenSalaDTO imagenSalaDTO) {
        ImagenSala imagenSala = mapToEntity(imagenSalaDTO);
        imagenSalaRepository.save(imagenSala);
        return mapToDTO(imagenSala);
    }
    private ImagenSalaResponseDTO mapToDTO(ImagenSala imagenSala) {
        return objectMapper.convertValue(imagenSala, ImagenSalaResponseDTO.class);


    }

    private ImagenSala mapToEntity(ImagenSalaDTO imagenSalaDTO) {
        return objectMapper.convertValue(imagenSalaDTO, ImagenSala.class);
    }

    @Override
    public ImagenSalaResponseDTO buscarPorId(Long id) {
        ImagenSala imagenSala =imagenSalaRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(imagenSala);
    }

    @Override
    @Transactional
    public ImagenSalaResponseDTO actualizarImagenSala(ImagenSalaRequestToUpdateDTO imagenSalaRequestToUpdateDTO) {
        buscarPorId(imagenSalaRequestToUpdateDTO.getId());
        return mapToDTO(imagenSalaRepository.save(mapToEntity(imagenSalaRequestToUpdateDTO)));
    }
    private ImagenSala mapToEntity(ImagenSalaRequestToUpdateDTO imagenSalaRequestToUpdateDTO) {
        return objectMapper.convertValue(imagenSalaRequestToUpdateDTO, ImagenSala.class);
    }

    @Override
    public List<ImagenSalaResponseDTO> listarImagenSala() {
        return imagenSalaRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public void borrarImagenSala(Long id) {
        buscarPorId(id);
        imagenSalaRepository.deleteById(id);
    }
}
