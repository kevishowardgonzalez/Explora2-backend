package com.explora2.explora2_backend.service;

import com.explora2.explora2_backend.model.Planificacion;
import com.explora2.explora2_backend.repository.PlanificacionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PlanificacionService {

    private final PlanificacionRepository repository;

    public PlanificacionService(PlanificacionRepository repository) {
        this.repository = repository;
    }

    public List<Planificacion> listar() {
        return repository.findAll();
    }

    public Planificacion obtenerPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Planificacion crear(Planificacion datos) {
        return repository.save(datos);
    }

    public Planificacion actualizar(Long id, Planificacion datos) {
        Planificacion actual = obtenerPorId(id);
        actual.setAsignatura(datos.getAsignatura());
        actual.setUnidad(datos.getUnidad());
        actual.setObjetivo(datos.getObjetivo());
        actual.setTipoRecurso(datos.getTipoRecurso());
        actual.setTitulo(datos.getTitulo());
        actual.setDescripcion(datos.getDescripcion());
        actual.setValidado(datos.isValidado());
        actual.setEnviadoUtp(datos.isEnviadoUtp());
        actual.setAutorizado(datos.isAutorizado());
        actual.setAsignadoAlumno(datos.isAsignadoAlumno());
        actual.setRespuestaEntregada(datos.isRespuestaEntregada());
        actual.setRespuestaAlumno(datos.getRespuestaAlumno());
        return repository.save(actual);
    }

    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }
}