package com.example.examen.service;

import com.example.examen.model.*;
import com.example.examen.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveillerService {

    private final SurveillerRepository surveillerRepo;
    private final SurveillantRepository surveillantRepo;
    private final ExamenRepository examenRepo;

    public SurveillerService(
            SurveillerRepository surveillerRepo,
            SurveillantRepository surveillantRepo,
            ExamenRepository examenRepo
    ) {
        this.surveillerRepo = surveillerRepo;
        this.surveillantRepo = surveillantRepo;
        this.examenRepo = examenRepo;
    }

    // 🔹 Liste de toutes les surveillances (Examens ↔ Surveillants)
    public List<Surveiller> getAll() {
        return surveillerRepo.findAll();
    }

    // 🔹 Ajouter ou modifier une association
    public Surveiller save(Surveiller s) {
        // Vérifie que l'examen et le surveillant existent
        Optional<Examen> examenOpt = examenRepo.findById(s.getExamen().getIdExamen());
        Optional<Surveillant> surveillantOpt = surveillantRepo.findById(s.getSurveillant().getIdSurveillant());

        if (examenOpt.isEmpty() || surveillantOpt.isEmpty()) {
            throw new RuntimeException("Examen ou Surveillant introuvable !");
        }

        s.setExamen(examenOpt.get());
        s.setSurveillant(surveillantOpt.get());

        return surveillerRepo.save(s);
    }

    // 🔹 Supprimer une association
    public void delete(Long idExamen, Long idSurveillant) {
        SurveillerId id = new SurveillerId(idExamen, idSurveillant);

        if (!surveillerRepo.existsById(id)) {
            throw new RuntimeException("Association introuvable !");
        }

        surveillerRepo.deleteById(id);
    }

    // 🔹 Récupérer les surveillants d’un examen
    public List<Surveiller> getByExamen(Long idExamen) {
        return surveillerRepo.findAll().stream()
                .filter(s -> s.getExamen().getIdExamen().equals(idExamen))
                .toList();
    }

    // 🔹 Récupérer les examens d’un surveillant
    public List<Surveiller> getBySurveillant(Long idSurveillant) {
        return surveillerRepo.findAll().stream()
                .filter(s -> s.getSurveillant().getIdSurveillant().equals(idSurveillant))
                .toList();
    }
}
