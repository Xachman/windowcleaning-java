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
import com.gti.windowcleaning.web.controller.customers.CustomerController;
import com.gti.windowcleaning.web.controller.customers.CustomersController;
import com.gti.windowcleaning.web.payload.CustomerPayload;
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
import junit.framework.Assert;
import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.simple.parser.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.skyscreamer.jsonassert.JSONAssert;

/**
 *
 * @author xach
 */
public class CustomerControllerTest {
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
        List<Customer> customers = Util.assembleCustomers();
        storage.add(Customer.class, customers);
    } 
    @Test
    public void getCustomer() throws ParseException, FileNotFoundException, IOException, JSONException, URISyntaxException {
        EmptyPayload ep = new EmptyPayload();
        CustomersModel model = new CustomersModel(storage);
        CustomerController controller = new CustomerController(model);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(":id", "1");
        Answer answer = controller.process(ep, urlParams, true);
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customer_expect.json").toURI()), "UTF-8");
        Assert.assertEquals(200, answer.getCode());
        JSONAssert.assertEquals(expect, answer.getBody(), false);

    }
}
