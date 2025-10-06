package com.example.examen.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "parcours")
public class Parcours {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_parcours", nullable = false, updatable = false)
    private Long idParcours;

    @Column(name = "code_parcours", nullable = false, unique = true)
    @NotNull(message = "Le code du parcours est obligatoire")
    @Size(min = 2, max = 10, message = "Le code doit contenir entre 2 et 10 caractères")
    private String codeParcours;

    @Column(name = "libelle_parcours", nullable = false)
    @NotNull(message = "Le libellé du parcours est obligatoire")
    @Size(min = 2, max = 100, message = "Le libellé doit contenir entre 2 et 100 caractères")
    private String libelleParcours;

    // Constructeurs
    public Parcours() {}

    public Parcours(String codeParcours, String libelleParcours) {
        this.codeParcours = codeParcours;
        this.libelleParcours = libelleParcours;
    }

    // Getters et Setters
    public Long getIdParcours() {
        return idParcours;
    }

    public Parcours(Long idParcours) {
        this.idParcours = idParcours;
    }


    public void setIdParcours(Long idParcours) {
        this.idParcours = idParcours;
    }

    public String getCodeParcours() {
        return codeParcours;
    }

    public void setCodeParcours(String codeParcours) {
        this.codeParcours = codeParcours;
    }

    public String getLibelleParcours() {
        return libelleParcours;
    }

    public void setLibelleParcours(String libelleParcours) {
        this.libelleParcours = libelleParcours;
    }
}