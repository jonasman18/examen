package com.example.examen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "examen")
public class Examen {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_examen")
    private Long idExamen;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_matiere", referencedColumnName = "id_matiere")
    @NotNull(message = "La matière est obligatoire")
    private Matiere matiere;

    @ManyToOne(optional = false)
    @JoinColumn(name = "id_niveau", referencedColumnName = "id_niveau")
    @NotNull(message = "Le niveau est obligatoire")
    private Niveau niveau;

    @ManyToOne
    @JoinColumn(name = "id_repartition")
    private Repartition repartition;

    @Column(name = "date_examen", nullable = false)
    @NotNull(message = "La date est obligatoire")
    @FutureOrPresent(message = "La date ne peut pas être dans le passé")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate dateExamen;

    @Column(name = "heure_debut", nullable = false)
    @NotNull(message = "L'heure de début est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime heureDebut;

    @Column(name = "heure_fin", nullable = false)
    @NotNull(message = "L'heure de fin est obligatoire")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime heureFin;

    @Column(name = "duree", columnDefinition = "NUMERIC", nullable = true)
    private BigDecimal duree;

    @Column(name = "numero_salle")
    @Size(max = 20, message = "Numéro de salle trop long")
    private String numeroSalle;

    @Column(name = "session")
    @Size(max = 50, message = "Session trop longue")
    private String session;

    @OneToMany(mappedBy = "examen", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnoreProperties("examen")
    private List<ExamenParcours> examenParcoursList;

    public List<ExamenParcours> getExamenParcoursList() {
        return examenParcoursList;
    }
    public void setExamenParcoursList(List<ExamenParcours> examenParcoursList) {
        this.examenParcoursList = examenParcoursList;
    }

    public Examen() {
        this.matiere = new Matiere(); // Initialisation par défaut
        this.niveau = new Niveau(); // Initialisation par défaut
    }

    public Examen(Matiere matiere, Niveau niveau, LocalDate dateExamen,
                  LocalDateTime heureDebut, LocalDateTime heureFin,
                  BigDecimal duree, String numeroSalle, String session) {
        this.matiere = matiere;
        this.niveau = niveau;
        this.dateExamen = dateExamen;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.duree = duree;
        this.numeroSalle = numeroSalle;
        this.session = session;
    }

    public Examen(Long idExamen) {
        this.idExamen = idExamen;
    }

    // Getters et setters (déjà bons, mais ajoutez des logs si besoin)
    public Long getIdExamen() {
        return idExamen;
    }

    public void setIdExamen(Long idExamen) {
        this.idExamen = idExamen;
    }

    public Matiere getMatiere() {
        return matiere;
    }

    public void setMatiere(Matiere matiere) {
        this.matiere = matiere;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    public LocalDate getDateExamen() {
        return dateExamen;
    }

    public void setDateExamen(LocalDate dateExamen) {
        this.dateExamen = dateExamen;
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

    public BigDecimal getDuree() {
        return duree;
    }

    public void setDuree(BigDecimal duree) {
        this.duree = duree;
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
