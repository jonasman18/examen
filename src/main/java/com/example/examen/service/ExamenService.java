package com.example.examen.service;

import com.example.examen.model.Examen;
import com.example.examen.repository.ExamenRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;

    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Examen getExamenById(Long id) {
        return examenRepository.findById(id).orElse(null);
    }

    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public void deleteExamen(Long id) {
        examenRepository.deleteById(id);
    }
}