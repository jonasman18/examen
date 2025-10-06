package com.example.examen.controller;

import com.example.examen.service.RepartirService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repartition-salle")
@CrossOrigin(origins = "*")
public class RepartitionSalleController {

    private final RepartirService repartirService;

    public RepartitionSalleController(RepartirService repartirService) {
        this.repartirService = repartirService;
    }

    @GetMapping
    public List<Map<String, Object>> getRepartitionParSalle() {
        return (List<Map<String, Object>>) repartirService.getRepartitionParSalle();
    }
}
