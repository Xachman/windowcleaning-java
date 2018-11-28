package com.gti.windowcleaning.pdf;

import org.apache.pdfbox.pdmodel.common.PDRectangle;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Schedule extends PDFAbstract {
    Map<String,Object> data;
    String text;
    final private int MARGIN = 15;

    @Override
    public AutoCloseable getStream() {
        return null;
    }


    @Override
    void addContent() throws IOException {
        int count = 1;
        int boxStart = 75;
        PDRectangle rect = page.getMediaBox();
        int line = 1;
        int lineNext = 0;
        List<Map<String, String>> schedule = (List) data.get("schedule");
        for(Map<String,String> item : schedule) {
            int curLine = line;
            addText(item.get("doneBy"), 10 ,curLine);
            addText(item.get("dateTime"), 200,curLine);
            addText("Customer", 10,curLine+1);
            addText("Location", 150,curLine+1);
            addText("Amount", 350,curLine+1);
            addText("Invoice", 250,curLine+1);
            addText("Payment", 400,curLine+1);
            addText("Amnt. Coll.", 475,curLine+1);
            drawLine(475, 540, curLine+3);
            addText("Code", 550,curLine+1);
            drawLine(550, 600, curLine+3);
            addText(item.get("invoice"), 250,curLine+2);
            addText(item.get("customer"), 10,curLine+2);
            addText(item.get("location"), 150,curLine+2);
            addText(item.get("amount"), 350,curLine+2);
            addText(item.get("payment"), 400,curLine+2);
            addText("Totals", 250,curLine+4);
            drawLine(350, 450, curLine+3);
            addText(item.get("totals"), 350,curLine+4);
            addText(item.get("payment"), 400,curLine+4);
            addText(item.get("notes"), 10,curLine+5);
            line = line + 7;
            count++;
        }
    }

    @Override
    public void setData(Map<String, Object> data) {
        this.data = data;
    }



}
