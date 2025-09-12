/*
package com.example.examen.service;

import com.example.examen.model.User;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.*;
import com.google.firebase.cloud.FirestoreClient;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class UserService {

    public static final String COLLECTION_NAME = "users"; // Nom de la collection Firestore

    // Récupère l'instance de Firestore
    private Firestore getFirestore() {
        return FirestoreClient.getFirestore();
    }

    // CREATE - Ajouter un nouvel utilisateur
    public String createUser(User user) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();

        // Ajoute un nouveau document avec un ID généré automatiquement
        DocumentReference addedDocRef = db.collection(COLLECTION_NAME).add(user).get();

        // Récupère l'ID auto-généré et le définit dans l'objet user
        String generatedId = addedDocRef.getId();
        user.setId(generatedId);

        // Met à jour le document pour y inclure l'ID comme champ
        addedDocRef.set(user).get();

        return generatedId; // Retourne l'ID du nouveau user
    }

    // READ - Récupérer un utilisateur par son ID
    public User getUser(String id) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        DocumentReference docRef = db.collection(COLLECTION_NAME).document(id);
        ApiFuture<DocumentSnapshot> future = docRef.get();
        DocumentSnapshot document = future.get();

        if (document.exists()) {
            // Convertit le document Firestore en objet Java User
            User user = document.toObject(User.class);
            user.setId(document.getId()); // S'assure que l'ID est défini
            return user;
        } else {
            return null; // Aucun user trouvé avec cet ID
        }
    }

    // READ - Récupérer TOUS les utilisateurs
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<QuerySnapshot> future = db.collection(COLLECTION_NAME).get();
        List<QueryDocumentSnapshot> documents = future.get().getDocuments();

        List<User> userList = new ArrayList<>();
        for (QueryDocumentSnapshot document : documents) {
            User user = document.toObject(User.class);
            user.setId(document.getId()); // S'assure que l'ID est défini
            userList.add(user);
        }
        return userList;
    }

    // UPDATE - Mettre à jour un utilisateur
    public void updateUser(String id, User user) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME).document(id).set(user);
        future.get(); // Attend que la mise à jour soit terminée
    }

    // DELETE - Supprimer un utilisateur
    public void deleteUser(String id) throws ExecutionException, InterruptedException {
        Firestore db = getFirestore();
        ApiFuture<WriteResult> future = db.collection(COLLECTION_NAME).document(id).delete();
        future.get(); // Attend que la suppression soit terminée
    }
}
*/
