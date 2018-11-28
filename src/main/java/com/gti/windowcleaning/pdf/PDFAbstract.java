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
    protected int TOP_MARGIN = 12;
    protected int FONT_SIZE = 12;
    protected int LINE_SIZE = 12;
    private PDRectangle rect;

    protected void addText(String text, float start, float line , float fontSize) {
        float y = rect.getHeight() - line*LINE_SIZE - TOP_MARGIN;
        float x = start;
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

    protected void addText(String text, float start, float line) {
        addText(text, start, line, FONT_SIZE);
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
}
