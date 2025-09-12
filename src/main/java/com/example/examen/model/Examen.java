package com.example.examen.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen")
    private Long idExamen;

    @ManyToOne
    @JoinColumn(name = "id_matiere", referencedColumnName = "id_matiere")
    private Matiere matiere;

    @ManyToOne
    @JoinColumn(name = "id_niveau", referencedColumnName = "id_niveau")
    private Niveau niveau;

    @Column(name = "date_examen", nullable = false)
    private LocalDate dateExamen;

    @Column(name = "heure_debut", nullable = false)
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin", nullable = false)
    private LocalDateTime heureFin;

    @Column(name = "duree")
    private Double duree;

    @Column(name = "numero_salle")
    private String numeroSalle;

    @Column(name = "session")
    private String session;

    public Examen() {}

    public Examen(Matiere matiere, Niveau niveau, LocalDate dateExamen,
                  LocalDateTime heureDebut, LocalDateTime heureFin,
                  Double duree, String numeroSalle, String session) {
        this.matiere = matiere;
        this.niveau = niveau;
        this.dateExamen = dateExamen;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = duree;
        this.numeroSalle = numeroSalle;
        this.session = session;
    }

    public Long getIdExamen() { return idExamen; }
    public void setIdExamen(Long idExamen) { this.idExamen = idExamen; }

    public Matiere getMatiere() { return matiere; }
    public void setMatiere(Matiere matiere) { this.matiere = matiere; }

    public Niveau getNiveau() { return niveau; }
    public void setNiveau(Niveau niveau) { this.niveau = niveau; }

    public LocalDate getDateExamen() { return dateExamen; }
    public void setDateExamen(LocalDate dateExamen) { this.dateExamen = dateExamen; }

    public LocalDateTime getHeureDebut() { return heureDebut; }
    public void setHeureDebut(LocalDateTime heureDebut) { this.heureDebut = heureDebut; }

    public LocalDateTime getHeureFin() { return heureFin; }
    public void setHeureFin(LocalDateTime heureFin) { this.heureFin = heureFin; }

    public Double getDuree() { return duree; }
    public void setDuree(Double duree) { this.duree = duree; }

    public String getNumeroSalle() { return numeroSalle; }
    public void setNumeroSalle(String numeroSalle) { this.numeroSalle = numeroSalle; }

    public String getSession() { return session; }
    public void setSession(String session) { this.session = session; }
}