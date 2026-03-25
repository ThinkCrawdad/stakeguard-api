package app.com.stakeguard.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

import app.com.stakeguard.enums.StatusPick;

@Entity
@Table(name = "compras_picks")
public class CompraPick {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pick_id")
    private Pick pick;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id")
    private Usuario cliente;

    @Column(nullable = false)
    private Double montoPagado;

    private LocalDateTime fechaCompra = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private StatusPick statusPago = StatusPick.RETENIDO;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pick getPick() {
        return pick;
    }

    public void setPick(Pick pick) {
        this.pick = pick;
    }

    public Usuario getCliente() {
        return cliente;
    }

    public void setCliente(Usuario cliente) {
        this.cliente = cliente;
    }

    public Double getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(Double montoPagado) {
        this.montoPagado = montoPagado;
    }

    public LocalDateTime getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDateTime fechaCompra) {
        this.fechaCompra = fechaCompra;
    }

    public StatusPick getStatusPago() {
        return statusPago;
    }

    public void setStatusPago(StatusPick statusPago) {
        this.statusPago = statusPago;
    }

    public CompraPick() {
    }
}