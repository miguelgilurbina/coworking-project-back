package com.example.coworkingprojectback.service.impl;
import com.example.coworkingprojectback.DTO.In.CategoriaDTO;
import com.example.coworkingprojectback.DTO.Out.CategoriaResponseDTO;
import com.example.coworkingprojectback.DTO.Update.CategoriaRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Categoria;
import com.example.coworkingprojectback.repository.CategoriaRepository;
import com.example.coworkingprojectback.service.ICategoriaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService  implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;
    private final ObjectMapper objectMapper;

    public CategoriaService(CategoriaRepository categoriaRepository, ObjectMapper objectMapper) {
        this.categoriaRepository = categoriaRepository;
        this.objectMapper = objectMapper;
    }

    private final String NOT_FOUND_MESSAGE = "No se encontró la categoria solicitada";


    @Override
    public CategoriaResponseDTO createCategoria(CategoriaDTO categoriaDTO) {
        Categoria categoria =mapToEntity(categoriaDTO);
        categoriaRepository.save(categoria);
        return mapToDTO(categoria);
    }

    private CategoriaResponseDTO mapToDTO(Categoria categoria) {
        return objectMapper.convertValue(categoria, CategoriaResponseDTO.class);
    }

    private Categoria mapToEntity(CategoriaDTO categoriaDTO) {
        return objectMapper.convertValue(categoriaDTO, Categoria.class);
    }

    @Override
    public CategoriaResponseDTO getCategoriaById(Long id){
        Categoria categoria = categoriaRepository.findById(id)
               .orElseThrow(() -> new RuntimeException(NOT_FOUND_MESSAGE));
         return mapToDTO(categoria);
    }

    @Override
    public List<CategoriaResponseDTO> getAllCategorias() {
        return categoriaRepository.findAll().stream()
               .map(this::mapToDTO)
               .toList();
    }

    @Override
    public CategoriaResponseDTO updateCategoria(CategoriaRequestToUpdateDTO categoriaRequestToUpdateDTO) {
        getCategoriaById(categoriaRequestToUpdateDTO.getId());
        return mapToDTO(categoriaRepository.save(mapToEntity(categoriaRequestToUpdateDTO)));
    }
    public Categoria mapToEntity(CategoriaRequestToUpdateDTO categoriaRequestToUpdateDTO){
        return objectMapper.convertValue(categoriaRequestToUpdateDTO, Categoria.class);
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

}



