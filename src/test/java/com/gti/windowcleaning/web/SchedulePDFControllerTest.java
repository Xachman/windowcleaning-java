package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.controller.SchedulePDFController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.junit.Before;
import org.junit.Test;
import com.gti.windowcleaning.storage.StorageI;

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
    public void getSchedule() {
        Model<Job> jobs = new Model<>(Job.class, storage);
        Model<Customer> customers = new Model<>(Customer.class, storage);
        Model<Order> orders = new Model<>(Order.class, storage);

        SchedulePDFController controller = new SchedulePDFController(customers, jobs, orders);

        Map<String,String> query = new HashMap<>();
        query.put("between", "{\"serviceDate\": [1544383319341, 1544803188591]}");
        query.put("filter", "{\"doneBy\": Tim}");

        Answer answer = controller.process(new EmptyPayload(), Collections.emptyMap(),  query, false);

        answer.getBody();
    }
}
