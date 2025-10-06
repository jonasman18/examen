package com.example.examen.controller;

import com.example.examen.model.*;
import com.example.examen.repository.*;
import com.example.examen.service.SurveillerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveiller")
@CrossOrigin(origins = "*")
public class SurveillerRestController {

    private final SurveillerRepository surveillerRepository;
    private final ExamenRepository examenRepository;
    private final SurveillantRepository surveillantRepository;

    public SurveillerRestController(
            SurveillerRepository surveillerRepository,
            ExamenRepository examenRepository,
            SurveillantRepository surveillantRepository
    ) {
        this.surveillerRepository = surveillerRepository;
        this.examenRepository = examenRepository;
        this.surveillantRepository = surveillantRepository;
    }

    // 🔹 Liste de toutes les associations
    @GetMapping
    public List<Surveiller> getAll() {
        return surveillerRepository.findAll();
    }

    // 🔹 Ajouter une association (Examen ↔ Surveillant)
    @PostMapping
    public ResponseEntity<Surveiller> create(@RequestBody Surveiller request) {
        if (request.getExamen() == null || request.getSurveillant() == null) {
            return ResponseEntity.badRequest().build();
        }

        Examen examen = examenRepository.findById(request.getExamen().getIdExamen())
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));

        Surveillant surveillant = surveillantRepository.findById(request.getSurveillant().getIdSurveillant())
                .orElseThrow(() -> new RuntimeException("Surveillant introuvable"));

        Surveiller association = new Surveiller(examen, surveillant);
        Surveiller saved = surveillerRepository.save(association);

        return ResponseEntity.ok(saved);
    }

    // 🔹 Modifier une association (changer le surveillant ou l’examen)
    @PutMapping("/{oldIdExamen}/{oldIdSurveillant}")
    public ResponseEntity<Surveiller> update(
            @PathVariable Long oldIdExamen,
            @PathVariable Long oldIdSurveillant,
            @RequestBody Surveiller newAssociation
    ) {
        SurveillerId oldId = new SurveillerId(oldIdExamen, oldIdSurveillant);

        if (!surveillerRepository.existsById(oldId)) {
            return ResponseEntity.notFound().build();
        }

        // Supprimer l’ancienne association
        surveillerRepository.deleteById(oldId);

        // Vérifier que les nouveaux objets existent
        Examen examen = examenRepository.findById(newAssociation.getExamen().getIdExamen())
                .orElseThrow(() -> new RuntimeException("Examen introuvable"));
        Surveillant surveillant = surveillantRepository.findById(newAssociation.getSurveillant().getIdSurveillant())
                .orElseThrow(() -> new RuntimeException("Surveillant introuvable"));

        // Créer la nouvelle association
        Surveiller updated = new Surveiller(examen, surveillant);
        Surveiller saved = surveillerRepository.save(updated);

        return ResponseEntity.ok(saved);
    }

    // 🔹 Supprimer une association
    @DeleteMapping("/{idExamen}/{idSurveillant}")
    public ResponseEntity<Void> delete(@PathVariable Long idExamen, @PathVariable Long idSurveillant) {
        SurveillerId id = new SurveillerId(idExamen, idSurveillant);

        if (!surveillerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        surveillerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
