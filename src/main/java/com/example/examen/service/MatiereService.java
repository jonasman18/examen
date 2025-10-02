package com.example.examen.service;

import com.example.examen.model.Matiere;
import com.example.examen.repository.MatiereRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatiereService {

    private final MatiereRepository matiereRepository;

    @Autowired
    public MatiereService(MatiereRepository matiereRepository) {
        this.matiereRepository = matiereRepository;
    }

    public Optional<Matiere> getMatiereById(Long id_matiere) {
        return matiereRepository.findById(id_matiere);
    }
    public List<Matiere> getAllMatieres() {
        return matiereRepository.findAll();
    }

    public Matiere saveMatiere(Matiere matiere) {
        return matiereRepository.save(matiere);
    }

    public void deleteMatiere(Long id_matiere) {
        matiereRepository.deleteById(id_matiere);
    }
}