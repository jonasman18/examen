package com.example.examen.repository;

import com.example.examen.model.PlanningSurveillance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface PlanningSurveillanceRepository extends JpaRepository<PlanningSurveillance, Long> {

    // 🔹 Requête personnalisée pour charger toutes les relations (JOIN FETCH)
    @Query("""
        SELECT p FROM PlanningSurveillance p
        JOIN FETCH p.examen e
        JOIN FETCH p.salle s
        JOIN FETCH p.surveillant sv
    """)
    List<PlanningSurveillance> findAllWithRelations();

    // Optionnel : pour récupérer par examen
    @Query("""
        SELECT p FROM PlanningSurveillance p
        JOIN FETCH p.examen e
        JOIN FETCH p.salle s
        JOIN FETCH p.surveillant sv
        WHERE e.idExamen = :idExamen
    """)
    List<PlanningSurveillance> findByExamenId(Long idExamen);
}
