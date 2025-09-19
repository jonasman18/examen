package com.example.examen.controller;

import com.example.examen.model.Parcours;
import com.example.examen.service.ParcoursService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parcours")
public class ParcoursController {

    private final ParcoursService parcoursService;

    public ParcoursController(ParcoursService parcoursService) {
        this.parcoursService = parcoursService;
    }

    @GetMapping
    public List<Parcours> getAllParcours() {
        return parcoursService.getAllParcours();
    }

    @GetMapping("/{id}")
    public Parcours getParcoursById(@PathVariable Long id) {
        return parcoursService.getParcoursById(id);
    }

    @PostMapping
    public Parcours createParcours(@RequestBody Parcours parcours) {
        return parcoursService.saveParcours(parcours);
    }

    @PutMapping("/{id}")
    public Parcours updateParcours(@PathVariable Long id, @RequestBody Parcours parcours) {
        parcours.setIdParcours(id);
        return parcoursService.saveParcours(parcours);
    }

    @DeleteMapping("/{id}")
    public void deleteParcours(@PathVariable Long id) {
        parcoursService.deleteParcours(id);
    }
}