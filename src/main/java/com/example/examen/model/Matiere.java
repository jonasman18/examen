package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "matiere")
public class Matiere {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_matiere", nullable = false)
    private String nom_matiere;

    // Constructeurs
    public Matiere() {}
    public Matiere(String nom) { this.nom_matiere = nom_matiere; }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNom() { return nom_matiere; }
    public void setNom(String nom) { this.nom_matiere = nom; }
}