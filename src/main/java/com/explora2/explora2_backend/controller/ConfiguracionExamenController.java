package com.explora2.explora2_backend.controller;

import com.explora2.explora2_backend.model.ConfiguracionExamen;
import com.explora2.explora2_backend.service.ConfiguracionExamenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/configuracion-examen")
@CrossOrigin(origins = "*")
public class ConfiguracionExamenController {

    private final ConfiguracionExamenService service;

    public ConfiguracionExamenController(ConfiguracionExamenService service) {
        this.service = service;
    }

    @GetMapping
    public List<ConfiguracionExamen> listar() {
        return service.listar();
    }

    @PutMapping
    public ConfiguracionExamen guardar(@RequestBody ConfiguracionExamen datos) {
        return service.guardar(datos);
    }
}