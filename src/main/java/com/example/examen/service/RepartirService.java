package com.example.examen.service;

import com.example.examen.model.Repartir;
import com.example.examen.model.RepartirId;
import com.example.examen.repository.RepartirRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RepartirService {

    private final RepartirRepository repartirRepository;

    public RepartirService(RepartirRepository repartirRepository) {
        this.repartirRepository = repartirRepository;
    }

    public List<Repartir> getAll() {
        return repartirRepository.findAll();
    }

    public Repartir save(Repartir repartir) {
        return repartirRepository.save(repartir);
    }

    public void delete(RepartirId id) {
        repartirRepository.deleteById(id);
    }

    // 🆕 Nouvelle méthode : regrouper les répartitions par salle
    public Map<String, List<Repartir>> getRepartitionParSalle() {
        List<Repartir> toutes = repartirRepository.findAll();
        // Groupement par numéro de salle
        return toutes.stream().collect(Collectors.groupingBy(r -> r.getId().getNumeroSalle()));
    }
}
