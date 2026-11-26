package com.explora2.explora2_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "configuracion_examen")
public class ConfiguracionExamen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int intentosPermitidos;
    private String fechaInicio;
    private String fechaFin;
    private String version;

    public ConfiguracionExamen() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public int getIntentosPermitidos() { return intentosPermitidos; }
    public void setIntentosPermitidos(int intentosPermitidos) { this.intentosPermitidos = intentosPermitidos; }

    public String getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(String fechaInicio) { this.fechaInicio = fechaInicio; }

    public String getFechaFin() { return fechaFin; }
    public void setFechaFin(String fechaFin) { this.fechaFin = fechaFin; }

    public String getVersion() { return version; }
    public void setVersion(String version) { this.version = version; }
}