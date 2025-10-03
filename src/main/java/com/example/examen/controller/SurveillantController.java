/*package com.example.examen.controller;

import com.example.examen.model.Surveillant;
import com.example.examen.service.SurveillantService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class SurveillantController {

    private final SurveillantService surveillantService;

    public SurveillantController(SurveillantService surveillantService) {
        this.surveillantService = surveillantService;
    }

    @GetMapping
    public List<Surveillant> getAllSurveillants() {
        return surveillantService.getAllSurveillants();
    }

    @GetMapping("/{id}")
    public Optional<Surveillant> getSurveillantById(@PathVariable Long id) {
        return surveillantService.getSurveillantById(id);
    }

    @PostMapping
    public Surveillant createSurveillant(@RequestBody Surveillant surveillant) {
        return surveillantService.saveSurveillant(surveillant);
    }

    @PutMapping("/{id}")
    public Surveillant updateSurveillant(@PathVariable Long id, @RequestBody Surveillant surveillant) {
        surveillant.setIdSurveillant(id);
        return surveillantService.saveSurveillant(surveillant);
    }

    @DeleteMapping("/{id}")
    public void deleteSurveillant(@PathVariable Long id) {
        surveillantService.deleteSurveillant(id);
    }
} */