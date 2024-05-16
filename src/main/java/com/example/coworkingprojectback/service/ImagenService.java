package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.entity.Imagen;
import com.example.coworkingprojectback.repository.ImagenRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class ImagenService {

    private final ImagenRepository imagenRepository;

    public ImagenService(ImagenRepository imagenRepository) {
        this.imagenRepository = imagenRepository;
    }

    @Transactional
    public Imagen registrarImagen(Imagen imagen) {
        return imagenRepository.save(imagen);
    }

    @Transactional
    public void actualizarImagen(Imagen imagen) {
        imagenRepository.save(imagen);
    }

    @Transactional
    public void eliminarImagen(Integer id) {
        imagenRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<Imagen> listarImagen() {
        return imagenRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Imagen> buscarPorId(Integer id) {
        return imagenRepository.findById(id);
    }
}
