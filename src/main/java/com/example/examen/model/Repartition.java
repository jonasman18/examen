package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "repartition")
public class Repartition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repartition", nullable = false, updatable = false)
    private Long idRepartition;

    @Column(name = "groupe")
    @Size(max = 50, message = "Le groupe ne doit pas dépasser 50 caractères")
    private String groupe;

    @Column(name = "etudiants_debut")
    @Size(max = 100, message = "Les étudiants de début ne doivent pas dépasser 100 caractères")
    private String etudiantsDebut;

    @Column(name = "etudiants_fin")
    @Size(max = 100, message = "Les étudiants de fin ne doivent pas dépasser 100 caractères")
    private String etudiantsFin;

    // Constructeurs
    public Repartition() {}

    public Repartition(String groupe, String etudiantsDebut, String etudiantsFin) {
        this.groupe = groupe;
        this.etudiantsDebut = etudiantsDebut;
        this.etudiantsFin = etudiantsFin;
    }

    // Getters et Setters
    public Long getIdRepartition() {
        return idRepartition;
    }

    public void setIdRepartition(Long idRepartition) {
        this.idRepartition = idRepartition;
    }

    public String getGroupe() {
        return groupe;
    }

    public void setGroupe(String groupe) {
        this.groupe = groupe;
    }

    public String getEtudiantsDebut() {
        return etudiantsDebut;
    }

    public void setEtudiantsDebut(String etudiantsDebut) {
        this.etudiantsDebut = etudiantsDebut;
    }

    public String getEtudiantsFin() {
        return etudiantsFin;
    }

    public void setEtudiantsFin(String etudiantsFin) {
        this.etudiantsFin = etudiantsFin;
    }
}