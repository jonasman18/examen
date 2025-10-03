package com.example.examen.model;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class ExamenParcoursId implements Serializable {
    private Long idExamen;
    private Long idParcours;

    public ExamenParcoursId() {}

    public ExamenParcoursId(Long idExamen, Long idParcours) {
        this.idExamen = idExamen;
        this.idParcours = idParcours;
    }

    public Long getIdExamen() { return idExamen; }
    public void setIdExamen(Long idExamen) { this.idExamen = idExamen; }

    public Long getIdParcours() { return idParcours; }
    public void setIdParcours(Long idParcours) { this.idParcours = idParcours; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExamenParcoursId)) return false;
        ExamenParcoursId that = (ExamenParcoursId) o;
        return Objects.equals(idExamen, that.idExamen) &&
                Objects.equals(idParcours, that.idParcours);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExamen, idParcours);
    }
}
