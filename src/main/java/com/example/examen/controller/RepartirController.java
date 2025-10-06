package com.example.examen.controller;

import com.example.examen.model.Repartir;
import com.example.examen.model.RepartirId;
import com.example.examen.service.RepartirService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/repartir")
@CrossOrigin(origins = "*")
public class RepartirController {

    private final RepartirService service;

    public RepartirController(RepartirService service) {
        this.service = service;
    }

    @GetMapping
    public List<Repartir> getAll() {
        return service.getAll();
    }
    @GetMapping("/par-salle")
    public Map<String, List<Repartir>> getRepartitionParSalle() {
        return service.getRepartitionParSalle();
    }

    @PostMapping
    public Repartir create(@RequestBody Repartir repartir) {
        return service.save(repartir);
    }

    // ✅ Mise à jour (avec remplacement de la clé composite)
    @PutMapping("/{oldNumeroSalle}/{oldIdRepartition}")
    public Repartir update(
            @PathVariable String oldNumeroSalle,
            @PathVariable Long oldIdRepartition,
            @RequestBody Repartir newRepartir
    ) {
        // Supprimer l’ancienne association
        service.delete(new RepartirId(oldNumeroSalle, oldIdRepartition));

        // Enregistrer la nouvelle association
        return service.save(newRepartir);
    }

    @DeleteMapping("/{numeroSalle}/{idRepartition}")
    public void delete(@PathVariable String numeroSalle, @PathVariable Long idRepartition) {
        service.delete(new RepartirId(numeroSalle, idRepartition));
    }
}
