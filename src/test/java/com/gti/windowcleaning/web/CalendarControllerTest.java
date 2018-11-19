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
import static junit.framework.Assert.*;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;

import java.io.File;
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

        // 1544383319341 - 1544803188591
        assertEquals(200, answer.getCode());
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/calendar_expect.json").toURI()), "UTF-8");
        JSONAssert.assertEquals(expect, answer.getBody(), JSONCompareMode.STRICT);
    }
}
