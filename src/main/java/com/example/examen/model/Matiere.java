package com.example.examen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToMany(mappedBy = "matiere", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("matiere")
    private List<Enseigner> enseignerList = new ArrayList<>();

    public List<Enseigner> getEnseignerList() {
        return enseignerList;
    }

    public void setEnseignerList(List<Enseigner> enseignerList) {
        this.enseignerList = enseignerList;
    }

}