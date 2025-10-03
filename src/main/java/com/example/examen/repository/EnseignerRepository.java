package com.example.examen.repository;

import com.example.examen.model.Enseigner;
import com.example.examen.model.EnseignerId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignerRepository extends JpaRepository<Enseigner, EnseignerId> {
}
