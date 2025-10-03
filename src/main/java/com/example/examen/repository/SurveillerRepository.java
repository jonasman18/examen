package com.example.examen.repository;

import com.example.examen.model.Surveiller;
import com.example.examen.model.SurveillerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SurveillerRepository extends JpaRepository<Surveiller, SurveillerId> {
}
