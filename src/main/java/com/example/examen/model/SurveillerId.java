package com.example.examen.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class SurveillerId implements Serializable {

    private Long idExamen;
    private Long idSurveillant;

    public SurveillerId() {}

    public SurveillerId(Long idExamen, Long idSurveillant) {
        this.idExamen = idExamen;
        this.idSurveillant = idSurveillant;
    }

    // Getters & Setters
    public Long getIdExamen() {
        return idExamen;
    }
    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }

    public Long getIdSurveillant() {
        return idSurveillant;
    }
    public void setIdSurveillant(Long idSurveillant) {
        this.idSurveillant = idSurveillant;
    }

    // equals & hashCode (OBLIGATOIRE pour @Embeddable)
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SurveillerId)) return false;
        SurveillerId that = (SurveillerId) o;
        return Objects.equals(idExamen, that.idExamen) &&
                Objects.equals(idSurveillant, that.idSurveillant);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExamen, idSurveillant);
    }
}
