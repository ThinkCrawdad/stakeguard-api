package app.com.stakeguard.service;

import app.com.stakeguard.entity.Usuario;
import app.com.stakeguard.enums.AuthProvider;
import app.com.stakeguard.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {

    private final UsuarioRepository repository;

    // Nuestro constructor manual para inyectar la dependencia
    public UsuarioService(UsuarioRepository repository) {
        this.repository = repository;
    }

    public Usuario registrarUsuario(Usuario nuevoUsuario) {
        // Validamos correos y usernames duplicados
        if (repository.findByEmail(nuevoUsuario.getEmail()).isPresent()) {
            throw new IllegalArgumentException("El correo ya está en uso.");
        }
        if (repository.findByUsername(nuevoUsuario.getUsername()).isPresent()) {
            throw new IllegalArgumentException("El nombre de usuario ya está en uso.");
        }

        // 🚨 NUEVA REGLA: Si es registro manual (LOCAL), la contraseña es obligatoria
        if (nuevoUsuario.getProvider() == AuthProvider.LOCAL &&
                (nuevoUsuario.getPassword() == null || nuevoUsuario.getPassword().trim().isEmpty())) {
            throw new IllegalArgumentException("La contraseña es obligatoria para registros por correo.");
        }

        // Más adelante, aquí meteremos BCrypt para encriptar la contraseña antes de
        // guardarla

        return repository.save(nuevoUsuario);
    }

    public List<Usuario> obtenerTodos() {
        return repository.findAll();
    }

    public Usuario buscarPorId(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID no puede ser nulo");
        }
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}