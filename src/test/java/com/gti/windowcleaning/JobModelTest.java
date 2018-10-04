/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.JobsModel;
import com.gti.windowcleaning.model.MustIncludeException;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class JobModelTest {
    private URL dbPath;
    private StorageI storage;

    @Before
    public void before() {
        dbPath =  getClass().getResource("/mocks/test.db");
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
    @Test()
    public void addJobTest() throws MustIncludeException {
        JobsModel jobsModel = new JobsModel(storage); 
        
        Job job = new Job();
        Customer customer = Util.assembleCustomers().get(3);
        job.setAmount(10);
        job.setCustomer(customer);
        job.setServicedBy("Tim");
        job.setServiceDate(new Date());
        jobsModel.save(job);
        Job eJob = jobsModel.get(49);

        Assert.assertEquals(10, eJob.getAmount());
    }    
}
