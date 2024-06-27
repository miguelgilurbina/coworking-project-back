package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.SalaDTO;
import com.example.coworkingprojectback.DTO.In.UsuarioDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.DTO.Update.SalaRequestToUpdateDTO;
import com.example.coworkingprojectback.DTO.Update.UsuarioRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Sala;
import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import com.example.coworkingprojectback.service.IUsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UsuarioService implements IUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final ObjectMapper objectMapper;

    public UsuarioService(UsuarioRepository usuarioRepository, ObjectMapper objectMapper) {
        this.usuarioRepository = usuarioRepository;
        this.objectMapper = objectMapper;
    }

    private final String NOT_FOUND_MESSAGE = "No se encontró usuario";



    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioDTO usuarioDTO) {
        Usuario usuario = mapToEntity(usuarioDTO);
        usuarioRepository.save(usuario);
        return null;
    }

    private Usuario mapToEntity(UsuarioDTO usuarioDTO) {
        return objectMapper.convertValue(usuarioDTO, Usuario.class);
    }

    private Usuario mapToEntity(UsuarioRequestToUpdateDTO usuarioRequestToUpdateDTO) {
        return objectMapper.convertValue(usuarioRequestToUpdateDTO, Usuario.class);
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(UsuarioRequestToUpdateDTO usuarioRequestToUpdateDTO) {
        buscarPorId(usuarioRequestToUpdateDTO.getId());
        return mapToDTO(usuarioRepository.save(mapToEntity(usuarioRequestToUpdateDTO)));
    }

    private UsuarioResponseDTO mapToDTO(Usuario usuario) {
        return objectMapper.convertValue(usuario, UsuarioResponseDTO.class);
    }
    private SalaResponseDTO mapToDTO(Sala sala) {
        return objectMapper.convertValue(sala, SalaResponseDTO.class);
    }


    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return usuarioRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    @Override
    public UsuarioResponseDTO buscarPorNombre(String nombre) {
        Usuario usuario = (Usuario) usuarioRepository.findByNombre(nombre).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(usuario);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(()->new ResourceNotFoundException(NOT_FOUND_MESSAGE));
        return mapToDTO(usuario);
    }

    @Override
    public void eliminarSala(Long id) throws ResourceNotFoundException {
        buscarPorId(id);
        usuarioRepository.deleteById(id);

    }
}
