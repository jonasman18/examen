package com.example.examen.controller;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.service.ExamenParcoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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

    // 🔹 Mettre à jour une association (changer examen/parcours)
    @PutMapping("/{idExamen}/{idParcours}")
    public ResponseEntity<ExamenParcours> update(
            @PathVariable Long idExamen,
            @PathVariable Long idParcours,
            @RequestBody ExamenParcours ep) {

        ExamenParcoursId oldId = new ExamenParcoursId(idExamen, idParcours);

        // Supprime l'ancien (clé composite)
        service.delete(oldId);

        // 🟢 Crée un nouvel objet propre
        ExamenParcours newEp = new ExamenParcours();
        newEp.setId(new ExamenParcoursId(
                ep.getExamen().getIdExamen(),
                ep.getParcours().getIdParcours()
        ));
        newEp.setExamen(ep.getExamen());
        newEp.setParcours(ep.getParcours());

        // Sauvegarde le nouveau
        ExamenParcours saved = service.save(newEp);
        return ResponseEntity.ok(saved);
    }


    // 🔹 Supprimer une seule association
    @DeleteMapping("/{idExamen}/{idParcours}")
    public ResponseEntity<Void> delete(@PathVariable Long idExamen, @PathVariable Long idParcours) {
        service.delete(new ExamenParcoursId(idExamen, idParcours));
        return ResponseEntity.noContent().build();
    }

    // 🔹 Supprimer toutes les associations d’un examen
    @DeleteMapping("/delete-all/{idExamen}")
    public ResponseEntity<Void> deleteAllByExamen(@PathVariable Long idExamen) {
        service.deleteAllByExamen(idExamen);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Mise à jour globale des parcours pour un examen
    @PostMapping("/update-global")
    public ResponseEntity<String> updateGlobal(@RequestBody Map<String, Object> payload) {
        Long idExamen = Long.valueOf(payload.get("idExamen").toString());
        @SuppressWarnings("unchecked")
        List<Integer> parcoursIds = (List<Integer>) payload.get("parcoursIds");

        service.updateGlobal(idExamen, parcoursIds.stream().map(Long::valueOf).toList());
        return ResponseEntity.ok("Mise à jour globale réussie !");
    }


}
