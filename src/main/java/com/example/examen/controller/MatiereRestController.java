package com.example.examen.controller;

import com.example.examen.model.Matiere;
import com.example.examen.service.MatiereService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/matieres") // 🔥 cohérent avec ton frontend
@CrossOrigin(origins = "*") // pour React + Vite
public class MatiereRestController {

    @Autowired
    private MatiereService matiereService;

    @GetMapping
    public List<Matiere> getAllMatieres() {
        return matiereService.getAllMatieres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matiere> getMatiereById(@PathVariable Long id) {
        return matiereService.getMatiereById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Matiere> createMatiere(@RequestBody Matiere matiere) {
        Matiere saved = matiereService.saveMatiere(matiere);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Matiere> updateMatiere(@PathVariable Long id, @RequestBody Matiere matiere) {
        matiere.setIdMatiere(id);
        Matiere saved = matiereService.saveMatiere(matiere);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMatiere(@PathVariable Long id) {
        try {
            matiereService.deleteMatiere(id);
            return ResponseEntity.noContent().build(); // 204 OK
        } catch (Exception e) {
            // ⚠️ Si contrainte FK (matiere utilisée dans un examen), renvoie 409 Conflict
            return ResponseEntity.status(409).build();
        }
    }

}
