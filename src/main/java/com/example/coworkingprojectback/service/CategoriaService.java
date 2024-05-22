package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.entity.Categoria;
import com.example.coworkingprojectback.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    public Categoria registrarCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public void actualizarCategoria(Categoria categoria) {
        categoriaRepository.save(categoria);
    }

    public void eliminarCategoria(int id) {
        categoriaRepository.deleteById(id);
    }

    public List<Categoria> listarCategorias() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> buscarPorNombre(String nombre){
        return CategoriaRepository.buscarPorNombre(nombre);
    }
}
