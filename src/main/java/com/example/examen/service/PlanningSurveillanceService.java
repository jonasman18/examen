package com.example.examen.service;

import com.example.examen.model.*;
import com.example.examen.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlanningSurveillanceService {

    private final PlanningSurveillanceRepository planningRepo;
    private final ExamenRepository examenRepo;
    private final ParcoursRepository parcoursRepo;
    private final SalleRepository salleRepo;
    private final SurveillantRepository surveillantRepo;

    public PlanningSurveillanceService(
            PlanningSurveillanceRepository planningRepo,
            ExamenRepository examenRepo,
            ParcoursRepository parcoursRepo,
            SalleRepository salleRepo,
            SurveillantRepository surveillantRepo
    ) {
        this.planningRepo = planningRepo;
        this.examenRepo = examenRepo;
        this.parcoursRepo = parcoursRepo;
        this.salleRepo = salleRepo;
        this.surveillantRepo = surveillantRepo;
    }

    // 🔹 Récupérer tous les plannings
    public List<PlanningSurveillance> getAll() {
        return planningRepo.findAll();
    }

    // 🔹 Récupérer un planning par ID
    public Optional<PlanningSurveillance> getById(Long id) {
        return planningRepo.findById(id);
    }

    // 🔹 Récupérer les plannings par examen
    public List<PlanningSurveillance> getByExamen(Long idExamen) {
        Examen examen = examenRepo.findById(idExamen)
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));
        return planningRepo.findByExamen(examen);
    }

    // 🔹 Récupérer les plannings par salle
    public List<PlanningSurveillance> getBySalle(String numeroSalle) {
        Salle salle = salleRepo.findById(numeroSalle)
                .orElseThrow(() -> new RuntimeException("Salle introuvable"));
        return planningRepo.findBySalle(salle);
    }

    // 🔹 Récupérer les plannings par surveillant
    public List<PlanningSurveillance> getBySurveillant(Long idSurveillant) {
        Surveillant s = surveillantRepo.findById(idSurveillant)
                .orElseThrow(() -> new RuntimeException("Surveillant introuvable"));
        return planningRepo.findBySurveillant(s);
    }

    // 🔹 Ajouter ou mettre à jour un planning
    public PlanningSurveillance save(PlanningSurveillance planning) {
        if (planning.getExamen() != null && planning.getExamen().getIdExamen() != null) {
            planning.setExamen(
                    examenRepo.findById(planning.getExamen().getIdExamen())
                            .orElseThrow(() -> new RuntimeException("Examen introuvable"))
            );
        }
        if (planning.getParcours() != null && planning.getParcours().getIdParcours() != null) {
            planning.setParcours(
                    parcoursRepo.findById(planning.getParcours().getIdParcours())
                            .orElseThrow(() -> new RuntimeException("Parcours introuvable"))
            );
        }
        if (planning.getSalle() != null && planning.getSalle().getNumeroSalle() != null) {
            planning.setSalle(
                    salleRepo.findById(planning.getSalle().getNumeroSalle())
                            .orElseThrow(() -> new RuntimeException("Salle introuvable"))
            );
        }
        if (planning.getSurveillant() != null && planning.getSurveillant().getIdSurveillant() != null) {
            planning.setSurveillant(
                    surveillantRepo.findById(planning.getSurveillant().getIdSurveillant())
                            .orElseThrow(() -> new RuntimeException("Surveillant introuvable"))
            );
        }

        return planningRepo.save(planning);
    }

    // 🔹 Supprimer un planning
    public void delete(Long id) {
        planningRepo.deleteById(id);
    }
}
