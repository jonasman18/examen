package com.example.examen.service;

import com.example.examen.model.Examen;
import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.model.Parcours;
import com.example.examen.repository.ExamenParcoursRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExamenParcoursService {

    private final ExamenParcoursRepository repo;

    public ExamenParcoursService(ExamenParcoursRepository repo) {
        this.repo = repo;
    }

    public List<ExamenParcours> getAll() {
        return repo.findAll();
    }

    public ExamenParcours save(ExamenParcours ep) {
        return repo.save(ep);
    }

    public void delete(ExamenParcoursId id) {
        repo.deleteById(id);
    }

    // ✅ Supprime toutes les associations pour un examen
    @Transactional
    public void deleteAllByExamen(Long idExamen) {
        repo.deleteAll(
                repo.findAll().stream()
                        .filter(ep -> ep.getExamen() != null && ep.getExamen().getIdExamen().equals(idExamen))
                        .toList()
        );
    }

    // ✅ Met à jour globalement toutes les associations d’un examen
    @Transactional
    public void updateGlobal(Long idExamen, List<Long> parcoursIds) {
        // Supprimer les anciennes associations
        deleteAllByExamen(idExamen);

        // Ajouter les nouvelles associations
        parcoursIds.forEach(pid -> {
            ExamenParcours ep = new ExamenParcours();
            ep.setId(new ExamenParcoursId(idExamen, pid));
            ep.setExamen(new Examen(idExamen));
            ep.setParcours(new Parcours(pid));
            repo.save(ep);
        });
    }
}
