package com.example.examen.controller;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.service.ExamenParcoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examenparcours")
@CrossOrigin(origins = "*")
public class ExamenParcoursRestController {

    private final ExamenParcoursService service;

    public ExamenParcoursRestController(ExamenParcoursService service) {
        this.service = service;
    }

    // 🔹 Récupérer toutes les associations
    @GetMapping
    public List<ExamenParcours> getAll() {
        return service.getAll();
    }

    // 🔹 Ajouter une association
    @PostMapping
    public ExamenParcours create(@RequestBody ExamenParcours ep) {
        return service.save(ep);
    }

    // 🔹 Mettre à jour une association existante
    @PutMapping("/{idExamen}/{idParcours}")
    public ResponseEntity<ExamenParcours> update(
            @PathVariable Long idExamen,
            @PathVariable Long idParcours,
            @RequestBody ExamenParcours ep) {

        ExamenParcoursId oldId = new ExamenParcoursId(idExamen, idParcours);

        // ⚠️ Si l’association n’existe pas → 404
        //if (!service.existsById(oldId)) {
           // return ResponseEntity.notFound().build();
        //}

        // Supprimer l’ancienne association
        service.delete(oldId);

        // Sauvegarder la nouvelle
        ExamenParcours saved = service.save(ep);
        return ResponseEntity.ok(saved);
    }

    // 🔹 Supprimer une association
    @DeleteMapping("/{idExamen}/{idParcours}")
    public ResponseEntity<Void> delete(@PathVariable Long idExamen, @PathVariable Long idParcours) {
        service.delete(new ExamenParcoursId(idExamen, idParcours));
        return ResponseEntity.noContent().build();
    }
}
