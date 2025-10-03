package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "surveiller")
public class Surveiller {

    @EmbeddedId
    private SurveillerId id;

    @ManyToOne
    @MapsId("idExamen")
    @JoinColumn(name = "id_examen")
    private Examen examen;

    @ManyToOne
    @MapsId("idSurveillant")
    @JoinColumn(name = "id_surveillant")
    private Surveillant surveillant;

    public Surveiller() {}

    public Surveiller(Examen examen, Surveillant surveillant) {
        this.examen = examen;
        this.surveillant = surveillant;
        this.id = new SurveillerId(examen.getIdExamen(), surveillant.getIdSurveillant());
    }

    // Getters & Setters
    public SurveillerId getId() {
        return id;
    }

    public void setId(SurveillerId id) {
        this.id = id;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }

    public Surveillant getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(Surveillant surveillant) {
        this.surveillant = surveillant;
    }
}
