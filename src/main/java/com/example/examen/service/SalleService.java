package com.example.examen.service;

import com.example.examen.model.Salle;
import com.example.examen.repository.SalleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SalleService {

    private final SalleRepository salleRepository;

    @Autowired
    public SalleService(SalleRepository salleRepository) {
        this.salleRepository = salleRepository;
    }

    public List<Salle> getAllSalles() {
        return salleRepository.findAll();
    }

    public Salle getSalleById(String numeroSalle) {
        Optional<Salle> salle = salleRepository.findById(numeroSalle);
        return salle.orElse(null);
    }

    public Salle saveSalle(Salle salle) {
        return salleRepository.save(salle);
    }

    public void deleteSalle(String numeroSalle) {
        salleRepository.deleteById(numeroSalle);
    }
}