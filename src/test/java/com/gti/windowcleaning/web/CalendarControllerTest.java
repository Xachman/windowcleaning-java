/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.storage.QueryBuilder;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.web.controller.CalendarController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.*;

import static org.mockito.Mockito.*;


/**
 *
 * @author xach
 */
public class CalendarControllerTest {
    StorageI storage;
    List<Job> jobs;
    List<Customer> customers;
    List<Order> orders;

    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }

    @Test
    public void getSchedule() throws JSONException, URISyntaxException, IOException {
        Model<Job> jobModel = new Model<>(Job.class, storage);
        Model<Customer> customerModel = new Model(Customer.class, storage);
        Model<Order> orderModel = new Model<>(Order.class, storage);
        EmptyPayload payload = new EmptyPayload();


        CalendarController controller = new CalendarController(jobModel, customerModel, orderModel);
        Map<String, String> query = new HashMap<>();
        query.put("between", "{\"serviceDate\": [1542240000000, 1542240000000]}");

        Answer answer = controller.process(payload, Collections.emptyMap(), query, false);

    }
}
