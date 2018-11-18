package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.controller.EditCreateController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class EditCreateControllerTest {

    StorageI storage;
    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }
    @Test
    public void createItem() {
        Job job = new Job();
        Customer customer = new Customer();
        customer.setId(5);
        job.setCustomer(customer);
        job.setAmount(200.00);
        Date date = new Date();
        job.setAutoPrintServiceStatement(true);
        job.setPrintInvoice(true);
        job.setPaymentExpected(true);
        job.setPrintServiceStatement(true);
        job.setDescription("Clean Windows");
        job.setNotes("Has Big Windows");
        Model<Job> model = new Model(Job.class, storage);
        EditCreateController controller = new EditCreateController(model);

        Answer answer = controller.process(job, Collections.emptyMap(), Collections.emptyMap(), false);

        List<Job> jobs = model.getAll();
        assertEquals(200, answer.getCode());
        assertEquals(11, jobs.size());

        Job inJob = model.get(11);

        assertEquals(5, inJob.getCustomer().getId());
        assertEquals(200.00, job.getAmount(), 0.0f);
        assertEquals(true, inJob.isAutoPrintServiceStatement());
        assertEquals(true, inJob.isPaymentExpected());
        assertEquals(true, inJob.isPrintInvoice());
        assertEquals(true, inJob.isPrintServiceStatement());
        assertEquals("Clean Windows", inJob.getDescription());
        assertEquals("Has Big Windows", inJob.getNotes());
    }
    @Test
    public void updateItem() {
        Job job = new Job();
        Customer customer = new Customer();
        customer.setId(5);
        job.setId(4);
        job.setCustomer(customer);
        job.setAmount(200.00);
        Date date = new Date();
        job.setAutoPrintServiceStatement(true);
        job.setPrintInvoice(true);
        job.setPaymentExpected(true);
        job.setPrintServiceStatement(true);
        job.setDescription("Clean Windows");
        job.setNotes("Has Big Windows");
        Model<Job> model = new Model(Job.class, storage);
        EditCreateController controller = new EditCreateController(model);
        Map<String,String> urlParams = new HashMap<>();
        urlParams.put(":id", "4");
        Answer answer = controller.process(job, urlParams, Collections.emptyMap(), false);

        List<Job> jobs = model.getAll();
        assertEquals(200, answer.getCode());
        assertEquals(10, jobs.size());

        Job inJob = model.get(4);

        assertEquals(5, inJob.getCustomer().getId());
        assertEquals(200.00, job.getAmount(), 0.0f);
        assertEquals(true, inJob.isAutoPrintServiceStatement());
        assertEquals(true, inJob.isPaymentExpected());
        assertEquals(true, inJob.isPrintInvoice());
        assertEquals(true, inJob.isPrintServiceStatement());
        assertEquals("Clean Windows", inJob.getDescription());
        assertEquals("Has Big Windows", inJob.getNotes());
    }
}
