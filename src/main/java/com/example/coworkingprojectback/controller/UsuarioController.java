package com.example.coworkingprojectback.controller;

import com.example.coworkingprojectback.DTO.In.UsuarioDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.DTO.Update.UsuarioRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.service.IUsuarioService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
@AllArgsConstructor
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final IUsuarioService usuarioService;

    @PostMapping("/registrar")
    public ResponseEntity<UsuarioResponseDTO> registrarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO){
        return new ResponseEntity<>(usuarioService.registrarUsuario(usuarioDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorId(@PathVariable Long id) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.buscarPorId(id), HttpStatus.OK);
    }
    @GetMapping("/{email}")
    public ResponseEntity<UsuarioResponseDTO> buscarPorEmail(@PathVariable String email) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.buscarPorEmail(email), HttpStatus.OK);
    }
    @GetMapping("/listar")
    public ResponseEntity<Collection<UsuarioResponseDTO>> listarUsuarios() {
        return new ResponseEntity<>(usuarioService.listarUsuarios(), HttpStatus.OK);
    }
    @PutMapping("/actualizar")
    public ResponseEntity<UsuarioResponseDTO> actualizarUsuario(@Valid @RequestBody UsuarioRequestToUpdateDTO usuarioRequestToUpdateDTO) throws ResourceNotFoundException {
        return new ResponseEntity<>(usuarioService.actualizarUsuario(usuarioRequestToUpdateDTO), HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarUsuario(@PathVariable Long id) throws ResourceNotFoundException {
        usuarioService.eliminarUsuario(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
