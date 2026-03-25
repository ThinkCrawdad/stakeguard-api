package app.com.stakeguard.controller;

import app.com.stakeguard.entity.CompraPick;
import app.com.stakeguard.service.CompraPickService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/compras")
public class CompraPickController {

    private final CompraPickService service;

    public CompraPickController(CompraPickService service) {
        this.service = service;
    }

    // Endpoint para realizar la compra
    @PostMapping
    public ResponseEntity<?> realizarCompra(
            @RequestParam Long clienteId,
            @RequestParam Long pickId,
            @RequestParam Double montoPagado) {
        try {
            return ResponseEntity.ok(service.comprarPick(clienteId, pickId, montoPagado));
        } catch (IllegalStateException | IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<CompraPick>> verMisCompras(@PathVariable Long clienteId) {
        return ResponseEntity.ok(service.misCompras(clienteId));
    }
}