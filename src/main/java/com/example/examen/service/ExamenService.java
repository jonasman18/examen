package com.example.examen.service;

import com.example.examen.model.Examen;
import com.example.examen.repository.ExamenRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExamenService {

    private final ExamenRepository examenRepository;

    public ExamenService(ExamenRepository examenRepository) {
        this.examenRepository = examenRepository;
    }

    public List<Examen> getAllExamens() {
        return examenRepository.findAll();
    }

    public Optional<Examen> getExamenById(Long id_examen) {
        return examenRepository.findById(id_examen);
    }

    public Examen saveExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    public void deleteExamen(Long id_examen) {
        examenRepository.deleteById(id_examen);
    }
}
