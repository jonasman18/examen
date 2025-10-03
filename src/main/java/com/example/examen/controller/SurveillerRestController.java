package com.example.examen.controller;

import com.example.examen.model.*;
import com.example.examen.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveiller")
@CrossOrigin(origins = "*")
public class SurveillerRestController {

    @Autowired
    private SurveillerRepository surveillerRepository;

    @Autowired
    private ExamenRepository examenRepository;

    @Autowired
    private SurveillantRepository surveillantRepository;

    // 🔹 Liste des associations
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
