package com.explora2.explora2_backend.service;

import com.explora2.explora2_backend.model.Tarea;
import com.explora2.explora2_backend.repository.TareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TareaService {

    private final TareaRepository tareaRepository;

    public TareaService(TareaRepository tareaRepository) {
        this.tareaRepository = tareaRepository;
    }

    public List<Tarea> listar() {
        return tareaRepository.findAll();
    }

    public Tarea crear(Tarea tarea) {
        return tareaRepository.save(tarea);
    }

    public Tarea editar(Long id, Tarea datos) {
        Tarea actual = tareaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        actual.setTitulo(datos.getTitulo());
        actual.setAsignatura(datos.getAsignatura());
        actual.setDescripcion(datos.getDescripcion());
        actual.setEntregada(datos.isEntregada());

        return tareaRepository.save(actual);
    }

    public void borrar(Long id) {
        if (!tareaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        tareaRepository.deleteById(id);
    }
}