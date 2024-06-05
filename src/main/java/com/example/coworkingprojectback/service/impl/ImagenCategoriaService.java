package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.ImagenCategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenCategoriaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenCategoriaRequestToUpdateDTO;
import com.example.coworkingprojectback.DTO.Update.ImagenSalaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Categoria;
import com.example.coworkingprojectback.entity.ImagenCategoria;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.CategoriaRepository;
import com.example.coworkingprojectback.repository.ImagenCategoriaRepository;
import com.example.coworkingprojectback.service.IImagenCategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImagenCategoriaService implements IImagenCategoriaService {

    private final ImagenCategoriaRepository imagenCategoriaRepository;
    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public ImagenCategoriaService(ImagenCategoriaRepository imagenCategoriaRepository, CategoriaRepository categoriaRepository, ObjectMapper objectMapper) {
        this.imagenCategoriaRepository = imagenCategoriaRepository;
        this.categoriaRepository = categoriaRepository;
        this.objectMapper = objectMapper;
    }

    private final String NOT_FOUND_MESSAGE = "No se encontró imagen de categoría";

    @Override
    public ImagenCategoriaResponseDTO createImagenCategoria(ImagenCategoriaDTO imagenCategoriaDTO) {
        ImagenCategoria imagenCategoria = mapToEntity(imagenCategoriaDTO);
        imagenCategoriaRepository.save(imagenCategoria);
        return mapToDTO(imagenCategoria);
    }

    @Override
    public ImagenCategoriaResponseDTO getImagenCategoriaById(Long id) {
        ImagenCategoria imagenCategoria = imagenCategoriaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(imagenCategoria);
    }

    @Override
    public List<ImagenCategoriaResponseDTO> getAllImagenesCategoria() {
        return imagenCategoriaRepository.findAll().stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public ImagenCategoriaResponseDTO updateImagenCategoria(ImagenCategoriaRequestToUpdateDTO imagenCategoriaRequestToUpdateDTO) {
        getImagenCategoriaById(imagenCategoriaRequestToUpdateDTO.getId());
        return mapToDTO(imagenCategoriaRepository.save(mapToEntity(imagenCategoriaRequestToUpdateDTO)));
    }


    @Override
    public void deleteImagenCategoria(Long id) {
        imagenCategoriaRepository.deleteById(id);
    }

    private ImagenCategoriaResponseDTO mapToDTO(ImagenCategoria imagenCategoria) {
        return objectMapper.convertValue(imagenCategoria, ImagenCategoriaResponseDTO.class);
    }
    private ImagenCategoria mapToEntity(ImagenCategoriaRequestToUpdateDTO imagenCategoriaRequestToUpdateDTO) {
        return objectMapper.convertValue(imagenCategoriaRequestToUpdateDTO, ImagenCategoria.class);
    }
    private ImagenCategoria mapToEntity(ImagenCategoriaDTO imagenCategoriaDTO) {
        return objectMapper.convertValue(imagenCategoriaDTO, ImagenCategoria.class);
    }

}