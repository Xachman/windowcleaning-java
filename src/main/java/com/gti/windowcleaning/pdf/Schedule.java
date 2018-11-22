package com.gti.windowcleaning.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDTrueTypeFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class Schedule implements PDFI {
    Map<String,Object> data;
    PDDocument document;
    PDPageContentStream contentStream;
    String text;
    private void setText(PDDocument document) {
        try {
            text = new PDFTextStripper().getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public AutoCloseable getStream() {
        return null;
    }

    @Override
    public ByteArrayOutputStream process() {
        PDDocument document = new PDDocument();
        PDPage page = new PDPage();
        document.addPage(page);
        List<Map<String,String>> schedule = (List) data.get("schedule");
        try {
            contentStream = new PDPageContentStream(document, page);
            int count = 0;
            int boxStart = 50;
            PDRectangle rect = page.getMediaBox();
            int topMargin = 15;
            for(Map<String,String> item : schedule) {
                addText(item.get("doneBy"), page, document, 12, 10 , rect.getHeight() - 12 - topMargin - boxStart*count);
                addText(item.get("customer"), page, document, 12, 10, rect.getHeight() - 24 - topMargin - boxStart*count);
                addText(item.get("location"), page, document, 12, 150, rect.getHeight() - 24 - topMargin - boxStart*count);
                addText(item.get("notes"), page, document, 12, 10, rect.getHeight() - 36 - topMargin - boxStart*count);
                count++;
            }
            contentStream.saveGraphicsState();
            contentStream.close();
            PDDocumentCatalog cat = document.getDocumentCatalog();
            PDMetadata metadata = new PDMetadata(document);
            cat.setMetadata(metadata);
            setText(document);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            document.save(output);
            document.close();
            return output;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    @Override
    public String getText() {
        return text;
    }
    @Override
    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    private void addText(String text, PDPage page, PDDocument document, int fontSize, float x, float y ) {
        try {
            PDFont font = PDType1Font.HELVETICA;
            contentStream.beginText();
            contentStream.setFont(font, fontSize);
            contentStream.moveTextPositionByAmount(x, y);
            contentStream.drawString(text);
            contentStream.endText();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
