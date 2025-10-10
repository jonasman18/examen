package com.example.examen.controller;

import com.example.examen.model.PlanningSurveillance;
import com.example.examen.service.PlanningSurveillanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/planning-surveillance")
@CrossOrigin(origins = "http://localhost:5173") // ton front
public class PlanningSurveillanceController {

    private final PlanningSurveillanceService service;

    public PlanningSurveillanceController(PlanningSurveillanceService service) {
        this.service = service;
    }

    @GetMapping
    public List<PlanningSurveillance> getAll() {
        return service.getAll();
    }

    @GetMapping("/examen/{idExamen}")
    public List<PlanningSurveillance> getByExamen(@PathVariable Long idExamen) {
        return service.getByExamen(idExamen);
    }

    @PostMapping
    public PlanningSurveillance save(@RequestBody PlanningSurveillance planning) {
        return service.save(planning);
    }

    @PutMapping("/{id}")
    public PlanningSurveillance update(@PathVariable Long id, @RequestBody PlanningSurveillance planning) {
        planning.setIdPlanning(id);
        return service.save(planning);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}

