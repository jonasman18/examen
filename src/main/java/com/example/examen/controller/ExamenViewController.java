package com.example.examen.controller;

import com.example.examen.service.ExamenService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExamenViewController {

    private final ExamenService examenService;

    public ExamenViewController(ExamenService examenService) {
        this.examenService = examenService;
    }

    @GetMapping("/view-examens")
    public String viewExamens(Model model) {
        model.addAttribute("examens", examenService.getAllExamens());
        return "examens"; // nom du fichier HTML
    }
}