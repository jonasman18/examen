package com.example.examen.service;

import com.example.examen.model.Surveillant;
import com.example.examen.repository.SurveillantRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SurveillantService {

    private final SurveillantRepository surveillantRepository;

    public SurveillantService(SurveillantRepository surveillantRepository) {
        this.surveillantRepository = surveillantRepository;
    }

    public List<Surveillant> getAllSurveillants() {
        return surveillantRepository.findAll();
    }

    public Optional<Surveillant> getSurveillantById(Long id) {
        return surveillantRepository.findById(id);
    }

    public Surveillant saveSurveillant(Surveillant surveillant) {
        // ⚡ On ignore "contact" (il sera null par défaut ou inchangé)
        //surveillant.setContact(null);
        return surveillantRepository.save(surveillant);
    }

    public void deleteSurveillant(Long id) {
        surveillantRepository.deleteById(id);
    }

    // 🔹 Nouvel ajout : récupérer les surveillants d'une salle donnée
    public List<Surveillant> getBySalle(String numeroSalle) {
        return surveillantRepository.findByNumeroSalle(numeroSalle);
    }
}
