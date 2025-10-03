package com.example.examen.controller;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.service.ExamenParcoursService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/examenparcours")
@CrossOrigin(origins = "*")
public class ExamenParcoursRestController {

    private final ExamenParcoursService service;

    public ExamenParcoursRestController(ExamenParcoursService service) {
        this.service = service;
    }

    @GetMapping
    public List<ExamenParcours> getAll() {
        return service.getAll();
    }

    @PostMapping
    public ExamenParcours create(@RequestBody ExamenParcours ep) {
        return service.save(ep);
    }

    @DeleteMapping("/{idExamen}/{idParcours}")
    public ResponseEntity<Void> delete(@PathVariable Long idExamen, @PathVariable Long idParcours) {
        service.delete(new ExamenParcoursId(idExamen, idParcours));
        return ResponseEntity.noContent().build();
    }

    
}
