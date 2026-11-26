package com.explora2.explora2_backend.controller;

import com.explora2.explora2_backend.model.Planificacion;
import com.explora2.explora2_backend.service.PlanificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/planificaciones")
@CrossOrigin(origins = "*")
public class PlanificacionController {

    private final PlanificacionService service;

    public PlanificacionController(PlanificacionService service) {
        this.service = service;
    }

    @GetMapping
    public List<Planificacion> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Planificacion obtenerPorId(@PathVariable Long id) {
        return service.obtenerPorId(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Planificacion crear(@RequestBody Planificacion planificacion) {
        return service.crear(planificacion);
    }

    @PutMapping("/{id}")
    public Planificacion actualizar(@PathVariable Long id, @RequestBody Planificacion datos) {
        return service.actualizar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void eliminar(@PathVariable Long id) {
        service.eliminar(id);
    }
}