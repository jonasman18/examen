package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_matiere")
    private Long idMatiere;

    @Column(name = "nom_matiere", nullable = false)
    private String nomMatiere;

    public Matiere() {}

    public Matiere(String nomMatiere) {
        this.nomMatiere = nomMatiere;
    }

    public Long getIdMatiere() { return idMatiere; }
    public void setIdMatiere(Long idMatiere) { this.idMatiere = idMatiere; }

    public String getNomMatiere() { return nomMatiere; }
    public void setNomMatiere(String nomMatiere) { this.nomMatiere = nomMatiere; }
}