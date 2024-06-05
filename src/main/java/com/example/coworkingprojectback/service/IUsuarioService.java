package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.DTO.In.UsuarioDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;
import com.example.coworkingprojectback.DTO.Update.UsuarioRequestToUpdateDTO;
import com.example.coworkingprojectback.entity.Usuario;

import java.util.List;


public interface IUsuarioService {
    UsuarioResponseDTO registrarUsuario (UsuarioDTO usuarioDTO);
    UsuarioResponseDTO actualizarUsuario (UsuarioRequestToUpdateDTO usuarioRequestToUpdateDTO);
    List<UsuarioResponseDTO> listarUsuarios ();
    UsuarioResponseDTO buscarPorId (Long id);
    UsuarioResponseDTO buscarPorEmail (String email);
    void eliminarUsuario (Long id);
}
