package com.example.examen.controller;

import com.example.examen.model.Enseignant;
import com.example.examen.service.EnseignantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EnseignantController {

    private final EnseignantService enseignantService;

    public EnseignantController(EnseignantService enseignantService) {
        this.enseignantService = enseignantService;
    }

    @GetMapping
    public List<Enseignant> getAllEnseignants() {
        return enseignantService.getAllEnseignants();
    }

    @GetMapping("/{id}")
    public Optional<Enseignant> getEnseignantById(@PathVariable Long id) {
        return enseignantService.getEnseignantById(id);
    }

    @PostMapping
    public Enseignant createEnseignant(@RequestBody Enseignant enseignant) {
        return enseignantService.saveEnseignant(enseignant);
    }

    @PutMapping("/{id}")
    public Enseignant updateEnseignant(@PathVariable Long id, @RequestBody Enseignant enseignant) {
        enseignant.setIdEnseignant(id);
        return enseignantService.saveEnseignant(enseignant);
    }

    @DeleteMapping("/{id}")
    public void deleteEnseignant(@PathVariable Long id) {
        enseignantService.deleteEnseignant(id);
    }
}