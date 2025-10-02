package com.example.examen.service;

import com.example.examen.model.Niveau;
import com.example.examen.repository.NiveauRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NiveauService {

    private final NiveauRepository niveauRepository;

    @Autowired
    public NiveauService(NiveauRepository niveauRepository) {
        this.niveauRepository = niveauRepository;
    }

    public List<Niveau> getAllNiveaux() {
        return niveauRepository.findAll();
    }

    public Niveau saveNiveau(Niveau niveau) {
        return niveauRepository.save(niveau);
    }

    public void deleteNiveau(Long id_niveau) {
        niveauRepository.deleteById(id_niveau);
    }
    public Optional<Niveau> getNiveauById(Long id_niveau) {
        return niveauRepository.findById(id_niveau);
    }
}