package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "enseigner")
public class Enseigner {

    @EmbeddedId
    private EnseignerId id;

    @ManyToOne
    @MapsId("idMatiere") // 🔥 fait le lien avec EnseignerId.idMatiere
    @JoinColumn(name = "id_matiere", nullable = false)
    private Matiere matiere;

    @ManyToOne
    @MapsId("idEnseignant") // 🔥 fait le lien avec EnseignerId.idEnseignant
    @JoinColumn(name = "id_enseignant", nullable = false)
    private Enseignant enseignant;

    public Enseigner() {}

    public Enseigner(Matiere matiere, Enseignant enseignant) {
        this.matiere = matiere;
        this.enseignant = enseignant;
        this.id = new EnseignerId(matiere.getIdMatiere(), enseignant.getIdEnseignant());
    }

    public EnseignerId getId() { return id; }
    public void setId(EnseignerId id) { this.id = id; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Enseignant getEnseignant() { return enseignant; }
    public void setEnseignant(Enseignant enseignant) { this.enseignant = enseignant; }
}
