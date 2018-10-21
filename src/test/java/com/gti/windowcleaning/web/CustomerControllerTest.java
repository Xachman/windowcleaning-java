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
import com.gti.windowcleaning.web.controller.customers.DeleteCustomerController;
import com.gti.windowcleaning.web.controller.customers.EditCreateCustomerController;
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
        Answer answer = controller.process(ep, urlParams, Collections.emptyMap(), false);
        String expect = FileUtils.readFileToString(new File(getClass().getResource("/mocks/data/web/customer_expect.json").toURI()), "UTF-8");
        Assert.assertEquals(200, answer.getCode());
        JSONAssert.assertEquals(expect, answer.getBody(), false);

    }

    @Test
    public void deleteCustomer() throws JSONException {
        EmptyPayload ep = new EmptyPayload();
        CustomersModel model = new CustomersModel(storage);
        DeleteCustomerController controller = new DeleteCustomerController(model);
        Map<String, String> urlParams = new HashMap<>();
        urlParams.put(":id", "3");

        Answer answer = controller.process(ep, urlParams, Collections.emptyMap(), false);

        Assert.assertEquals(200, answer.getCode());
        JSONAssert.assertEquals("{\"success\": \"Customer 3 was deleted\"}", answer.getBody(), false);

        answer = controller.process(ep, urlParams, Collections.emptyMap(), false);

        Assert.assertEquals(400, answer.getCode());
        JSONAssert.assertEquals("{\"error\": \"Customer not found\"}", answer.getBody(), false);
    }

    @Test 
    public void createCustomer() {
        Customer cp = new Customer();
        cp.setName("Ted Denson");
        cp.setLocation("Wilmington");
        cp.setArea("Back Street");
        cp.setAWWPUR(true);
        cp.setAWWLine(true);
        cp.setActive(true);
        cp.setContact("Barbra Denson");
        cp.setAddressLine1("123 Elm St");
        cp.setCity("Salisbury");
        cp.setZip("12345");
        cp.setState("MD");
        cp.setPhone("555-555-5555");
        cp.setContactB("Barbra Denson");
        cp.setAddressLine1B("123 Elm St");
        cp.setCityB("Salisbury");
        cp.setZipB("12345");
        cp.setStateB("MD");
        cp.setPhoneB("555-555-5555");
        cp.setBillCharge(100.00);
        cp.setBillNotes("Some notes about the charge");
        cp.setCredit(0.0);
        cp.setDivision("Residence");
        cp.setEOM(false);
        cp.setMonthly(true);
        cp.setType("S");
        CustomersModel model = new CustomersModel(storage);
        EditCreateCustomerController controller = new EditCreateCustomerController(model);

        Answer answer = controller.process(cp, Collections.emptyMap(), Collections.emptyMap(), false);

        List<Customer> customers = model.getAll();
        Assert.assertEquals(200, answer.getCode());
        Assert.assertEquals(11, customers.size());
    }
    @Test
    public void editCustomer() {
        Customer cp = new Customer();
        cp.setId(3);
        cp.setName("Ted Denson");
        cp.setLocation("Wilmington");
        cp.setArea("Back Street");
        cp.setAWWPUR(true);
        cp.setAWWLine(true);
        cp.setActive(true);
        cp.setContact("Barbra Denson");
        cp.setAddressLine1("123 Elm St");
        cp.setCity("Salisbury");
        cp.setZip("12345");
        cp.setState("MD");
        cp.setPhone("555-555-5555");
        cp.setContactB("Barbra Denson");
        cp.setAddressLine1B("123 Elm St");
        cp.setCityB("Salisbury");
        cp.setZipB("12345");
        cp.setStateB("MD");
        cp.setPhoneB("555-555-5555");
        cp.setBillCharge(100.00);
        cp.setBillNotes("Some notes about the charge");
        cp.setCredit(0.0);
        cp.setDivision("Residence");
        cp.setEOM(false);
        cp.setMonthly(true);
        cp.setType("S");
        CustomersModel model = new CustomersModel(storage);
        EditCreateCustomerController controller = new EditCreateCustomerController(model);
        
        Map<String,String> urlParams = new HashMap<>();
        
        urlParams.put("id", "3");
        Answer answer = controller.process(cp, urlParams, Collections.emptyMap(), false);

        List<Customer> customers = model.getAll();
        Assert.assertEquals(200, answer.getCode());
        Assert.assertEquals(10, customers.size());

        Customer customer = model.get(3);
        
        Assert.assertEquals("Ted Denson", customer.getName());
        Assert.assertEquals("Wilmington", customer.getLocation());


    }
}
