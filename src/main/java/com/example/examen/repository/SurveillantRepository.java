package com.example.examen.repository;

import com.example.examen.model.Surveillant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveillantRepository extends JpaRepository<Surveillant, Long> {
}