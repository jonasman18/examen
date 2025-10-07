package com.example.examen.controller;

import com.example.examen.model.PlanningSurveillance;
import com.example.examen.service.PlanningSurveillanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planning-surveillance")
@CrossOrigin(origins = "*")
public class PlanningSurveillanceController {

    private final PlanningSurveillanceService service;

    public PlanningSurveillanceController(PlanningSurveillanceService service) {
        this.service = service;
    }

    // 🔹 Récupérer tous les plannings
    @GetMapping
    public List<PlanningSurveillance> getAll() {
        return service.getAll();
    }

    // 🔹 Récupérer un planning par ID
    @GetMapping("/{id}")
    public ResponseEntity<PlanningSurveillance> getById(@PathVariable Long id) {
        Optional<PlanningSurveillance> p = service.getById(id);
        return p.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter un planning
    @PostMapping
    public ResponseEntity<PlanningSurveillance> create(@RequestBody PlanningSurveillance planning) {
        PlanningSurveillance saved = service.save(planning);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Mettre à jour un planning
    @PutMapping("/{id}")
    public ResponseEntity<PlanningSurveillance> update(
            @PathVariable Long id,
            @RequestBody PlanningSurveillance planning
    ) {
        Optional<PlanningSurveillance> existing = service.getById(id);
        if (existing.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        planning.setIdPlanning(id);
        PlanningSurveillance updated = service.save(planning);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Supprimer un planning
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer les plannings par examen
    @GetMapping("/by-examen/{idExamen}")
    public List<PlanningSurveillance> getByExamen(@PathVariable Long idExamen) {
        return service.getByExamen(idExamen);
    }

    // 🔹 Récupérer les plannings par salle
    @GetMapping("/by-salle/{numeroSalle}")
    public List<PlanningSurveillance> getBySalle(@PathVariable String numeroSalle) {
        return service.getBySalle(numeroSalle);
    }

    // 🔹 Récupérer les plannings par surveillant
    @GetMapping("/by-surveillant/{idSurveillant}")
    public List<PlanningSurveillance> getBySurveillant(@PathVariable Long idSurveillant) {
        return service.getBySurveillant(idSurveillant);
    }
}
