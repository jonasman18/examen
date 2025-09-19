package com.example.examen.service;

import com.example.examen.model.Surveillant;
import com.example.examen.repository.SurveillantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveillantService {

    private final SurveillantRepository surveillantRepository;

    @Autowired
    public SurveillantService(SurveillantRepository surveillantRepository) {
        this.surveillantRepository = surveillantRepository;
    }

    public List<Surveillant> getAllSurveillants() {
        return surveillantRepository.findAll();
    }

    public Surveillant getSurveillantById(Long id) {
        Optional<Surveillant> surveillant = surveillantRepository.findById(id);
        return surveillant.orElse(null); // Retourne null si non trouvé
    }

    public Surveillant saveSurveillant(Surveillant surveillant) {
        return surveillantRepository.save(surveillant);
    }

    public void deleteSurveillant(Long id) {
        surveillantRepository.deleteById(id);
    }
}