package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.controller.SchedulePDFController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.apache.pdfbox.io.RandomAccessBuffer;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import com.gti.windowcleaning.storage.StorageI;

import java.io.*;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class SchedulePDFControllerTest {
    StorageI storage;
    @Before
    public void setUp(){
        storage = Util.getTestDB();
    }
    @Test
    public void getSchedule() throws IOException {
        Model<Job> jobs = new Model<>(Job.class, storage);
        Model<Customer> customers = new Model<>(Customer.class, storage);
        Model<Order> orders = new Model<>(Order.class, storage);

        SchedulePDFController controller = new SchedulePDFController(customers, jobs, orders);

        Map<String,String> query = new HashMap<>();
        query.put("between", "{\"serviceDate\": [1544383319341, 1544803188591]}");
        query.put("filter", "{\"doneBy\": \"Jim\"}");

        Answer<ByteArrayOutputStream> answer = controller.process(new EmptyPayload(), Collections.emptyMap(),  query, false);

        PDFParser parser = new PDFParser(new RandomAccessBuffer(answer.getBody().toByteArray()));

        parser.parse();
        PDDocument doc = parser.getPDDocument();

        String text = "";
        try {
            text = new PDFTextStripper().getText(doc);
        } catch (IOException e) {
            e.printStackTrace();
        }
        assertEquals("application/pdf", answer.getHeaders().get("Content-Type"));
        assertTrue(text.contains("Totals"));
    }
}
