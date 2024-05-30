package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;

public class DataInitializer implements CommandLineRunner {
    @Autowired
    private CaracteristicaRepository caracteristicaRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private ImagenSalaRepository imagenSalaRepository;
    @Autowired
    private ImagenCategoriaRepository imagenCategoriaRepository;



    @Override
    public void run(String... args) throws Exception {

    }
}
