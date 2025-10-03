package com.example.examen.service;

import com.example.examen.model.Enseignant;
import com.example.examen.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnseignantService {

    private final EnseignantRepository enseignantRepository;

    @Autowired
    public EnseignantService(EnseignantRepository enseignantRepository) {
        this.enseignantRepository = enseignantRepository;
    }

    // 🔹 Récupérer tous les enseignants
    public List<Enseignant> getAllEnseignants() {
        return enseignantRepository.findAll();
    }

    // 🔹 Récupérer un enseignant par ID (Optional pour éviter NullPointer)
    public Optional<Enseignant> getEnseignantById(Long id) {
        return enseignantRepository.findById(id);
    }

    // 🔹 Sauvegarder ou mettre à jour un enseignant
    public Enseignant saveEnseignant(Enseignant enseignant) {
        return enseignantRepository.save(enseignant);
    }

    // 🔹 Supprimer un enseignant par ID
    public void deleteEnseignant(Long id) {
        enseignantRepository.deleteById(id);
    }
}
