package com.example.examen.repository;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.model.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ExamenParcoursRepository extends JpaRepository<ExamenParcours, ExamenParcoursId> {

    @Query("SELECT ep.parcours FROM ExamenParcours ep WHERE ep.examen.idExamen = :idExamen")
    List<Parcours> findParcoursByExamen(@Param("idExamen") Long idExamen);
    // ✅ Supprime toutes les associations liées à un examen spécifique
    @Transactional
    void deleteAllByExamen_IdExamen(Long idExamen);

}
