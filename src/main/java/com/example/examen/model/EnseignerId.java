package com.example.examen.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class EnseignerId implements Serializable {
    private Long idMatiere;
    private Long idEnseignant;

    public EnseignerId() {}

    public EnseignerId(Long idMatiere, Long idEnseignant) {
        this.idMatiere = idMatiere;
        this.idEnseignant = idEnseignant;
    }

    public Long getIdMatiere() { return idMatiere; }
    public void setIdMatiere(Long idMatiere) { this.idMatiere = idMatiere; }

    public Long getIdEnseignant() { return idEnseignant; }
    public void setIdEnseignant(Long idEnseignant) { this.idEnseignant = idEnseignant; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EnseignerId)) return false;
        EnseignerId that = (EnseignerId) o;
        return Objects.equals(idMatiere, that.idMatiere) &&
                Objects.equals(idEnseignant, that.idEnseignant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMatiere, idEnseignant);
    }
}
