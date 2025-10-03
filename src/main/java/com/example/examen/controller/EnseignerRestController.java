package com.example.examen.controller;

import com.example.examen.model.Enseigner;
import com.example.examen.model.EnseignerId;
import com.example.examen.model.Enseignant;
import com.example.examen.model.Matiere;
import com.example.examen.repository.EnseignerRepository;
import com.example.examen.repository.EnseignantRepository;
import com.example.examen.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enseigner")
@CrossOrigin(origins = "*") // pour React
public class EnseignerRestController {

    @Autowired
    private EnseignerRepository enseignerRepository;

    @Autowired
    private MatiereRepository matiereRepository;

    @Autowired
    private EnseignantRepository enseignantRepository;

    // 🔹 Récupérer toutes les associations
    @GetMapping
    public List<Enseigner> getAll() {
        return enseignerRepository.findAll();
    }

    // 🔹 Ajouter une nouvelle association (matiere ↔ enseignant)
    @PostMapping
    public ResponseEntity<Enseigner> create(@RequestBody Enseigner request) {
        if (request.getMatiere() == null || request.getEnseignant() == null) {
            return ResponseEntity.badRequest().build();
        }

        // Vérifier si matière et enseignant existent
        Matiere matiere = matiereRepository.findById(request.getMatiere().getIdMatiere())
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
        Enseignant enseignant = enseignantRepository.findById(request.getEnseignant().getIdEnseignant())
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));

        Enseigner association = new Enseigner(matiere, enseignant);
        Enseigner saved = enseignerRepository.save(association);

        return ResponseEntity.ok(saved);
    }

    // 🔹 Mettre à jour une association (changer enseignant ou matière)
    @PutMapping("/{idMatiere}/{idEnseignant}")
    public ResponseEntity<Enseigner> update(
            @PathVariable Long idMatiere,
            @PathVariable Long idEnseignant,
            @RequestBody Enseigner request) {

        EnseignerId oldId = new EnseignerId(idMatiere, idEnseignant);

        // Vérifier si l'association existe
        if (!enseignerRepository.existsById(oldId)) {
            return ResponseEntity.notFound().build();
        }

        // Vérifier si nouvelle matière et enseignant existent
        Matiere matiere = matiereRepository.findById(request.getMatiere().getIdMatiere())
                .orElseThrow(() -> new RuntimeException("Matière introuvable"));
        Enseignant enseignant = enseignantRepository.findById(request.getEnseignant().getIdEnseignant())
                .orElseThrow(() -> new RuntimeException("Enseignant introuvable"));

        // Supprimer l'ancienne association (clé composite)
        enseignerRepository.deleteById(oldId);

        // Sauvegarder la nouvelle association
        Enseigner updated = enseignerRepository.save(new Enseigner(matiere, enseignant));

        return ResponseEntity.ok(updated);
    }


    // 🔹 Supprimer une association par idMatiere + idEnseignant
    @DeleteMapping("/{idMatiere}/{idEnseignant}")
    public ResponseEntity<Void> delete(@PathVariable Long idMatiere, @PathVariable Long idEnseignant) {
        EnseignerId id = new EnseignerId(idMatiere, idEnseignant);

        if (!enseignerRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        enseignerRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
