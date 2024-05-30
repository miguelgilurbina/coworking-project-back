package com.example.coworkingprojectback.service.Impl;

import com.example.coworkingprojectback.entity.Categoria;
import com.example.coworkingprojectback.repository.CategoriaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;


    public CategoriaService(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }
    @Transactional
    public Categoria registrarCategoria(Categoria categoria){
        return categoriaRepository.save(categoria);
    }
    @Transactional
    public void actualizarCategoria(Categoria categoria){
        categoriaRepository.save(categoria);
    }
    @Transactional
    public void eliminarCategoria(Categoria categoria){
        categoriaRepository.delete(categoria);
    }

    public List<Categoria> listarCategorias(){
        return categoriaRepository.findAll();
    }
    @Transactional
    public Optional<Categoria> buscarPorNombre(String nombre){
        return categoriaRepository.buscarPorNombre(nombre);
    }
}
