package com.example.examen.service;

import com.example.examen.model.PlanningSurveillance;
import com.example.examen.repository.PlanningSurveillanceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlanningSurveillanceService {

    private final PlanningSurveillanceRepository planningRepo;

    public PlanningSurveillanceService(PlanningSurveillanceRepository planningRepo) {
        this.planningRepo = planningRepo;
    }

    public List<PlanningSurveillance> getAll() {
        return planningRepo.findAllWithRelations();
    }

    public PlanningSurveillance save(PlanningSurveillance planning) {
        return planningRepo.save(planning);
    }

    public void delete(Long id) {
        planningRepo.deleteById(id);
    }

    public List<PlanningSurveillance> getByExamen(Long idExamen) {
        return planningRepo.findByExamenId(idExamen);
    }
}
