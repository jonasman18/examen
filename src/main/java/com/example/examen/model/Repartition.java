package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.util.List;

@Entity
@Table(name = "repartition")
public class Repartition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_repartition", nullable = false, updatable = false)
    private Long idRepartition;

    @OneToMany(mappedBy = "repartition")
    private List<Repartir> repartirs;


    @Column(name = "groupe")
    @Size(max = 50, message = "Le groupe ne doit pas dépasser 50 caractères")
    private String groupe;

    @Column(name = "etudiant_debut")
    @Size(max = 100, message = "Les étudiants de début ne doivent pas dépasser 100 caractères")
    private String etudiantDebut;

    @Column(name = "etudiant_fin")
    @Size(max = 100, message = "Les étudiants de fin ne doivent pas dépasser 100 caractères")
    private String etudiantFin;

    // Constructeurs
    public Repartition() {}

    public Repartition(String groupe, String etudiantDebut, String etudiantFin) {
        this.groupe = groupe;
        this.etudiantDebut = etudiantDebut;
        this.etudiantFin = etudiantFin;
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

    public String getEtudiantDebut() {
        return etudiantDebut;
    }

    public void setEtudiantDebut(String etudiantDebut) {
        this.etudiantDebut = etudiantDebut;
    }

    public String getEtudiantFin() {
        return etudiantFin;
    }

    public void setEtudiantFin(String etudiantFin) {
        this.etudiantFin = etudiantFin;
    }

    public int calculerNombreEtudiants(String debut, String fin) {
        // Extraire les nombres de chaque matricule
        int numDebut = extraireNumero(debut);
        int numFin = extraireNumero(fin);

        // Si un des deux est invalide, on renvoie 0
        if (numDebut == -1 || numFin == -1 || numFin < numDebut) return 0;

        return (numFin - numDebut) + 1; // +1 car inclusif
    }

    private int extraireNumero(String matricule) {
        try {
            String chiffres = matricule.replaceAll("[^0-9]", ""); // garde uniquement les chiffres
            return Integer.parseInt(chiffres);
        } catch (Exception e) {
            return -1;
        }
    }

}