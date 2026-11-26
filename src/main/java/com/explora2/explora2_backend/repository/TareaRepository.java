package cl.explora2.backend.repository;

import cl.explora2.backend.model.Tarea;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TareaRepository extends JpaRepository<Tarea, Long> {
}