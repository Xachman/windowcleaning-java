/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.storage.SQLiteStorage;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.controller.customers.CustomersController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 *
 * @author xach
 */
public class CustomersControllerTest {

    private StorageI storage;

    @Before
    public void setUp()  {
        storage = Util.getTestDB();
    }

    @Test
    public void displayCustomers() throws ParseException, FileNotFoundException, IOException, JSONException, URISyntaxException {
        URL dbPath = getClass().getResource("/mocks/test.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if (file_exists) {
            file.delete();
        }
        StorageI storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);
        List<Customer> customers = Util.assembleCustomers();
        storage.add(customers.get(0));
        EmptyPayload ep = new EmptyPayload();

        CustomersModel model = new CustomersModel(storage);
        CustomersController controller = new CustomersController(model);

        Answer answer = controller.process(ep, Collections.emptyMap(), Collections.emptyMap(), false);
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customers_expect.json").toURI()), "UTF-8");

        Assert.assertEquals("1-1/1", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(expect, answer.getBody(), false);

    }
    
    @Test
    public void rangeOfCustomers() throws ParseException, FileNotFoundException, IOException, JSONException, URISyntaxException {
        EmptyPayload ep = new EmptyPayload();
        CustomersModel model = new CustomersModel(storage);
        CustomersController controller = new CustomersController(model);
        Map<String,String> query = new HashMap<>();
        query.put("range", "[0,1]");

        Answer answer = controller.process(ep, Collections.emptyMap(), query, false);
        
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customers_range_expect.json").toURI()), "UTF-8");
        Assert.assertEquals("1-2/10", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(expect, answer.getBody(), false);

        query.put("range", "[3,6]");

        answer = controller.process(ep, Collections.emptyMap(), query, false);
        
        expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customers_range_expect2.json").toURI()), "UTF-8");
        Assert.assertEquals("4-7/10", answer.getHeaders().get("Content-Range"));
        JSONAssert.assertEquals(expect, answer.getBody(), false);
    }
}
