/*
package com.example.examen.controller;

import com.example.examen.model.User;
import com.example.examen.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // CREATE - Endpoint pour créer un nouvel utilisateur
    @PostMapping
    public String createUser(@RequestBody User user) throws ExecutionException, InterruptedException {
        return userService.createUser(user);
    }

    // READ - Endpoint pour récupérer un user par son ID
    @GetMapping("/{id}")
    public User getUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        return userService.getUser(id);
    }

    // READ - Endpoint pour récupérer tous les users
    @GetMapping
    public List<User> getAllUsers() throws ExecutionException, InterruptedException {
        return userService.getAllUsers();
    }

    // UPDATE - Endpoint pour mettre à jour un user
    @PutMapping("/{id}")
    public String updateUser(@PathVariable String id, @RequestBody User user) throws ExecutionException, InterruptedException {
        userService.updateUser(id, user);
        return "User with ID: " + id + " has been updated.";
    }

    // DELETE - Endpoint pour supprimer un user
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable String id) throws ExecutionException, InterruptedException {
        userService.deleteUser(id);
        return "User with ID: " + id + " has been deleted.";
    }
}
*/
