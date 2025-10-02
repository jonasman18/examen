package com.example.examen.controller;

import com.example.examen.model.Examen;
import com.example.examen.service.ExamenService;
import org.springframework.web.bind.annotation.*;

/* import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/examens")
@CrossOrigin(origins = "http://localhost:5173")
public class ExamenController {

    private final ExamenService examenService;

    public ExamenController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @GetMapping
    public List<Examen> getAll() {
        return examenService.getAllExamens();
    }

    @GetMapping("/{id}")
    public Optional<Examen> getById(@PathVariable Long id) {
        return examenService.getExamenById(id);
    }

    @PostMapping
    public Examen create(@RequestBody Examen examen) {
        return examenService.saveExamen(examen);
    }

    @PutMapping("/{id}")
    public Examen update(@PathVariable Long id, @RequestBody Examen examen) {
        examen.setIdExamen(id);
        return examenService.saveExamen(examen);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        examenService.deleteExamen(id);
    }
}

 */
