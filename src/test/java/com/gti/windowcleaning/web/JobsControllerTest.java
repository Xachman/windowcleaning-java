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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import static org.skyscreamer.jsonassert.JSONCompareMode.STRICT;
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
        
        String actual = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("1-10/10", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual, answer.getBody(), STRICT);
       
        Map<String,String> query = new HashMap<>();
        query.put("range", "2-5");
        Answer answer2 = controller.process(payload, Collections.emptyMap(), query, false);
        
        String actual2 = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/jobs_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("3-6/10", answer2.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(actual, answer2.getBody(), STRICT);
    }
}
