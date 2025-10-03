package com.example.examen.controller;

import com.example.examen.model.Enseignant;
import com.example.examen.service.EnseignantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseignants") // cohérent avec ton frontend
@CrossOrigin(origins = "*") // autorise React/Vite (http://localhost:5173)
public class EnseignantRestController {

    @Autowired
    private EnseignantService enseignantService;

    // 🔹 GET: tous les enseignants
    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }

    // 🔹 GET: un enseignant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Enseignant> getEnseignantById(@PathVariable Long id) {
        return enseignantService.getEnseignantById(id)
                .map(ResponseEntity::ok) // si trouvé => 200
                .orElse(ResponseEntity.notFound().build()); // sinon => 404
    }

    // 🔹 POST: ajouter un enseignant (ID doit être saisi manuellement)
    @PostMapping
    public ResponseEntity<Enseignant> createEnseignant(@RequestBody Enseignant enseignant) {
        if (enseignant.getIdEnseignant() == null) {
            return ResponseEntity.badRequest().build(); // ID obligatoire
        }
        Enseignant saved = enseignantService.saveEnseignant(enseignant);
        return ResponseEntity.ok(saved);
    }

    // 🔹 PUT: modifier un enseignant
    @PutMapping("/{id}")
    public ResponseEntity<Enseignant> updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        enseignant.setIdEnseignant(id);
        Enseignant updated = enseignantService.saveEnseignant(enseignant);
        return ResponseEntity.ok(updated);
    }

    // 🔹 DELETE: supprimer un enseignant
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
        return ResponseEntity.noContent().build();
    }
}
