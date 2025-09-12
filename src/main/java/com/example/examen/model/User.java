package com.example.examen.model;

import java.util.Date;

public class User {
    private String id; // Cet ID correspondra au document ID dans Firestore
    private String email;
    private String displayName;
    private String role; // Par exemple : "admin", "user", "customer"
    private Date createdAt;

    // Constructeurs
    public User() {
        // Constructeur par défaut nécessaire pour Firestore
    }

    public User(String email, String displayName, String role) {
        this.email = email;
        this.displayName = displayName;
        this.role = role;
        this.createdAt = new Date(); // Définit la date de création automatiquement
    }

    // Getters et Setters (Obligatoires pour que Firestore mappe les données)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", email='" + email + '\'' +
                ", displayName='" + displayName + '\'' +
                ", role='" + role + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}