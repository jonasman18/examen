package com.example.examen.service;

import com.example.examen.model.ExamenParcours;
import com.example.examen.model.ExamenParcoursId;
import com.example.examen.repository.ExamenParcoursRepository;
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
}
