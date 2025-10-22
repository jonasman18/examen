package com.example.examen.controller;

import com.example.examen.service.PlanningService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/planning")
@CrossOrigin(origins = "*")
public class PlanningController {

    private final PlanningService planningService;

    public PlanningController(PlanningService planningService) {
        this.planningService = planningService;
    }

    // ✅ Téléchargement du PDF avec filtres facultatifs
    @GetMapping("/pdf")
    public ResponseEntity<byte[]> downloadPlanning(
            @RequestParam(required = false) String niveau,
            @RequestParam(required = false) String parcours,
            @RequestParam(required = false) String enseignant
    ) {
        try {
            byte[] pdfBytes = planningService.generatePlanningPdf(niveau, parcours, enseignant);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=planning_examens.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(pdfBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError()
                    .body(("Erreur : " + e.getMessage()).getBytes());
        }
    }
}
