package com.example.examen.controller;

import com.example.examen.model.Repartition;
import com.example.examen.service.RepartitionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/repartitions")
public class RepartitionController {

    private final RepartitionService repartitionService;

    public RepartitionController(RepartitionService repartitionService) {
        this.repartitionService = repartitionService;
    }

    @GetMapping
    public List<Repartition> getAllRepartitions() {
        return repartitionService.getAllRepartitions();
    }

    @GetMapping("/{id}")
    public Repartition getRepartitionById(@PathVariable Long id) {
        return repartitionService.getRepartitionById(id);
    }

    @PostMapping
    public Repartition createRepartition(@RequestBody Repartition repartition) {
        return repartitionService.saveRepartition(repartition);
    }

    @PutMapping("/{id}")
    public Repartition updateRepartition(@PathVariable Long id, @RequestBody Repartition repartition) {
        repartition.setIdRepartition(id);
        return repartitionService.saveRepartition(repartition);
    }

    @DeleteMapping("/{id}")
    public void deleteRepartition(@PathVariable Long id) {
        repartitionService.deleteRepartition(id);
    }
}