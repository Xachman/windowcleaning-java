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
            int boxStart = 75;
            PDRectangle rect = page.getMediaBox();
            int topMargin = 15;
            int line = 12;
            for(Map<String,String> item : schedule) {
                addText(item.get("doneBy"), page, document, 12, 10 , rect.getHeight() - line - topMargin - boxStart*count);
                addText(item.get("dateTime"), page, document, 12, 200, rect.getHeight() - line - topMargin - boxStart*count);
                addText("Customer", page, document, 12, 10, rect.getHeight() - line*2 - topMargin - boxStart*count);
                addText("Location", page, document, 12, 150, rect.getHeight() - line*2 - topMargin - boxStart*count);
                addText("Amount", page, document, 12, 350, rect.getHeight() - line*2 - topMargin - boxStart*count);
                addText("Invoice", page, document, 12, 250, rect.getHeight() - line*2 - topMargin - boxStart*count);
                addText("Payment", page, document, 12, 400, rect.getHeight() - line*2 - topMargin - boxStart*count);
                addText("Amnt. Coll.", page, document, 12, 475, rect.getHeight() - line*2 - topMargin - boxStart*count);
                drawLine(475,rect.getHeight() - line*4 - topMargin - boxStart*count, 540, rect.getHeight() - line*4 - topMargin - boxStart*count);
                addText("Code", page, document, 12, 550, rect.getHeight() - line*2 - topMargin - boxStart*count);
                drawLine(550,rect.getHeight() - line*4 - topMargin - boxStart*count, 600, rect.getHeight() - line*4 - topMargin - boxStart*count);
                addText(item.get("invoice"), page, document, 12, 250, rect.getHeight() - line*3 - topMargin - boxStart*count);
                addText(item.get("customer"), page, document, 12, 10, rect.getHeight() - line*3 - topMargin - boxStart*count);
                addText(item.get("location"), page, document, 12, 150, rect.getHeight() - line*3 - topMargin - boxStart*count);
                addText(item.get("amount"), page, document, 12, 350, rect.getHeight() - line*3 - topMargin - boxStart*count);
                addText(item.get("payment"), page, document, 12, 400, rect.getHeight() - line*3 - topMargin - boxStart*count);
                addText(item.get("notes"), page, document, 12, 10, rect.getHeight() - line*4 - topMargin - boxStart*count);
                addText("Totals", page, document, 12, 250, rect.getHeight() - line*5 - topMargin - boxStart*count);
                drawLine(350,rect.getHeight()+2 - line*4 - topMargin - boxStart*count, 450, rect.getHeight()+2 - line*4 - topMargin - boxStart*count);
                addText(item.get("totals"), page, document, 12, 350, rect.getHeight() - line*5 - topMargin - boxStart*count);
                addText(item.get("payment"), page, document, 12, 400, rect.getHeight() - line*5 - topMargin - boxStart*count);
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
            document.save("/home/xach/testpdfbox.pdf");
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

    private void drawLine(float startX, float startY, float endX, float endY) throws IOException {
        contentStream.drawLine(startX, startY, endX, endY);
    }

}
