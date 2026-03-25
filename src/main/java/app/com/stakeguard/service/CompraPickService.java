package app.com.stakeguard.service;

import app.com.stakeguard.entity.*;
import app.com.stakeguard.enums.StatusPick;
import app.com.stakeguard.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CompraPickService {

    private final CompraPickRepository compraRepository;
    private final PickRepository pickRepository;
    private final UsuarioRepository usuarioRepository;

    // Constructor manual para inyectar los 3 repositorios
    public CompraPickService(CompraPickRepository compraRepository,
            PickRepository pickRepository,
            UsuarioRepository usuarioRepository) {
        this.compraRepository = compraRepository;
        this.pickRepository = pickRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Transactional
    public CompraPick comprarPick(Long clienteId, Long pickId, Double montoPagado) {
        if (clienteId == null || pickId == null || montoPagado == null) {
            throw new IllegalArgumentException("Cliente, Pick y Monto pagado son obligatorios.");
        }
        Usuario cliente = usuarioRepository.findById(clienteId)
                .orElseThrow(() -> new IllegalArgumentException("El cliente no existe."));

        Pick pick = pickRepository.findById(pickId)
                .orElseThrow(() -> new IllegalArgumentException("El pick no existe."));

        // BLINDAJE 1: Un tipster no puede comprar su propio pick para inflar sus ventas
        if (pick.getTipster() != null && pick.getTipster().getId().equals(clienteId)) {
            throw new IllegalStateException("Trampa detectada: No puedes comprar tu propio pick.");
        }

        // BLINDAJE 2: Solo se pueden comprar picks que aún no suceden (PENDIENTES)
        // Nota: Asegúrate de que importas StatusPick de tu paquete entity
        if (pick.getEstado() != StatusPick.PENDIENTE) {
            throw new IllegalStateException("El partido ya comenzó o ya terminó. Venta cerrada.");
        }

        // Si pasa los blindajes, creamos el "ticket" de compra
        CompraPick compra = new CompraPick();
        compra.setCliente(cliente);
        compra.setPick(pick);
        compra.setMontoPagado(montoPagado);
        compra.setStatusPago(StatusPick.RETENIDO);

        return compraRepository.save(compra);
    }

    public List<CompraPick> misCompras(Long clienteId) {
        return compraRepository.findByClienteId(clienteId);
    }
}