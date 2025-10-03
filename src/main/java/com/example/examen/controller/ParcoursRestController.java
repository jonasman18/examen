package com.example.examen.controller;

import com.example.examen.model.Parcours;
import com.example.examen.service.ParcoursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcours")
@CrossOrigin(origins = "http://localhost:5173") // pour autoriser ton frontend React
public class ParcoursRestController {

    private final ParcoursService parcoursService;

    @Autowired
    public ParcoursRestController(ParcoursService parcoursService) {
        this.parcoursService = parcoursService;
    }

    // 🔹 Récupérer tous les parcours
    @GetMapping
    public List<Parcours> getAllParcours() {
        return parcoursService.getAllParcours();
    }

    // 🔹 Récupérer un parcours par ID
    @GetMapping("/{id}")
    public ResponseEntity<Parcours> getParcoursById(@PathVariable Long id) {
        return parcoursService.getParcoursById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 Créer un nouveau parcours
    @PostMapping
    public ResponseEntity<Parcours> createParcours(@RequestBody Parcours parcours) {
        Parcours saved = parcoursService.saveParcours(parcours);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Mettre à jour un parcours
    @PutMapping("/{id}")
    public ResponseEntity<Parcours> updateParcours(@PathVariable Long id, @RequestBody Parcours parcours) {
        parcours.setIdParcours(id);
        Parcours updated = parcoursService.saveParcours(parcours);
        return ResponseEntity.ok(updated);
    }

    // 🔹 Supprimer un parcours
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteParcours(@PathVariable Long id) {
        parcoursService.deleteParcours(id);
        return ResponseEntity.noContent().build();
    }
}
