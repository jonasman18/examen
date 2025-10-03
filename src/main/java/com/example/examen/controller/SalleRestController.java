package com.example.examen.controller;

import com.example.examen.model.Salle;
import com.example.examen.service.SalleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/salles")
@CrossOrigin(origins = "http://localhost:5173") // autoriser React
public class SalleRestController {

    private final SalleService salleService;

    @Autowired
    public SalleRestController(SalleService salleService) {
        this.salleService = salleService;
    }

    // 🔹 Récupérer toutes les salles
    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    // 🔹 Récupérer une salle par son numéro
    @GetMapping("/{numeroSalle}")
    public ResponseEntity<Salle> getSalleById(@PathVariable String numeroSalle) {
        return salleService.getSalleById(numeroSalle)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Ajouter une salle
    @PostMapping
    public ResponseEntity<Salle> createSalle(@RequestBody Salle salle) {
        Salle saved = salleService.saveSalle(salle);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Modifier une salle
    @PutMapping("/{numeroSalle}")
    public ResponseEntity<Salle> updateSalle(@PathVariable String numeroSalle, @RequestBody Salle salle) {
        salle.setNumeroSalle(numeroSalle); // garder la clé primaire
        Salle updated = salleService.saveSalle(salle);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Supprimer une salle
    @DeleteMapping("/{numeroSalle}")
    public ResponseEntity<Void> deleteSalle(@PathVariable String numeroSalle) {
        salleService.deleteSalle(numeroSalle);
        return ResponseEntity.noContent().build();
    }
}
