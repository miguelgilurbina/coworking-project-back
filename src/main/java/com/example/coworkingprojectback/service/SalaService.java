package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.repository.SalaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }

    public Sala registrarSala(Sala sala) {
        return salaRepository.save(sala);
    }

    public void actualizarSala(Sala sala) {
        salaRepository.save(sala);
    }

    public void eliminarSala(Long id) {
        salaRepository.deleteById(id);
    }

    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }

    public Optional<Sala> buscarPorNombre(String nombre){
        return salaRepository.buscarPorNombre(nombre);
    }
}
