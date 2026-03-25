package app.com.stakeguard.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import app.com.stakeguard.enums.StatusPick;

@Entity
@Table(name = "pick")
public class Pick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private Double momio;
    private Double stake;
    @Enumerated(EnumType.STRING)
    private StatusPick estado = StatusPick.PENDIENTE;
    private LocalDateTime fechaCreacion = LocalDateTime.now();
    private LocalDateTime fechaResultado;
    private LocalDateTime fechaExpiracionDisputa;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tipster_id")
    @JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
    private Usuario tipster;
    private String deporte;
    private String liga;
    private Double precio;
    private String casaApuestas;
    @Column(length = 1000)
    private String imageUrl;
    @OneToMany(mappedBy = "pick", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seleccion> selecciones = new ArrayList<>();

    public Pick() {
    }

    /* -- Getters and Setters -- */
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCasaApuestas() {
        return casaApuestas;
    }

    public void setCasaApuestas(String casaApuestas) {
        this.casaApuestas = casaApuestas;
    }

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

    public void setSelecciones(java.util.List<Seleccion> selecciones) {
        this.selecciones = selecciones;
        for (Seleccion s : selecciones) {
            s.setPick(this);
        }
    }

    public List<Seleccion> getSelecciones() {
        return selecciones;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}