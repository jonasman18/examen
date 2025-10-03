package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "examenparcours")
public class ExamenParcours {

    @EmbeddedId
    private ExamenParcoursId id;

    @ManyToOne
    @MapsId("idExamen") // lie le champ de la clé composite
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;

    @ManyToOne
    @MapsId("idParcours")
    @JoinColumn(name = "id_parcours", nullable = false)
    private Parcours parcours;

    public ExamenParcours() {}

    public ExamenParcours(Examen examen, Parcours parcours) {
        this.examen = examen;
        this.parcours = parcours;
        this.id = new ExamenParcoursId(examen.getIdExamen(), parcours.getIdParcours());
    }

    public ExamenParcoursId getId() {
        return id;
    }

    public void setId(ExamenParcoursId id) {
        this.id = id;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Parcours getParcours() {
        return parcours;
    }

    public void setParcours(Parcours parcours) {
        this.parcours = parcours;
    }
}
