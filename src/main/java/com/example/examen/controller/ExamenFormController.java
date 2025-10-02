package com.example.examen.controller;

import com.example.examen.model.Examen;
import com.example.examen.model.Matiere;
import com.example.examen.model.Niveau;
import com.example.examen.repository.MatiereRepository;
import com.example.examen.repository.NiveauRepository;
import com.example.examen.service.ExamenService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.Optional;

@Controller
@RequestMapping("/examen") // -> pages Thymeleaf accessibles sous /examen/...
public class ExamenFormController {

    private static final Logger logger = LoggerFactory.getLogger(ExamenFormController.class);

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
    public String viewExamens(Model model) {
        model.addAttribute("examens", examenService.getAllExamens());
        return "examens"; // examens.html
    }

    @GetMapping("/form-examen")
    public String formExamen(Model model) {
        Examen examen = new Examen();
        examen.setMatiere(new Matiere()); // pour binding
        examen.setNiveau(new Niveau());   // pour binding
        model.addAttribute("examen", examen);
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("niveaux", niveauRepository.findAll());
        return "form-examen";
    }

    @GetMapping("/edit-examen/{id_examen}")
    public String editExamen(@PathVariable("id_examen") Long idExamen, Model model, RedirectAttributes redirectAttributes) {
        Optional<com.example.examen.model.Examen> examenOpt = examenService.getExamenById(idExamen);
        if (examenOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Examen introuvable: " + idExamen);
            return "redirect:/examen/view-examens";
        }
        model.addAttribute("examen", examenOpt.get());
        model.addAttribute("matieres", matiereRepository.findAll());
        model.addAttribute("niveaux", niveauRepository.findAll());
        return "form-examen";
    }

    @PostMapping("/save-examen")
    public String saveExamen(@Valid @ModelAttribute("examen") Examen examen,
                             BindingResult bindingResult,
                             Model model,
                             RedirectAttributes redirectAttributes) {
        logger.info("Requête saveExamen : ID={}, MatiereID={}, NiveauID={}",
                examen.getIdExamen(),
                examen.getMatiere() != null ? examen.getMatiere().getIdMatiere() : null,
                examen.getNiveau() != null ? examen.getNiveau().getIdNiveau() : null
        );

        // validations supplémentaires
        if (examen.getHeureDebut() != null && examen.getHeureFin() != null &&
                examen.getHeureDebut().isAfter(examen.getHeureFin())) {
            bindingResult.rejectValue("heureFin", "error.heureFin", "L'heure de fin doit être après l'heure de début.");
        }
        if (examen.getDuree() != null && examen.getDuree().compareTo(BigDecimal.ZERO) <= 0) {
            bindingResult.rejectValue("duree", "error.duree", "La durée doit être positive.");
        }

        if (bindingResult.hasErrors()) {
            logger.warn("Erreurs de validation : {}", bindingResult.getAllErrors());
            model.addAttribute("matieres", matiereRepository.findAll());
            model.addAttribute("niveaux", niveauRepository.findAll());
            return "form-examen"; // vue correcte en cas d'erreur
        }

        // persist
        examenService.saveExamen(examen);
        redirectAttributes.addFlashAttribute("success", "Examen enregistré avec succès.");
        return "redirect:/examen/view-examens";
    }

    @GetMapping("/delete-examen/{id_examen}")
    public String deleteExamen(@PathVariable("id_examen") Long idExamen, RedirectAttributes redirectAttributes) {
        Optional<com.example.examen.model.Examen> examenOpt = examenService.getExamenById(idExamen);
        if (examenOpt.isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Examen introuvable pour suppression: " + idExamen);
        } else {
            examenService.deleteExamen(idExamen);
            redirectAttributes.addFlashAttribute("success", "Examen supprimé avec succès.");
        }
        return "redirect:/examen/view-examens";
    }
}
