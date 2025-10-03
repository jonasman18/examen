package com.example.examen.repository;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamenParcoursRepository extends JpaRepository<ExamenParcours, ExamenParcoursId> {
}
