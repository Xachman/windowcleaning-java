/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.customer.Contact;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.MustIncludeException;
import com.gti.windowcleaning.util.Json;
import java.io.FileReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class CustomerModelTest {
    private String dbPath;
    @Before
    public void before() {
       dbPath =  getClass().getResource("/mocks/test.db").toString();
    }
    
    @Test(expected = MustIncludeException.class)
    public void validateAllFields() throws MustIncludeException {
        Customer customer = new Customer();
        CustomersModel model = new CustomersModel();
        try {
            model.save(customer);
        } catch(MustIncludeException e) {
            Assert.assertEquals("[name, location, area, contact, city, phone, state, addressLine1, addressLine2, zip, contactB, cityB, phoneB, stateB, addressLine1B, addressLine2B, zipB, type]",e.getMessage());
            throw e;
        }
        
    }

    @Test()
    public void addAndGetCustomers() throws MustIncludeException {
        SQLiteStorage storage = new SQLiteStorage(dbPath);
        
        CustomersModel customersModel = new CustomersModel(storage);
        List<Customer> customers = assembleCustomers();
        
        customersModel.saveAll(customers);
        List<Customer> result = customersModel.getAll();
        
        Assert.assertEquals(customers.size(), result.size());
    }

    private List<Customer> assembleCustomers() {
        Json jsonUtil = new Json();

        return jsonUtil.getCustomers(getClass().getResource("/mocks/data/customers.json").getFile());
    }
}
