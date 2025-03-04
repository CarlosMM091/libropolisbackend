package com.libropolis.backend.service;

import com.libropolis.backend.model.Usuario;
import com.libropolis.backend.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email);
    }

    public Usuario actualizarSaldo(Long usuarioId, double montoRecarga) throws Exception {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (montoRecarga < 50000 || montoRecarga > 200000) {
                throw new Exception("El monto de recarga debe estar entre $50.000 y $200.000.");
            }
            usuario.setSaldo(usuario.getSaldo() + montoRecarga);
            return usuarioRepository.save(usuario);
        } else {
            throw new Exception("Usuario no encontrado.");
        }
    }

    public Usuario actualizarDatos(Long usuarioId, Usuario nuevosDatos) throws Exception {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNombre(nuevosDatos.getNombre());
            usuario.setEmail(nuevosDatos.getEmail());
            return usuarioRepository.save(usuario);
        } else {
            throw new Exception("Usuario no encontrado.");
        }
    }
}
