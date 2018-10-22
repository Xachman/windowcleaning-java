/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.JobsModel;
import com.gti.windowcleaning.web.jobs.JobsController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

/**
 *
 * @author xach
 */
public class JobsControllerTest {
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
    public void listJobs() throws JSONException, URISyntaxException, IOException {
        JobsModel model = new JobsModel(storage);
        EmptyPayload payload = new EmptyPayload();
        
        JobsController controller = new JobsController(model);
        Answer answer = controller.process(payload, Collections.emptyMap(), Collections.emptyMap(), false);
        
        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customer_expect.json").toURI()), "UTF-8");
        JSONAssert.assertEquals(actual, answer.getBody(), false);
        
    }
}
