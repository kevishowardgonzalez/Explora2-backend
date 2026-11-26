package com.explora2.explora2_backend.controller;

import com.explora2.explora2_backend.model.Tarea;
import com.explora2.explora2_backend.service.TareaService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tareas")
@CrossOrigin(origins = "*")
public class TareaController {

    private final TareaService tareaService;

    public TareaController(TareaService tareaService) {
        this.tareaService = tareaService;
    }

    @GetMapping
    public List<Tarea> listar() {
        return tareaService.listar();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Tarea crear(@RequestBody Tarea tarea) {
        return tareaService.crear(tarea);
    }

    @PutMapping("/{id}")
    public Tarea editar(@PathVariable Long id, @RequestBody Tarea datos) {
        return tareaService.editar(id, datos);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void borrar(@PathVariable Long id) {
        tareaService.borrar(id);
    }
}