package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "niveau")
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_niveau", nullable = false)
    private String code_niveau;

    // Constructeurs
    public Niveau() {}
    public Niveau(String code_niveau) { this.code_niveau = code_niveau; }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getLibelle() { return code_niveau; }
    public void setLibelle(String code_niveau) { this.code_niveau = code_niveau; }
}
