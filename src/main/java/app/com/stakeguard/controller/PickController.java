package app.com.stakeguard.controller;

import app.com.stakeguard.entity.Pick;
import app.com.stakeguard.service.PickService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/picks")
public class PickController {

    private final PickService service;

    // Inyección por constructor (Nuestra victoria sobre Lombok)
    public PickController(PickService service) {
        this.service = service;
    }

    // 1. Crear un Pick nuevo
    @PostMapping
    public ResponseEntity<Pick> crearPick(@RequestBody Pick pick) {
        return ResponseEntity.ok(service.crearPick(pick));
    }

    // 2. Leer TODOS los picks
    @GetMapping
    public ResponseEntity<List<Pick>> obtenerTodos() {
        return ResponseEntity.ok(service.obtenerTodos());
    }

    // 3. Leer SOLO los picks PENDIENTES (Para el feed principal)
    @GetMapping("/activos")
    public ResponseEntity<List<Pick>> obtenerActivos() {
        return ResponseEntity.ok(service.obtenerPicksActivos());
    }

    // 4. El Tipster reporta si ganó o perdió (Inicia el reloj de 4h)
    @PatchMapping("/{id}/resultado")
    public ResponseEntity<Pick> actualizarResultado(
            @PathVariable Long id,
            @RequestParam String estado) {
        return ResponseEntity.ok(service.reportarResultado(id, estado));
    }

    // 5. El usuario no está de acuerdo y mete disputa
    @PutMapping("/{id}/disputar")
    public ResponseEntity<?> disputar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.disputar(id));
        } catch (IllegalStateException | IllegalArgumentException e) {
            // Atrapa nuestras validaciones blindadas y regresa un 400 con el texto
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }

    // 6. El sistema liquida el pick (Libera el dinero al Tipster)
    @PutMapping("/{id}/liquidar")
    public ResponseEntity<?> liquidar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(service.liquidarPick(id));
        } catch (IllegalStateException e) {
            // Si intenta liquidar antes de las 4 horas o si está disputado, lo rebotamos
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Error interno del servidor");
        }
    }
}