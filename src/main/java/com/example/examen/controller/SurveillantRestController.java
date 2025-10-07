package com.example.examen.controller;

import com.example.examen.model.Surveillant;
import com.example.examen.service.SurveillantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/surveillants") // cohérent avec ton frontend
@CrossOrigin(origins = "*") // autorise React
public class SurveillantRestController {

    @Autowired
    private SurveillantService surveillantService;

    // GET : liste
    @GetMapping
    public List<Surveillant> getAllSurveillants() {
        return surveillantService.getAllSurveillants();
    }

    // GET : un surveillant
    @GetMapping("/{id}")
    public ResponseEntity<Surveillant> getSurveillantById(@PathVariable Long id) {
        return surveillantService.getSurveillantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST : ajout (⚠️ on ignore contact)
    @PostMapping
    public ResponseEntity<Surveillant> createSurveillant(@RequestBody Surveillant surveillant) {
        //surveillant.setContact(null); // toujours ignoré
        Surveillant saved = surveillantService.saveSurveillant(surveillant);
        return ResponseEntity.ok(saved);
    }

    // PUT : modification (⚠️ on ignore contact)
    @PutMapping("/{id}")
    public ResponseEntity<Surveillant> updateSurveillant(@PathVariable Long id, @RequestBody Surveillant surveillant) {
        surveillant.setIdSurveillant(id);
        //surveillant.setContact(null); // toujours ignoré
        Surveillant updated = surveillantService.saveSurveillant(surveillant);
        return ResponseEntity.ok(updated);
    }

    // DELETE : suppression
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSurveillant(@PathVariable Long id) {
        surveillantService.deleteSurveillant(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 Récupérer les surveillants d'une salle donnée
    @GetMapping("/by-salle/{numeroSalle}")
    public List<Surveillant> getBySalle(@PathVariable String numeroSalle) {
        return surveillantService.getBySalle(numeroSalle);
    }

}
