package com.explora2.explora2_backend.model;

import jakarta.persistence.*;

@Entity
@Table(name = "planificaciones")
public class Planificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String asignatura;
    private String unidad;
    private String objetivo;
    private String tipoRecurso;
    private String titulo;

    @Column(length = 1000)
    private String descripcion;

    private boolean validado;
    private boolean enviadoUtp;
    private boolean autorizado;
    private boolean asignadoAlumno;
    private boolean respuestaEntregada;

    @Column(length = 1000)
    private String respuestaAlumno;

    public Planificacion() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }

    public String getUnidad() { return unidad; }
    public void setUnidad(String unidad) { this.unidad = unidad; }

    public String getObjetivo() { return objetivo; }
    public void setObjetivo(String objetivo) { this.objetivo = objetivo; }

    public String getTipoRecurso() { return tipoRecurso; }
    public void setTipoRecurso(String tipoRecurso) { this.tipoRecurso = tipoRecurso; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isValidado() { return validado; }
    public void setValidado(boolean validado) { this.validado = validado; }

    public boolean isEnviadoUtp() { return enviadoUtp; }
    public void setEnviadoUtp(boolean enviadoUtp) { this.enviadoUtp = enviadoUtp; }

    public boolean isAutorizado() { return autorizado; }
    public void setAutorizado(boolean autorizado) { this.autorizado = autorizado; }

    public boolean isAsignadoAlumno() { return asignadoAlumno; }
    public void setAsignadoAlumno(boolean asignadoAlumno) { this.asignadoAlumno = asignadoAlumno; }

    public boolean isRespuestaEntregada() { return respuestaEntregada; }
    public void setRespuestaEntregada(boolean respuestaEntregada) { this.respuestaEntregada = respuestaEntregada; }

    public String getRespuestaAlumno() { return respuestaAlumno; }
    public void setRespuestaAlumno(String respuestaAlumno) { this.respuestaAlumno = respuestaAlumno; }
}