package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "salle")
public class Salle {

    @Id
    @Column(name = "numero_salle", nullable = false, updatable = false)
    @NotNull(message = "Le numéro de la salle est obligatoire")
    @Size(min = 1, max = 10, message = "Le numéro de la salle doit contenir entre 1 et 10 caractères")
    private String numeroSalle;

    @Column(name = "capacite_max", nullable = false)
    @NotNull(message = "La capacité maximale est obligatoire")
    @Min(value = 1, message = "La capacité maximale doit être supérieure ou égale à 1")
    private Integer capaciteMax;

    @Column(name = "nbr_surveillant", nullable = false)
    @NotNull(message = "Le nombre de surveillants est obligatoire")
    @Min(value = 0, message = "Le nombre de surveillants doit être supérieur ou égal à 0")
    private Integer nbrSurveillant;

    // Constructeurs
    public Salle() {}

    public Salle(String numeroSalle, Integer capaciteMax, Integer nbrSurveillant) {
        this.numeroSalle = numeroSalle;
        this.capaciteMax = capaciteMax;
        this.nbrSurveillant = nbrSurveillant;
    }

    // Getters et Setters
    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public Integer getCapaciteMax() {
        return capaciteMax;
    }

    public void setCapaciteMax(Integer capaciteMax) {
        this.capaciteMax = capaciteMax;
    }

    public Integer getNbrSurveillant() {
        return nbrSurveillant;
    }

    public void setNbrSurveillant(Integer nbrSurveillant) {
        this.nbrSurveillant = nbrSurveillant;
    }
}