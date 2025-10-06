package com.example.examen.controller;

import com.example.examen.model.Examen;
import com.example.examen.model.Matiere;
import com.example.examen.model.Niveau;
import com.example.examen.model.Repartition;
import com.example.examen.repository.MatiereRepository;
import com.example.examen.repository.NiveauRepository;
import com.example.examen.service.ExamenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api") // base /api
@CrossOrigin(origins = "*")

public class ExamenRestController {

    private final ExamenService examenService;
    private final MatiereRepository matiereRepository;
    private final NiveauRepository niveauRepository;

    @Autowired
    public ExamenRestController(ExamenService examenService,
                                MatiereRepository matiereRepository,
                                NiveauRepository niveauRepository) {
        this.examenService = examenService;
        this.matiereRepository = matiereRepository;
        this.niveauRepository = niveauRepository;
    }

    @GetMapping("/examens")
    public List<Examen> getAllExamens() {
        return examenService.getAllExamens();
    }

    @GetMapping("/examens/{id}")
    public ResponseEntity<Examen> getExamenById(@PathVariable Long id) {
        Optional<Examen> optionalExamen = examenService.getExamenById(id);
        return optionalExamen.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/examens")
    public ResponseEntity<Examen> createExamen(@RequestBody Examen examen) {
        Examen saved = examenService.saveExamen(examen);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/examens/{id}")
    public ResponseEntity<Examen> updateExamen(@PathVariable Long id, @RequestBody Examen examen) {
        examen.setIdExamen(id);
        Examen updated = examenService.saveExamen(examen);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/examens/{id}")
    public ResponseEntity<Void> deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
        return ResponseEntity.noContent().build();
    }

    /*@GetMapping("/matieres")
    public List<Matiere> getMatieres() {
        return matiereRepository.findAll();
    } */

    @GetMapping("/niveaux")
    public List<Niveau> getNiveaux() {
        return niveauRepository.findAll();
    }

    @GetMapping("/{id}/repartitions")
    public List<Repartition> getRepartitionByExamen(@PathVariable Long id) {
        return examenService.getRepartition(id);
    }

}
