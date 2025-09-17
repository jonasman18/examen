package com.example.examen.controller;

import com.example.examen.model.Examen;
import com.example.examen.repository.MatiereRepository;
import com.example.examen.repository.NiveauRepository;
import com.example.examen.service.ExamenService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@Controller
@RequestMapping("/examen")
public class ExamenFormController {

    private final ExamenService examenService;
    private final MatiereRepository matiereRepository;
    private final NiveauRepository niveauRepository;

    public ExamenFormController(ExamenService examenService,
                                MatiereRepository matiereRepository,
                                NiveauRepository niveauRepository) {
        this.examenService = examenService;
        this.matiereRepository = matiereRepository;
        this.niveauRepository = niveauRepository;
    }

    @GetMapping("/view-examens")
    public String viewExamens(Model model, RedirectAttributes redirectAttributes) {
        model.addAttribute("examens", examenService.getAllExamens());
        return "examens";
    }

    @GetMapping("/form-examen")
    public String formExamen(Model model) {
        model.addAttribute("examen", new Examen());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("niveaux", niveauRepository.findAll());
        return "form-examen";
    }

    @GetMapping("/edit-examen/{id_examen}")
    public String editExamen(@PathVariable Long id_examen, Model model, RedirectAttributes redirectAttributes) {
        Optional<Examen> optionalExamen = Optional.ofNullable(examenService.getExamenById(id_examen));
        if (optionalExamen.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Examen introuvable: " + id_examen);
            return "redirect:/examen/view-examens";
        }
        model.addAttribute("examen", optionalExamen.get());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("niveaux", niveauRepository.findAll());
        return "form-examen";
    }

    @PostMapping("/save-examen")
    public String saveExamen(@Valid @ModelAttribute("examen") Examen examen,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        if (examen.getHeureDebut() != null && examen.getHeureFin() != null &&
                examen.getHeureDebut().isAfter(examen.getHeureFin())) {
            bindingResult.rejectValue("heureFin", "error.heureFin", "L'heure de fin doit être après l'heure de début.");
        }
        if (examen.getDuree() != null && examen.getDuree().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("duree", "error.duree", "La durée doit être positive.");
        }

        if (bindingResult.hasErrors()) {
            model.addAttribute("matieres", matiereRepository.findAll());
            model.addAttribute("niveaux", niveauRepository.findAll());
            return "form-examen";
        }

        try {
            if (examen.getMatiere() != null && examen.getMatiere().getIdMatiere() != null) {
                Long idMatiere = examen.getMatiere().getIdMatiere();
                matiereRepository.findById(idMatiere)
                        .ifPresentOrElse(examen::setMatiere,
                                () -> { throw new IllegalArgumentException("Matière introuvable: " + idMatiere); });
            }
            if (examen.getNiveau() != null && examen.getNiveau().getIdNiveau() != null) {
                Long idNiveau = examen.getNiveau().getIdNiveau();
                niveauRepository.findById(idNiveau)
                        .ifPresentOrElse(examen::setNiveau,
                                () -> { throw new IllegalArgumentException("Niveau introuvable: " + idNiveau); });
            }
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            return "redirect:/examen/form-examen";
        }

        examenService.saveExamen(examen);
        redirectAttributes.addFlashAttribute("success", "Examen sauvegardé avec succès.");
        return "redirect:/examen/view-examens";
    }

    @GetMapping("/delete-examen/{id_examen}")
    public String deleteExamen(@PathVariable Long id_examen, RedirectAttributes redirectAttributes) {
        Optional<Examen> optionalExamen = Optional.ofNullable(examenService.getExamenById(id_examen));
        if (optionalExamen.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Examen introuvable pour suppression: " + id_examen);
        } else {
            examenService.deleteExamen(id_examen);
            redirectAttributes.addFlashAttribute("success", "Examen supprimé avec succès.");
        }
        return "redirect:/examen/view-examens";
    }
}