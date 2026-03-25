package app.com.stakeguard.service;

import app.com.stakeguard.entity.CompraPick;
import app.com.stakeguard.entity.Pick;
import app.com.stakeguard.enums.StatusPick;
import app.com.stakeguard.repository.CompraPickRepository;
import app.com.stakeguard.repository.PickRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PickService {

    private final PickRepository repository;
    private final CompraPickRepository compraPickRepository;

    // 👇 ESTE ES EL CONSTRUCTOR QUE FALTA 👇
    // Spring Boot verá esto y automáticamente le pasará el repositorio real
    public PickService(PickRepository repository, CompraPickRepository compraPickRepository) {
        this.repository = repository;
        this.compraPickRepository = compraPickRepository;
    }

    @Transactional
    public Pick reportarResultado(Long id, String nuevoEstado) {
        if (id == null) {
            throw new IllegalArgumentException("ID del Pick no puede ser nulo");
        }
        return repository.findById(id).map(pick -> {
            pick.setEstado(StatusPick.valueOf(nuevoEstado.toUpperCase()));
            pick.setFechaResultado(LocalDateTime.now());
            pick.setFechaExpiracionDisputa(LocalDateTime.now().plusHours(4));
            return repository.save(pick);
        }).orElseThrow(() -> new RuntimeException("Pick ID " + id + " no existe"));
    }

    @Transactional
    public Pick disputar(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del Pick no puede ser nulo");
        }

        Pick pick = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pick no encontrado"));

        // BLINDAJE 1: No puedes disputar algo que sigue PENDIENTE
        if (pick.getEstado() == StatusPick.PENDIENTE) {
            throw new IllegalStateException("No puedes disputar un pick que aún no tiene resultado.");
        }

        // BLINDAJE 2: No puedes disputar algo que ya está DISPUTADO
        if (pick.getEstado() == StatusPick.DISPUTADO) {
            throw new IllegalStateException("Este pick ya se encuentra en disputa.");
        }

        // BLINDAJE 3: La regla de las 4 horas
        if (pick.getFechaExpiracionDisputa() == null ||
                LocalDateTime.now().isAfter(pick.getFechaExpiracionDisputa())) {
            throw new IllegalStateException("El tiempo de disputa (4h) ha expirado.");
        }

        pick.setEstado(StatusPick.DISPUTADO);
        return repository.save(pick);
    }

    public Pick crearPick(Pick pick) {
        if (pick == null) {
            throw new IllegalArgumentException("El Pick no puede ser nulo");
        }
        return repository.save(pick);
    }

    public List<Pick> obtenerTodos() {
        return repository.findAll();
    }

    public List<Pick> obtenerPicksActivos() {
        return repository.findByEstado(StatusPick.PENDIENTE);
    }

    @Transactional
    public Pick liquidarPick(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("El ID del Pick no puede ser nulo");
        }

        Pick pick = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pick no encontrado"));

        // Solo podemos liquidar si ya pasó la ventana de disputa y NO fue disputado
        if (pick.getEstado() == StatusPick.DISPUTADO) {
            throw new IllegalStateException("No se puede liquidar un pick en disputa.");
        }

        if (pick.getFechaExpiracionDisputa() != null &&
                LocalDateTime.now().isBefore(pick.getFechaExpiracionDisputa())) {
            throw new IllegalStateException("Aún no expira el periodo de gracia de 4 horas.");
        }

        // Si sobrevive a las validaciones, significa que es seguro pagarle al Tipster
        // Aquí luego conectarás la lógica para liberar el saldo a la wallet del Tipster
        // 1. Buscas todas las compras de este pick
        List<CompraPick> compras = compraPickRepository.findByPickId(id);

        // 2. Liberas los fondos para el Tipster
        for (CompraPick compra : compras) {
            compra.setStatusPago(StatusPick.LIQUIDADO);
            compraPickRepository.save(compra);
        }

        // 3. (Futuro) Le sumas el saldo al "Wallet" del Tipster
        // Podríamos crear un status "LIQUIDADO" en el Enum, pero por ahora devolvemos
        // el pick
        return pick;
    }
}