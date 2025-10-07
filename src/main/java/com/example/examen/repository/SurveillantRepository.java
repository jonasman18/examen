package com.example.examen.repository;

import com.example.examen.model.Surveillant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SurveillantRepository extends JpaRepository<Surveillant, Long> {

    // 🔹 Trouver tous les surveillants d’une salle donnée
    List<Surveillant> findByNumeroSalle(String numeroSalle);
}