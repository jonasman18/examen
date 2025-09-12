package com.example.examen.controller;

import com.example.examen.model.Examen;
import com.example.examen.service.ExamenService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/examens")
public class ExamenController {

    private final ExamenService examenService;

    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @GetMapping
    public List<Examen> getAllExamens() {
        return examenService.getAllExamens();
    }

    @GetMapping("/{id}")
    public Examen getExamenById(@PathVariable Long id) {
        return examenService.getExamenById(id);
    }

    @PostMapping
    public Examen createExamen(@RequestBody Examen examen) {
        return examenService.saveExamen(examen);
    }

    @PutMapping("/{id}")
    public Examen updateExamen(@PathVariable Long id, @RequestBody Examen examen) {
        examen.setIdExamen(id);
        return examenService.saveExamen(examen);
    }

    @DeleteMapping("/{id}")
    public void deleteExamen(@PathVariable Long id) {
        examenService.deleteExamen(id);
    }
}