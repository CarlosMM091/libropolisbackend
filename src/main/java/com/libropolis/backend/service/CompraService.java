package com.libropolis.backend.service;

import com.libropolis.backend.model.Compra;
import com.libropolis.backend.model.Libro;
import com.libropolis.backend.model.Usuario;
import com.libropolis.backend.repository.CompraRepository;
import com.libropolis.backend.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompraService {

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private LibroRepository libroRepository;

    // Obtener todas las compras
    public List<Compra> obtenerTodas() {
        return compraRepository.findAll();
    }

    // Obtener compras de un usuario específico
    public List<Compra> obtenerPorUsuario(Usuario usuario) {
        return compraRepository.findByUsuario(usuario);
    }

    // Guardar una nueva compra con validación de stock
    public Compra guardarCompra(Compra compra) throws Exception {
        Optional<Libro> libroOptional = libroRepository.findById(compra.getLibro().getId());

        if (libroOptional.isPresent()) {
            Libro libro = libroOptional.get();

            if (libro.getStock() >= compra.getCantidad()) {
                // Restar stock
                libro.setStock(libro.getStock() - compra.getCantidad());
                libroRepository.save(libro);

                // Guardar compra
                return compraRepository.save(compra);
            } else {
                throw new Exception("Stock insuficiente para el libro: " + libro.getTitulo());
            }

        } else {
            throw new Exception("Libro no encontrado");
        }
    }
}
