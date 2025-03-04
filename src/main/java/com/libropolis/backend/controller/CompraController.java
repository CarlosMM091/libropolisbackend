package com.libropolis.backend.controller;

import com.libropolis.backend.model.Compra;
import com.libropolis.backend.model.Usuario;
import com.libropolis.backend.service.CompraService;
import com.libropolis.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/compras")
@CrossOrigin(origins = "*")
public class CompraController {

    @Autowired
    private CompraService compraService;

    @Autowired
    private UsuarioService usuarioService;

    // Obtener todas las compras
    @GetMapping
    public List<Compra> obtenerTodas() {
        return compraService.obtenerTodas();
    }

    // Obtener compras de un usuario específico
    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<Compra>> obtenerPorUsuario(@PathVariable Long usuarioId) {
        Optional<Usuario> usuarioOpt = usuarioService.obtenerPorId(usuarioId);
        if (usuarioOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<Compra> compras = compraService.obtenerPorUsuario(usuarioOpt.get());
        return ResponseEntity.ok(compras);
    }

    // Guardar una nueva compra
    @PostMapping
    public ResponseEntity<Object> guardarCompra(@RequestBody Compra compra) {
        try {
            Compra nuevaCompra = compraService.guardarCompra(compra);
            return ResponseEntity.ok(nuevaCompra);
        }catch (Exception e) {
            return ResponseEntity.badRequest().body("Error al guardar compra: " + e.getMessage());
        }

    }
}
