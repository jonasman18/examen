package com.example.examen.repository;

import com.example.examen.model.Parcours;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParcoursRepository extends JpaRepository<Parcours, Long> {
}