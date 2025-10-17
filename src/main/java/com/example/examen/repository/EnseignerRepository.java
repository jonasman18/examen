package com.example.examen.repository;

import com.example.examen.model.Enseigner;
import com.example.examen.model.EnseignerId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface EnseignerRepository extends JpaRepository<Enseigner, EnseignerId> {
    @Query("SELECT e FROM Enseigner e WHERE e.matiere.idMatiere = :idMatiere")
    Optional<Enseigner> findByMatiereId(@Param("idMatiere") Long idMatiere);
}
