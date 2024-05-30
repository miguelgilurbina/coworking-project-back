package com.example.coworkingprojectback.service;

import com.example.coworkingprojectback.entity.Usuario;

import java.util.List;

public interface IUsuarioService {
    Usuario createUser(Usuario usuario);
    Usuario getUserById(Long id);
    Usuario getUserByEmail(String email);
    List<Usuario> getAllUsers();
    Usuario updateUser(Long id, Usuario usuario);
    void deleteUser(Long id);
}
