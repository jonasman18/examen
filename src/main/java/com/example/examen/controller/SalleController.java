/* package com.example.examen.controller;

import com.example.examen.model.Salle;
import com.example.examen.service.SalleService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/salles")
public class SalleController {

    private final SalleService salleService;

    public SalleController(SalleService salleService) {
        this.salleService = salleService;
    }

    @GetMapping
    public List<Salle> getAllSalles() {
        return salleService.getAllSalles();
    }

    @GetMapping("/{numeroSalle}")
    public Optional<Salle> getSalleById(@PathVariable String numeroSalle) {
        return salleService.getSalleById(numeroSalle);
    }

    @PostMapping
    public Salle createSalle(@RequestBody Salle salle) {
        return salleService.saveSalle(salle);
    }

    @PutMapping("/{numeroSalle}")
    public Salle updateSalle(@PathVariable String numeroSalle, @RequestBody Salle salle) {
        salle.setNumeroSalle(numeroSalle);
        return salleService.saveSalle(salle);
    }

    @DeleteMapping("/{numeroSalle}")
    public void deleteSalle(@PathVariable String numeroSalle) {
        salleService.deleteSalle(numeroSalle);
    }
}

 */