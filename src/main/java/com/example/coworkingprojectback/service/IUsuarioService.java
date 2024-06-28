package com.example.coworkingprojectback.service;
import com.example.coworkingprojectback.DTO.In.UsuarioRequestDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;

import java.util.List;

public interface IUsuarioService {
    UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO usuarioDTO);
    UsuarioResponseDTO buscarPorId(Long id);
    List<UsuarioResponseDTO> listarUsuarios();
    UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioDTO);
    void eliminarUsuario(Long id);
}