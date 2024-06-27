package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.UsuarioDTO;
import com.example.coworkingprojectback.DTO.Out.SalaResponseDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.DTO.Update.UsuarioRequestToUpdateDTO;
import com.example.coworkingprojectback.exception.ResourceNotFoundException;

import java.util.List;

public interface IUsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioDTO usuarioDTO);
    UsuarioResponseDTO actualizarUsuario(UsuarioRequestToUpdateDTO usuarioRequestToUpdateDTO);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO buscarPorNombre(String nombre);
    UsuarioResponseDTO buscarPorId(Long id);
    void eliminarSala(Long id) throws ResourceNotFoundException;
}
