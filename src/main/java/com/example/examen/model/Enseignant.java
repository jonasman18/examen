package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "enseignant")
public class Enseignant {

    @Id
    @Column(name = "id_enseignant", nullable = false, updatable = false)
    private Long idEnseignant;

    @Column(name = "nom_enseignant", nullable = false)
    @NotNull(message = "Le nom de l'enseignant est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomEnseignant;

    @Column(name = "grade")
    @Size(max = 50, message = "Le grade ne doit pas dépasser 50 caractères")
    private String grade;

    // Constructeurs
    public Enseignant() {}

    public Enseignant(Long idEnseignant, String nomEnseignant, String grade) {
        this.idEnseignant = idEnseignant;
        this.nomEnseignant = nomEnseignant;
        this.grade = grade;
    }

    // Getters et Setters
    public Long getIdEnseignant() {
        return idEnseignant;
    }

    public void setIdEnseignant(Long idEnseignant) {
        this.idEnseignant = idEnseignant;
    }

    public String getNomEnseignant() {
        return nomEnseignant;
    }

    public void setNomEnseignant(String nomEnseignant) {
        this.nomEnseignant = nomEnseignant;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
}