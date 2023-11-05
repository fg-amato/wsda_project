package it.finalproject_lastversion.utils;

import it.finalproject_lastversion.model.Transaction;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

import java.io.IOException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ReportGeneratorUtil {

    public static PDDocument generaReport(List<Transaction> list) {
        try {
            PDDocument document = new PDDocument();
            PDPage page = new PDPage(PDRectangle.A4);
            document.addPage(page);
            PDPageContentStream contentStream = new PDPageContentStream(document, page, PDPageContentStream.AppendMode.APPEND, false);

            //oggetti istanziati per convertire le stringhe
            SimpleDateFormat parseFormat = new SimpleDateFormat("yyyy MMM dd");
            DecimalFormat df = new DecimalFormat("0.00");

            //margine dalla pagina
            float margin = 50;

            //inizio della tabella
            float yStart = page.getMediaBox().getHeight() - margin;
            float tableWidth = page.getMediaBox().getWidth() - 2 * margin;
            float yPosition = yStart;

            //definizione delle larghezze delle colonne
            float columnWidth = tableWidth / 4f;

            //creazione della tabella con intestazioni
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
            drawTableHeader(contentStream, yPosition, margin, tableWidth, columnWidth);
            //l'header si è discostato di 50 per come è stato scritto il metodo, aggiorno yPosition
            yPosition -= 50;

            //setto il font
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA), 12);
            //uso un contatore per l'offset nelle righe della tabella
            int i = 1;
            for (Transaction item : list) {
                //manipolo fuori la data e l'importo e passo tutti i parametri come stringa
                String dataTransazioneFormattata = parseFormat.format(item.getDateTransaction());
                //converto in numero con due cifre decimali
                String importoConvertito = df.format(item.getAmount());
                //inserimento delle righe della tabella
                drawTableRow(contentStream, yPosition, margin, columnWidth, dataTransazioneFormattata, importoConvertito, item.getCard().getCardNumber(), item.getMerchant().getUsername());
                //aggiorno yPosition sottraendo 50 perché ogni riga dovrebbe occupare 50 di spazio
                yPosition -= 50;
            }

            contentStream.close();
            return document;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static void drawTableHeader(PDPageContentStream contentStream, float yPosition, float margin, float tableWidth, float columnWidth)
            throws IOException {
        drawLine(margin, yPosition, margin + tableWidth, yPosition, contentStream);
        yPosition -= 25;

        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.HELVETICA_BOLD), 12);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText("Data Transazione");
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + columnWidth, yPosition);
        contentStream.showText("Importo (€)");
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + 2 * columnWidth, yPosition);
        contentStream.showText("Numero Carta");
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + 3 * columnWidth, yPosition);
        contentStream.showText("Negoziante");
        contentStream.endText();

        yPosition -= 25;
        drawLine(margin, yPosition, margin + tableWidth, yPosition, contentStream);
    }

    private static void drawTableRow(PDPageContentStream contentStream, float yPosition, float margin, float columnWidth,
                                     String dataTransazione, String importo, String numeroCarta, String negoziante) throws IOException {
        contentStream.setLineWidth(1f);
        //traccio una linea per separarlo dall'elemento precedente
        drawLine(margin, yPosition, margin + 4 * columnWidth, yPosition, contentStream);
        yPosition -= 25;


        contentStream.beginText();
        contentStream.newLineAtOffset(margin, yPosition);
        contentStream.showText(dataTransazione);
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + columnWidth, yPosition);
        contentStream.showText(importo);
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + 2 * columnWidth, yPosition);
        contentStream.showText(numeroCarta);
        contentStream.endText();
        contentStream.beginText();
        contentStream.newLineAtOffset(margin + 3 * columnWidth, yPosition);
        contentStream.showText(negoziante);
        contentStream.endText();
    }

    private static void drawLine(float xStart, float yStart, float xEnd, float yEnd, PDPageContentStream contentStream) {
        try {
            contentStream.moveTo(xStart, yStart);
            contentStream.lineTo(xEnd, yEnd);
            contentStream.stroke();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
