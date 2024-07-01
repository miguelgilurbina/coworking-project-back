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
    @Autowired
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public UsuarioService(PasswordEncoder passwordEncoder, UsuarioRepository usuarioRepository) {
        this.passwordEncoder = passwordEncoder;
        this.usuarioRepository = usuarioRepository;

    }
    @Override
    public UsuarioResponseDTO registrarUsuario(UsuarioRequestDTO usuarioDTO) {
        // Primero, verificar si el email ya está registrado
        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new DataIntegrityViolationException("El correo electrónico ya está registrado.");
        }

        // Crear una nueva instancia de Usuario
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setRol(usuarioDTO.getRol());

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
        return null;
    }

    @Override
    public List<UsuarioResponseDTO> listarUsuarios() {
        return null;
    }



    @Override
    public UsuarioResponseDTO actualizarUsuario(Long id, UsuarioRequestDTO usuarioDTO) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        usuario.setEmail(usuarioDTO.getEmail());
        usuario.setNombre(usuarioDTO.getNombre());
        usuario.setApellido(usuarioDTO.getApellido());
        usuario.setPassword(usuarioDTO.getPassword()); // Considera encriptar la contraseña
        usuario.setRol(usuarioDTO.getRol());

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
