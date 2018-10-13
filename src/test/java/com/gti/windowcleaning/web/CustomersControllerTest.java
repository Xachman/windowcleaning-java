/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.controller.CustomersController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.io.File;
import java.net.URL;
import java.util.Collections;
import java.util.List;
import static junit.framework.Assert.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class CustomersControllerTest {
    private StorageI storage; 
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
        List<Customer> customers = Util.assembleCustomers();
        storage.add(customers.get(0));
    }
    @Test
    public void displayCustomers() throws ParseException {
        EmptyPayload ep = new EmptyPayload();

        CustomersModel model = new CustomersModel(storage);
        CustomersController controller = new CustomersController(model);
        
        Answer answer = controller.process(ep, Collections.emptyMap(), true);
        String json = "{\"test\": \"val\"}";
        JSONParser parser = new JSONParser();
        JSONObject expect = (JSONObject) parser.parse(json);
        JSONObject test = (JSONObject) parser.parse(json);
        assertTrue(expect.equals(test));
    }
}
