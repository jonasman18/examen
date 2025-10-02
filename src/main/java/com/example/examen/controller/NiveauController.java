package com.example.examen.controller;

import com.example.examen.model.Niveau;
import com.example.examen.service.NiveauService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Optional;

@Controller
@RequestMapping("/api/niveau")
public class NiveauController {

    private final NiveauService niveauService;

    public NiveauController(NiveauService niveauService) {
        this.niveauService = niveauService;
    }

    @GetMapping("/view-niveaux")
    public String viewNiveaux(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("niveaux", niveauService.getAllNiveaux());
        return "niveaux"; // Créez niveaus.html si nécessaire
    }

    @GetMapping("/form-niveau")
    public String formNiveau(Model model) {
        model.addAttribute("niveau", new Niveau());
        return "form-niveau"; // Créez form-niveau.html si nécessaire
    }

    @GetMapping("/edit-niveau/{id_niveau}")
    public String editNiveau(@PathVariable Long id_niveau, Model model, RedirectAttributes redirectAttributes) {
        Optional<Niveau> optionalNiveau = niveauService.getNiveauById(id_niveau);
        if (optionalNiveau.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Niveau introuvable: " + id_niveau);
            return "redirect:/niveau/view-niveaux";
        }
        model.addAttribute("niveau", optionalNiveau.get());
        return "form-niveau";
    }

    @PostMapping("/save-niveau")
    public String saveNiveau(@Valid @ModelAttribute("niveau") Niveau niveau,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "form-niveau";
        }
        niveauService.saveNiveau(niveau);
        redirectAttributes.addFlashAttribute("success", "Niveau sauvegardé avec succès.");
        return "redirect:/niveau/view-niveaux";
    }

    @GetMapping("/delete-niveau/{id_niveau}")
    public String deleteNiveau(@PathVariable Long id_niveau, RedirectAttributes redirectAttributes) {
        Optional<Niveau> optionalNiveau = niveauService.getNiveauById(id_niveau);
        if (optionalNiveau.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Niveau introuvable pour suppression: " + id_niveau);
        } else {
            niveauService.deleteNiveau(id_niveau);
            redirectAttributes.addFlashAttribute("success", "Niveau supprimé avec succès.");
        }
        return "redirect:/niveau/view-niveaux";
    }
}