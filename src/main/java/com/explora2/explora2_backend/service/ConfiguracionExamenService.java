package com.explora2.explora2_backend.service;

import com.explora2.explora2_backend.model.ConfiguracionExamen;
import com.explora2.explora2_backend.repository.ConfiguracionExamenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConfiguracionExamenService {

    private final ConfiguracionExamenRepository repository;

    public ConfiguracionExamenService(ConfiguracionExamenRepository repository) {
        this.repository = repository;
    }

    public List<ConfiguracionExamen> listar() {
        return repository.findAll();
    }

    public ConfiguracionExamen guardar(ConfiguracionExamen datos) {
        ConfiguracionExamen actual = repository.findAll().stream().findFirst().orElse(new ConfiguracionExamen());
        actual.setIntentosPermitidos(datos.getIntentosPermitidos());
        actual.setFechaInicio(datos.getFechaInicio());
        actual.setFechaFin(datos.getFechaFin());
        actual.setVersion(datos.getVersion());
        return repository.save(actual);
    }
}