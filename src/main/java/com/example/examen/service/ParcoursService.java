package com.example.examen.service;

import com.example.examen.model.Parcours;
import com.example.examen.repository.ParcoursRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ParcoursService {

    private final ParcoursRepository parcoursRepository;

    @Autowired
    public ParcoursService(ParcoursRepository parcoursRepository) {
        this.parcoursRepository = parcoursRepository;
    }

    public List<Parcours> getAllParcours() {
        return parcoursRepository.findAll();
    }

    public Optional<Parcours> getParcoursById(Long id) {
        return parcoursRepository.findById(id);
    }

    public Parcours saveParcours(Parcours parcours) {
        return parcoursRepository.save(parcours);
    }

    public void deleteParcours(Long id) {
        parcoursRepository.deleteById(id);
    }
}
