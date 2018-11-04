/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.Util;
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
    public void setUp()  {
        storage = Util.getTestDB();
    }

    @Test()
    public void addJobTest() throws MustIncludeException {
        JobsModel jobsModel = new JobsModel(storage); 
        
        Job job = new Job();
        Customer customer = Util.assembleCustomers().get(3);
        job.setAmount(10.00);
        job.setCustomer(customer);
        job.setServicedBy("Tim");
        job.setServiceDate(new Date());
        jobsModel.save(job);
        Job eJob = jobsModel.get(11);

        Assert.assertEquals(10.00, eJob.getAmount(), 0);
        Assert.assertEquals("Tim", eJob.getServicedBy());
    }    

    @Test(expected = MustIncludeException.class)
    public void mustIncludeTest() throws MustIncludeException {
        JobsModel jobsModel = new JobsModel(storage); 

        Job job = new Job();

        try {
            jobsModel.save(job);
        } catch(MustIncludeException e) {
            System.out.println(e.getMessage());
            Assert.assertEquals("[customer, servicedBy, serviceDate]",e.getMessage());
            throw e;
        }


    }
}
