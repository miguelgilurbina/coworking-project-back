package com.example.coworkingprojectback.service.Impl;

import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.repository.SalaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class SalaService {

    private final SalaRepository salaRepository;

    public SalaService(SalaRepository salaRepository) {
        this.salaRepository = salaRepository;
    }
    @Transactional
    public Sala registrarSala(Sala sala) {
        return salaRepository.save(sala);
    }
    @Transactional
    public void actualizarSala(Sala sala) {
        salaRepository.save(sala);
    }
    @Transactional
    public void eliminarSala(Integer id) {
        salaRepository.deleteById(id);
    }
    @Transactional
    public List<Sala> listarSalas() {
        return salaRepository.findAll();
    }
    @Transactional
    public Optional<Sala> buscarPorNombre(String nombre){
        return salaRepository.buscarPorNombre(nombre);
    }
}
