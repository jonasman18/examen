package com.example.examen.repository;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface ExamenParcoursRepository extends JpaRepository<ExamenParcours, ExamenParcoursId> {

    // ✅ Supprime toutes les associations liées à un examen spécifique
    @Transactional
    void deleteById_IdExamen(Long idExamen);
}
