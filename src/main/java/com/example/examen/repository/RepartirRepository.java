package com.example.examen.repository;

import com.example.examen.model.Repartir;
import com.example.examen.model.RepartirId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface RepartirRepository extends JpaRepository<Repartir, RepartirId> {

    @Query("""
        SELECT r.salle.numeroSalle,
               rep.groupe,
               rep.etudiantDebut,
               rep.etudiantFin
        FROM Repartir r
        JOIN r.repartition rep
        ORDER BY r.salle.numeroSalle
    """)
    List<Object[]> getRepartitionParSalle();
}
