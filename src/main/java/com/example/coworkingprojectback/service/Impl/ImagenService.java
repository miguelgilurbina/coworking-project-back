package com.example.coworkingprojectback.service.Impl;

import com.example.coworkingprojectback.repository.ImagenSalaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    private final ImagenSalaRepository imagenSalaRepository;

    public ImagenService(ImagenSalaRepository imagenSalaRepository) {
        this.imagenSalaRepository = imagenSalaRepository;
    }

    @Transactional
    public Imagen registrarImagen(Imagen imagen) {
        return imagenSalaRepository.save(imagen);
    }

    @Transactional
    public Imagen actualizarImagen(Imagen imagen) {
        imagenSalaRepository.save(imagen);
        return imagen;
    }

    @Transactional
    public void eliminarImagen(Long id) {
        imagenSalaRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Imagen> listarImagen() {
        return imagenSalaRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Imagen> buscarPorId(Long id) {
        return imagenSalaRepository.findById(id);
    }
}
