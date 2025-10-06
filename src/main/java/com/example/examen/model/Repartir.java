package com.example.examen.model;

import jakarta.persistence.*;

@Entity
@Table(name = "repartir")
public class Repartir {

    @EmbeddedId
    private RepartirId id;

    @ManyToOne
    @MapsId("numeroSalle") // lie le champ dans la clé composite
    @JoinColumn(name = "numero_salle", nullable = false)
    private Salle salle;

    @ManyToOne
    @MapsId("idRepartition")
    @JoinColumn(name = "id_repartition", nullable = false)
    private Repartition repartition;

    public Repartir() {}

    public Repartir(Salle salle, Repartition repartition) {
        this.salle = salle;
        this.repartition = repartition;
        this.id = new RepartirId(salle.getNumeroSalle(), repartition.getIdRepartition());
    }

    public RepartirId getId() {
        return id;
    }

    public void setId(RepartirId id) {
        this.id = id;
    }

    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
        if (this.id == null) this.id = new RepartirId();
        this.id.setNumeroSalle(salle.getNumeroSalle());
    }

    public Repartition getRepartition() {
        return repartition;
    }

    public void setRepartition(Repartition repartition) {
        this.repartition = repartition;
        if (this.id == null) this.id = new RepartirId();
        this.id.setIdRepartition(repartition.getIdRepartition());
    }
}
