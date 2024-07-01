package com.example.coworkingprojectback.service.impl;

import com.example.coworkingprojectback.DTO.In.UsuarioRequestDTO;
import com.example.coworkingprojectback.DTO.Out.UsuarioResponseDTO;

import com.example.coworkingprojectback.entity.Usuario;
import com.example.coworkingprojectback.repository.UsuarioRepository;
import com.example.coworkingprojectback.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService implements IUsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO usuarioDTO) {
        // Verificar si el email ya está registrado
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("El correo electrónico ya está registrado.");
        }

        // Crear una nueva instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setRol(normalizarRol(usuarioDTO.getRol())); // Normalizar el rol

        // Encriptar la contraseña
        String encodedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
        usuario.setPassword(encodedPassword);

        // Guardar el usuario en la base de datos
        Usuario usuarioGuardado = usuarioRepository.save(usuario);

        // Convertir y retornar el DTO de respuesta
        return convertirADTO(usuarioGuardado);
    }

    @Override
    public UsuarioResponseDTO buscarPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null); // O lanzar una excepción si no se encuentra
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setRol(normalizarRol(usuarioDTO.getRol())); // Normalizar el rol

        // Encriptar y actualizar la contraseña si se proporciona
        if (usuarioDTO.getPassword() != null && !usuarioDTO.getPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(usuarioDTO.getPassword());
            usuario.setPassword(encodedPassword);
        }

        Usuario usuarioActualizado = usuarioRepository.save(usuario);
        return convertirADTO(usuarioActualizado);
    }

    @Override
    public void eliminarUsuario(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado");
        }
        usuarioRepository.deleteById(id);
    }

    private String normalizarRol(String rol) {
        // Aquí podrías implementar la lógica para normalizar y validar roles si es necesario
        return rol.toUpperCase(); // Ejemplo básico: convertir a mayúsculas
    }

    private UsuarioResponseDTO convertirADTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setEmail(usuario.getEmail());
        dto.setNombre(usuario.getNombre());
        dto.setApellido(usuario.getApellido());
        dto.setRol(usuario.getRol());
        return dto;
    }
}
