package com.example.examen.controller;

import com.example.examen.model.Matiere;
import com.example.examen.service.MatiereService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/api/matieres")
public class MatiereController {

    private final MatiereService matiereService;

    public MatiereController(MatiereService matiereService) {
        this.matiereService = matiereService;
    }

    @GetMapping("/view-matieres")
    public String viewMatieres(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("matieres", matiereService.getAllMatieres());
        return "matieres"; // Créez matieres.html si nécessaire
    }

    @GetMapping("/form-matiere")
    public String formMatiere(Model model) {
        model.addAttribute("matiere", new Matiere());
        return "form-matiere"; // Créez form-matiere.html si nécessaire
    }

    @GetMapping("/edit-matiere/{id_matiere}")
    public String editMatiere(@PathVariable Long id_matiere, Model model, RedirectAttributes redirectAttributes) {
        Optional<Matiere> optionalMatiere = matiereService.getMatiereById(id_matiere);
        if (optionalMatiere.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Matière introuvable: " + id_matiere);
            return "redirect:/matiere/view-matieres";
        }
        model.addAttribute("matiere", optionalMatiere.get());
        return "form-matiere";
    }

    @PostMapping("/save-matiere")
    public String saveMatiere(@Valid @ModelAttribute("matiere") Matiere matiere,
                              BindingResult bindingResult,
                              RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form-matiere";
        }
        matiereService.saveMatiere(matiere);
        redirectAttributes.addFlashAttribute("success", "Matière sauvegardée avec succès.");
        return "redirect:/matiere/view-matieres";
    }

    @GetMapping("/delete-matiere/{id_matiere}")
    public String deleteMatiere(@PathVariable Long id_matiere, RedirectAttributes redirectAttributes) {
        Optional<Matiere> optionalMatiere = matiereService.getMatiereById(id_matiere);
        if (optionalMatiere.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Matière introuvable pour suppression: " + id_matiere);
        } else {
            matiereService.deleteMatiere(id_matiere);
            redirectAttributes.addFlashAttribute("success", "Matière supprimée avec succès.");
        }
        return "redirect:/matiere/view-matieres";
    }
}