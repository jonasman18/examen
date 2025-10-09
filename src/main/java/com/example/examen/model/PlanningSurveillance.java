package com.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "planning_surveillance")
public class PlanningSurveillance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPlanning;

    @ManyToOne
    @JoinColumn(name = "id_examen", nullable = false)
    private Examen examen;


    @ManyToOne
    @JoinColumn(name = "numero_salle", referencedColumnName = "numero_salle", nullable = false)
    private Salle salle;

    @ManyToOne
    @JoinColumn(name = "id_surveillant", nullable = false)
    private Surveillant surveillant;

    @Column(name = "heure_debut")
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin")
    private LocalDateTime heureFin;

    // --- Getters & Setters ---
    public Long getIdPlanning() {
        return idPlanning;
    }

    public void setIdPlanning(Long idPlanning) {
        this.idPlanning = idPlanning;
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }



    public Salle getSalle() {
        return salle;
    }

    public void setSalle(Salle salle) {
        this.salle = salle;
    }

    public Surveillant getSurveillant() {
        return surveillant;
    }

    public void setSurveillant(Surveillant surveillant) {
        this.surveillant = surveillant;
    }

    public LocalDateTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalDateTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalDateTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalDateTime heureFin) {
        this.heureFin = heureFin;
    }
}
