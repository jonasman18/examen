package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "surveillant")
public class Surveillant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_surveillant", nullable = false, updatable = false)
    private Long idSurveillant;

    @Column(name = "nom_surveillant", nullable = false)
    @NotNull(message = "Le nom du surveillant est obligatoire")
    @Size(min = 2, max = 100, message = "Le nom doit contenir entre 2 et 100 caractères")
    private String nomSurveillant;

    @Column(name = "groupe_surveillant")
    @Size(max = 50, message = "Le groupe du surveillant ne doit pas dépasser 50 caractères")
    private String groupeSurveillant;

    @Column(name = "numero_salle", nullable = false)
    @NotNull(message = "Le numéro de la salle est obligatoire")
    @Size(min = 1, max = 10, message = "Le numéro de la salle doit contenir entre 1 et 10 caractères")
    private String numeroSalle;

    @Column(name = "contact")
    @Size(max = 20, message = "Le contact ne doit pas dépasser 20 caractères")
    private String contact;

    // Constructeurs
    public Surveillant() {}

    public Surveillant(String nomSurveillant, String groupeSurveillant, String numeroSalle, String contact) {
        this.nomSurveillant = nomSurveillant;
        this.groupeSurveillant = groupeSurveillant;
        this.numeroSalle = numeroSalle;
        this.contact = contact;
    }

    // Getters et Setters
    public Long getIdSurveillant() {
        return idSurveillant;
    }

    public void setIdSurveillant(Long idSurveillant) {
        this.idSurveillant = idSurveillant;
    }

    public String getNomSurveillant() {
        return nomSurveillant;
    }

    public void setNomSurveillant(String nomSurveillant) {
        this.nomSurveillant = nomSurveillant;
    }

    public String getGroupeSurveillant() {
        return groupeSurveillant;
    }

    public void setGroupeSurveillant(String groupeSurveillant) {
        this.groupeSurveillant = groupeSurveillant;
    }

    public String getNumeroSalle() {
        return numeroSalle;
    }

    public void setNumeroSalle(String numeroSalle) {
        this.numeroSalle = numeroSalle;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }
}