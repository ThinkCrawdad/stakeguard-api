package app.com.stakeguard.entity;

import java.time.LocalDateTime;

import app.com.stakeguard.enums.AuthProvider;
import jakarta.persistence.*;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    // 🚨 CAMBIO CLAVE: Ya no es obligatorio. Si entra con Google, esto será null
    private String password;

    // --- NUEVOS CAMPOS PARA OAUTH2 ---
    @Enumerated(EnumType.STRING)
    private AuthProvider provider = AuthProvider.LOCAL; // Por defecto es registro normal

    // Aquí guardaremos el ID larguísimo que nos mande Google/Apple (ej:
    // "10483920193...")
    private String providerId;

    // --- DATOS DE STAKEGUARD ---
    private Boolean esTipster = false;
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getEsTipster() {
        return esTipster;
    }

    public void setEsTipster(Boolean esTipster) {
        this.esTipster = esTipster;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public AuthProvider getProvider() {
        return provider;
    }

    public void setProvider(AuthProvider provider) {
        this.provider = provider;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public Usuario() {
    }
}