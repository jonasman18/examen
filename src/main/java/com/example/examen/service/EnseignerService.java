
package com.example.examen.service;
import com.example.examen.model.Enseigner;
import com.example.examen.model.EnseignerId;
import com.example.examen.repository.EnseignerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnseignerService {

    @Autowired
    private EnseignerRepository enseignerRepository;

    public List<Enseigner> getAll() {
        return enseignerRepository.findAll();
    }

    public Enseigner save(Enseigner enseigner) {
        return enseignerRepository.save(enseigner);
    }

    public void delete(EnseignerId id) {
        enseignerRepository.deleteById(id);
    }
}
