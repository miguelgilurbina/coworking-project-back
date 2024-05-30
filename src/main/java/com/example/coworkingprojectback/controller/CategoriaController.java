package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.entity.Categoria;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.Impl.CategoriaService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;
    private static final Logger logger = Logger.getLogger(CategoriaController.class);


    @PostMapping
    @Transactional
    public ResponseEntity<?> registrarCategoria(@RequestBody Categoria categoria) {
        Optional<Categoria> categoriaExistente = categoriaService.buscarPorNombre(categoria.getNombre());
        if (categoriaExistente.isPresent()) {
            logger.error("Ya existe categoria con el mismo nombre");
            return ResponseEntity.badRequest().body(null);
        }
        return ResponseEntity.ok(categoriaService.registrarCategoria(categoria));
    }
    @GetMapping("/categorias")
    public ResponseEntity<List<Categoria>> listarCategorias(){
        return ResponseEntity.ok(categoriaService.listarCategorias());
    }
    @PutMapping
    @Transactional
    public ResponseEntity<String> actualizarCategoria(@RequestBody Categoria categoria) throws ResourceNotFoundException {
        Optional<Categoria> categoriaBuscada = categoriaService.buscarPorNombre(categoria.getNombre());
        if (categoriaBuscada.isPresent()) {
            categoriaService.actualizarCategoria(categoria);
            return ResponseEntity.ok("Categoria actualizad");
        } else {
            logger.error("No se encontro categoria");
            throw new ResourceNotFoundException("No se encontro categoria");
        }
    }
    @GetMapping("/{nombre}")
    public ResponseEntity <Optional<Categoria>> buscarPorNombre(@PathVariable String nombre) throws ResourceNotFoundException {
        Optional<Categoria> categoriaBuscada = categoriaService.buscarPorNombre(nombre);
        if (categoriaBuscada.isPresent()) {
            return ResponseEntity.ok(categoriaBuscada);
        } else {
            logger.error("No se encontro categoria");
            throw new ResourceNotFoundException("No se encontro categoria");
        }
    }
    @DeleteMapping("/{nombre}")
    @Transactional
    public ResponseEntity<String> eliminarCategoria(@PathVariable String nombre) throws ResourceNotFoundException {
    Optional<Categoria> categoriaBuscada = categoriaService.buscarPorNombre(nombre);
    if (categoriaBuscada.isPresent()) {
        categoriaService.eliminarCategoria(categoriaBuscada.get());
        return ResponseEntity.ok("Categoria eliminada");
    }
    logger.error("No se encontro la categoria");
    throw new ResourceNotFoundException("No se encontro la categoria");
    }
}
