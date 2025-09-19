package com.example.examen.service;

import com.example.examen.model.Repartition;
import com.example.examen.repository.RepartitionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RepartitionService {

    private final RepartitionRepository repartitionRepository;

    @Autowired
    public RepartitionService(RepartitionRepository repartitionRepository) {
        this.repartitionRepository = repartitionRepository;
    }

    public List<Repartition> getAllRepartitions() {
        return repartitionRepository.findAll();
    }

    public Repartition getRepartitionById(Long id) {
        Optional<Repartition> repartition = repartitionRepository.findById(id);
        return repartition.orElse(null); // Retourne null si non trouvé
    }

    public Repartition saveRepartition(Repartition repartition) {
        return repartitionRepository.save(repartition);
    }

    public void deleteRepartition(Long id) {
        repartitionRepository.deleteById(id);
    }
}