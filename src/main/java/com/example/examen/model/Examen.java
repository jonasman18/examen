package com.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "examen") // Nom exact de la table dans PostgreSQL
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // auto-incrément
    @Column(name = "id_examen")
    private Long idExamen;

    @Column(name = "id_matiere", nullable = false)
    private Long idMatiere;

    @Column(name = "id_niveau", nullable = false)
    private Long idNiveau;

    @Column(name = "date_examen", nullable = false)
    private LocalDate dateExamen;

    @Column(name = "heure_debut", nullable = false)
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private LocalDateTime heureFin;

    @Column(name = "duree")
    private Double duree; // numeric en PostgreSQL → Double en Java

    // Constructeurs
    public Examen() {}

    public Examen(Long idMatiere, Long idNiveau, LocalDate dateExamen,
                  LocalDateTime heureDebut, LocalDateTime heureFin, Double duree) {
        this.idMatiere = idMatiere;
        this.idNiveau = idNiveau;
        this.dateExamen = dateExamen;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = duree;
    }

    // Getters & Setters
    public Long getIdExamen() { return idExamen; }
    public void setIdExamen(Long idExamen) { this.idExamen = idExamen; }

    public Long getIdMatiere() { return idMatiere; }
    public void setIdMatiere(Long idMatiere) { this.idMatiere = idMatiere; }

    public Long getIdNiveau() { return idNiveau; }
    public void setIdNiveau(Long idNiveau) { this.idNiveau = idNiveau; }

    public LocalDate getDateExamen() { return dateExamen; }
    public void setDateExamen(LocalDate dateExamen) { this.dateExamen = dateExamen; }

    public LocalDateTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalDateTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalDateTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalDateTime heureFin) { this.heureFin = heureFin; }

    public Double getDuree() { return duree; }
    public void setDuree(Double duree) { this.duree = duree; }

    @Override
    public String toString() {
        return "Examen{" +
                "idExamen=" + idExamen +
                ", idMatiere=" + idMatiere +
                ", idNiveau=" + idNiveau +
                ", dateExamen=" + dateExamen +
                ", heureDebut=" + heureDebut +
                ", heureFin=" + heureFin +
                ", duree=" + duree +
                '}';
    }
}