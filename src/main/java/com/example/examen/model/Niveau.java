package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "niveau")
public class Niveau {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_niveau")
    private Long idNiveau;

    @Column(name = "code_niveau", nullable = false)
    private String codeNiveau;

    public Niveau() {}

    public Niveau(String codeNiveau) {
        this.codeNiveau = codeNiveau;
    }

    public Long getIdNiveau() { return idNiveau; }
    public void setIdNiveau(Long idNiveau) { this.idNiveau = idNiveau; }

    public String getCodeNiveau() { return codeNiveau; }
    public void setCodeNiveau(String codeNiveau) { this.codeNiveau = codeNiveau; }
}