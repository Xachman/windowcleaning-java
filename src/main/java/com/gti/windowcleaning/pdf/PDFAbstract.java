package com.gti.windowcleaning.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDDocumentCatalog;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDMetadata;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.text.PDFTextStripper;
import org.eclipse.jetty.util.IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

abstract class PDFAbstract implements PDFI {
    protected PDPageContentStream contentStream;
    protected PDDocument document;
    protected PDPage page;
    protected String text;
    protected int TOP_MARGIN = 15;
    protected int FONT_SIZE = 12;
    protected int LINE_SIZE = 12;
    private PDRectangle rect;

    protected int addText(String text, float start, float lineN , float fontSize) {
        float x = start;
        int linesAdded = 0;
        String lines[] = text.split("\\r?\\n");
        for(int i = 0; i < lines.length; i++) {
            String line = lines[i];
            float y = rect.getHeight() - (linesAdded+lineN)*LINE_SIZE - TOP_MARGIN;
            try {
                PDFont font = PDType1Font.HELVETICA;
                contentStream.beginText();
                contentStream.setFont(font, fontSize);
                contentStream.moveTextPositionByAmount(x, y);
                contentStream.drawString(line);
                contentStream.endText();
            } catch (IOException e) {
                e.printStackTrace();
            }
            linesAdded++;
        }
        return linesAdded;
    }

    protected int addText(String text, float start, float line) {
        return addText(text, start, line, FONT_SIZE);
    }

    @Override
    public ByteArrayOutputStream process() {
        document = new PDDocument();
        page = new PDPage();
        document.addPage(page);
        rect = page.getMediaBox();
        try {
            contentStream = new PDPageContentStream(document, page);
            addContent();
            contentStream.saveGraphicsState();
            contentStream.close();
            PDDocumentCatalog cat = document.getDocumentCatalog();
            PDMetadata metadata = new PDMetadata(document);
            cat.setMetadata(metadata);
            setText(document);
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            document.save(System.getProperty("user.home")+"/testpdf.pdf");
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
    private void setText(PDDocument document) {
        try {
            text = new PDFTextStripper().getText(document);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    protected void drawLine(float startX, float endX, float line) throws IOException {
        float y = rect.getHeight() - line*LINE_SIZE - TOP_MARGIN;
        contentStream.drawLine(startX, y, endX, y);
    }
    abstract void addContent() throws IOException;

    private int[] possibleWrapPoints(String text) {
        String[] split = text.split("(?<=\\W)");
        int[] ret = new int[split.length];
        ret[0] = split[0].length();
        for ( int i = 1 ; i < split.length ; i++ )
            ret[i] = ret[i-1] + split[i].length();
        return ret;
    }
}
