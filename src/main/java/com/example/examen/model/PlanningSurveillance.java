package com.example.examen.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "planning_surveillance")
@Data
public class PlanningSurveillance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_planning")
    private Long idPlanning;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_examen", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "repartitions", "surveillances"})
    private Examen examen;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "numero_salle", referencedColumnName = "numero_salle", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "repartitions"})
    private Salle salle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_surveillant", nullable = false)
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Surveillant surveillant;

    @Column(name = "heure_debut")
    private String heureDebut;

    @Column(name = "heure_fin")
    private String heureFin;

    @Column(name = "date_examen")
    private String dateExamen;
}
