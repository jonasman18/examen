package com.example.examen.service;

import com.example.examen.model.Examen;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

import static java.awt.Font.PLAIN;

@Service
public class PlanningService {

    private final ExamenService examenService;

    public PlanningService(ExamenService examenService) {
        this.examenService = examenService;
    }

    public byte[] generatePlanningPdf() throws Exception {
        List<Examen> examens = examenService.getAllExamens();
        if (examens.isEmpty()) throw new RuntimeException("Aucun examen trouvé");

        // Trier par date puis heure
        examens.sort(Comparator.comparing(Examen::getDateExamen)
                .thenComparing(Examen::getHeureDebut));

        // Grouper par date
        Map<String, List<Examen>> examensParDate = examens.stream()
                .collect(Collectors.groupingBy(
                        e -> e.getDateExamen().format(DateTimeFormatter.ofPattern("EEEE dd/MM/yyyy")),
                        LinkedHashMap::new, Collectors.toList()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Document document = new Document(PageSize.A4.rotate(), 25, 25, 35, 25);
        PdfWriter.getInstance(document, out);
        document.open();

        // 🎓 Titre principal
        Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD, Color.decode("#004d80"));
        Paragraph title = new Paragraph("PLANNING DES EXAMENS", titleFont);
        title.setAlignment(Element.ALIGN_CENTER);
        title.setSpacingAfter(20f);
        document.add(title);

        DateTimeFormatter hourFmt = DateTimeFormatter.ofPattern("HH:mm");

        // 🔹 Génération du tableau principal
        for (Map.Entry<String, List<Examen>> entry : examensParDate.entrySet()) {
            String date = entry.getKey();
            List<Examen> examensDuJour = entry.getValue();

            // Table : 1 colonne pour la date + 1 par examen
            PdfPTable table = new PdfPTable(examensDuJour.size() + 1);
            table.setWidthPercentage(100);
            float[] widths = new float[examensDuJour.size() + 1];
            widths[0] = 2.5f; // colonne date
            Arrays.fill(widths, 1, widths.length, 4.5f);
            table.setWidths(widths);

            // 📅 Cellule date
            PdfPCell dateCell = new PdfPCell(new Phrase(date.toUpperCase(),
                    new Font(Font.HELVETICA, 11, Font.BOLD, Color.WHITE)));
            dateCell.setBackgroundColor(new Color(0, 102, 204));
            dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            dateCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            dateCell.setPadding(10f);
            table.addCell(dateCell);

            // 🧾 Colonnes des examens
            for (Examen exam : examensDuJour) {
                PdfPCell cell = new PdfPCell();
                cell.setPadding(8f);
                cell.setVerticalAlignment(Element.ALIGN_TOP);
                cell.setBackgroundColor(new Color(240, 248, 255)); // Bleu clair

                // 🕒 Heure
                String heure = (exam.getHeureDebut() != null && exam.getHeureFin() != null)
                        ? exam.getHeureDebut().format(hourFmt) + " - " + exam.getHeureFin().format(hourFmt)
                        : "";
                Paragraph heureP = new Paragraph("🕒 " + heure,
                        new Font(Font.HELVETICA, 10, Font.BOLD, Color.decode("#004d80")));
                heureP.setAlignment(Element.ALIGN_CENTER);
                heureP.setSpacingAfter(4f);
                cell.addElement(heureP);

                // 📘 Matière
                String matiere = exam.getMatiere() != null ? exam.getMatiere().getNomMatiere() : "";
                Paragraph matiereP = new Paragraph(matiere.toUpperCase(),
                        new Font(Font.HELVETICA, 11, Font.BOLD, Color.BLACK));
                matiereP.setAlignment(Element.ALIGN_CENTER);
                cell.addElement(matiereP);

                // 🎓 Niveau + parcours + durée
                String niveau = exam.getNiveau() != null ? exam.getNiveau().getCodeNiveau() : "";
                String parcours = "";
                if (exam.getExamenParcours() != null && !exam.getExamenParcours().isEmpty()) {
                    parcours = exam.getExamenParcours().stream()
                            .map(ep -> ep.getParcours().getCodeParcours())
                            .reduce((a, b) -> a + " / " + b).orElse("");
                }

                String duree = "";
                if (exam.getDuree() != null)
                    duree = String.format("(%dh%02d)", exam.getDuree().intValue(),
                            (int) ((exam.getDuree().doubleValue() * 60) % 60));

                Paragraph infoP = new Paragraph(
                        niveau + "  " + parcours + "  " + duree,
                        new Font(Font.HELVETICA, 9, PLAIN, Color.DARK_GRAY));
                infoP.setAlignment(Element.ALIGN_CENTER);
                infoP.setSpacingAfter(3f);
                cell.addElement(infoP);

                // 🏫 Salle
                String salle = exam.getNumeroSalle() != null ? "Salle : " + exam.getNumeroSalle() : "";
                Paragraph salleP = new Paragraph(salle,
                        new Font(Font.HELVETICA, 9, PLAIN, Color.BLACK));
                salleP.setAlignment(Element.ALIGN_CENTER);
                salleP.setSpacingAfter(3f);
                cell.addElement(salleP);

                // 👨‍🏫 Enseignant
                String enseignant = "";
                if (exam.getMatiere() != null && exam.getMatiere().getEnseignerList() != null
                        && !exam.getMatiere().getEnseignerList().isEmpty()) {
                    enseignant = exam.getMatiere().getEnseignerList().get(0).getEnseignant().getNomEnseignant();
                }
                Paragraph enseignantP = new Paragraph("👨‍🏫 " + enseignant,
                        new Font(Font.HELVETICA, 9, Font.ITALIC, Color.GRAY));
                enseignantP.setAlignment(Element.ALIGN_CENTER);
                cell.addElement(enseignantP);

                table.addCell(cell);
            }

            table.setSpacingAfter(10f);
            document.add(table);
        }

        document.close();
        return out.toByteArray();
    }
}
