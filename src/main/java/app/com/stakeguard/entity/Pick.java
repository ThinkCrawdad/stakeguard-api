package app.com.stakeguard.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

import app.com.stakeguard.enums.StatusPick;

@Entity
@Table(name = "pick")
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String evento;
    private String prediccion;
    private Double momio;
    private Double stake;

    @Enumerated(EnumType.STRING)
    private StatusPick estado = StatusPick.PENDIENTE;

    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaResultado;
    private LocalDateTime fechaExpiracionDisputa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipster_id")
    private Usuario tipster;

    private String deporte;
    private String liga;
    private Double precio;

    public Usuario getTipster() {
        return tipster;
    }

    public void setTipster(Usuario tipster) {
        this.tipster = tipster;
    }

    public String getDeporte() {
        return deporte;
    }

    public void setDeporte(String deporte) {
        this.deporte = deporte;
    }

    public String getLiga() {
        return liga;
    }

    public void setLiga(String liga) {
        this.liga = liga;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    public String getAnalisis() {
        return analisis;
    }

    public void setAnalisis(String analisis) {
        this.analisis = analisis;
    }

    @Column(length = 1000)
    private String analisis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEvento() {
        return evento;
    }

    public void setEvento(String evento) {
        this.evento = evento;
    }

    public String getPrediccion() {
        return prediccion;
    }

    public void setPrediccion(String prediccion) {
        this.prediccion = prediccion;
    }

    public Double getMomio() {
        return momio;
    }

    public void setMomio(Double momio) {
        this.momio = momio;
    }

    public Double getStake() {
        return stake;
    }

    public void setStake(Double stake) {
        this.stake = stake;
    }

    public StatusPick getEstado() {
        return estado;
    }

    public void setEstado(StatusPick estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaResultado() {
        return fechaResultado;
    }

    public void setFechaResultado(LocalDateTime fechaResultado) {
        this.fechaResultado = fechaResultado;
    }

    public LocalDateTime getFechaExpiracionDisputa() {
        return fechaExpiracionDisputa;
    }

    public void setFechaExpiracionDisputa(LocalDateTime fechaExpiracionDisputa) {
        this.fechaExpiracionDisputa = fechaExpiracionDisputa;
    }
}