package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.entity.Imagen;
import com.example.coworkingprojectback.repository.ImagenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    public Imagen registrarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    public void actualizarImagen(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    public void eliminarImagen(Long id) {
        imagenRepository.deleteById(id);
    }

    public List<Imagen> listarImagen() {
        return imagenRepository.findAll();
    }

    public Optional<Imagen> buscarPorId(Long id) {
        return imagenRepository.findById(id);
    }
}
