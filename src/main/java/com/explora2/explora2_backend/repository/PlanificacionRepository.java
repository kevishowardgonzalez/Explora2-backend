package com.explora2.explora2_backend.repository;

import com.explora2.explora2_backend.model.Planificacion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanificacionRepository extends JpaRepository<Planificacion, Long> {
}