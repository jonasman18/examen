package com.example.examen.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class RepartirId implements Serializable {

    private String numeroSalle;
    private Long idRepartition;

    public RepartirId() {}

    public RepartirId(String numeroSalle, Long idRepartition) {
        this.numeroSalle = numeroSalle;
        this.idRepartition = idRepartition;
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public Long getIdRepartition() {
        return idRepartition;
    }

    public void setIdRepartition(Long idRepartition) {
        this.idRepartition = idRepartition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RepartirId that)) return false;
        return Objects.equals(numeroSalle, that.numeroSalle) &&
                Objects.equals(idRepartition, that.idRepartition);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroSalle, idRepartition);
    }
}
