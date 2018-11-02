package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.JobsModel;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.controller.EditCreateController;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class EditCreateControllerTest {

    StorageI storage;
    @Before
    public void setUp() {
        URL dbPath =  getClass().getResource("/mocks/test.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if(file_exists) {
            file.delete();
        }
        storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);
        storage.create(Job.class);
        List<Customer> customers = Util.assembleCustomers();
        List<Job> jobs = Util.assembleJobs(customers);
        storage.add(Customer.class, customers);
        storage.add(Job.class, jobs);
    }
    @Test
    public void createItem() {
        Job job = new Job();
        Customer customer = new Customer();
        customer.setId(5);
        job.setCustomer(customer);
        job.setServicedBy("Tim");
        job.setAmount(200.00);
        Date date = new Date();
        job.setServiceDate(date);
        job.setAutoPrintServiceStatement(true);
        job.setPrintInvoice(true);
        job.setPaymentExpected(true);
        job.setPrintServiceStatement(true);
        job.setDescription("Clean Windows");
        job.setNotes("Has Big Windows");
        JobsModel model = new JobsModel(storage);
        EditCreateController controller = new EditCreateController(model);

        Answer answer = controller.process(job, Collections.emptyMap(), Collections.emptyMap(), false);

        List<Job> jobs = model.getAll();
        assertEquals(200, answer.getCode());
        assertEquals(11, jobs.size());

        Job inJob = model.get(11);

        assertEquals(5, inJob.getCustomer().getId());
        assertEquals("Tim", inJob.getServicedBy());
        assertEquals(200.00, job.getAmount(), 0.0f);
        assertEquals(date, inJob.getServiceDate());
        assertEquals(true, inJob.isAutoPrintServiceStatement());
        assertEquals(true, inJob.isPaymentExpected());
        assertEquals(true, inJob.isPrintInvoice());
        assertEquals(true, inJob.isPrintServiceStatement());
        assertEquals("Clean Windows", inJob.getDescription());
        assertEquals("Has Big Windows", inJob.getNotes());
    }
}
