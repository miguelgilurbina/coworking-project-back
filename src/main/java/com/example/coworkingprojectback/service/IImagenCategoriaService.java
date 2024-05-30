package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.ImagenCategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.ImagenCategoriaResponseDTO;

import java.util.List;

public interface IImagenCategoriaService {
    ImagenCategoriaResponseDTO createImagenCategoria(ImagenCategoriaDTO imagenCategoriaDTO);
    ImagenCategoriaResponseDTO getImagenCategoriaById(Long id);
    List<ImagenCategoriaResponseDTO> getAllImagenesCategoria();
    ImagenCategoriaResponseDTO updateImagenCategoria(Long id, ImagenCategoriaDTO imagenCategoriaDTO);
    void deleteImagenCategoria(Long id);
}
