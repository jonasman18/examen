package com.example.examen.service;

import com.example.examen.model.Examen;
import com.example.examen.model.Repartition;
import com.example.examen.model.Repartir;
import com.example.examen.repository.ExamenRepository;
import com.example.examen.repository.RepartirRepository;
import com.example.examen.repository.RepartitionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;
    private final RepartirRepository repartirRepository;
    private final RepartitionRepository repartitionRepository;

    public ExamenService(ExamenRepository examenRepository, RepartirRepository repartirRepository, RepartitionRepository repartitionRepository) {
        this.examenRepository = examenRepository;
        this.repartirRepository = repartirRepository;
        this.repartitionRepository = repartitionRepository;
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Optional<Examen> getExamenById(Long id_examen) {
        return examenRepository.findById(id_examen);
    }

    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public void deleteExamen(Long id_examen) {
        examenRepository.deleteById(id_examen);
    }

    // ✅ Nouvelle méthode : récupérer les répartitions (Repartition) liées à un examen
    public List<Repartition> getRepartition(Long idExamen) {
        // Cherche d’abord l’examen
        Optional<Examen> examenOpt = examenRepository.findById(idExamen);
        if (examenOpt.isEmpty()) return List.of();

        // On suppose que les "répartitions" sont reliées via Repartir
        return repartirRepository.findAll().stream()
                .map(Repartir::getRepartition)
                .distinct()
                .toList();
    }
}
