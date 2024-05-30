package com.example.coworkingprojectback.service;
import com.example.coworkingprojectback.DTO.Out.CategoriaResponseDTO;
import com.example.coworkingprojectback.DTO.In.CategoriaDTO;

import java.util.List;
public interface ICategoriaService {
    CategoriaResponseDTO createCategoria(CategoriaDTO categoriaDTO);
    CategoriaResponseDTO getCategoriaById(Long id);
    List<CategoriaResponseDTO> getAllCategorias();
    CategoriaResponseDTO updateCategoria(Long id, CategoriaDTO categoriaDTO);
    void deleteCategoria(Long id);
}