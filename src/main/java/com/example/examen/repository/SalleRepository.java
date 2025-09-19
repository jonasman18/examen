package com.example.examen.repository;

import com.example.examen.model.Salle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalleRepository extends JpaRepository<Salle, String> {
}