package com.example.examen.repository;

import com.example.examen.model.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlanningSurveillanceRepository extends JpaRepository<PlanningSurveillance, Long> {
    List<PlanningSurveillance> findByExamen(Examen examen);
    List<PlanningSurveillance> findBySalle(Salle salle);
    List<PlanningSurveillance> findBySurveillant(Surveillant surveillant);
}
