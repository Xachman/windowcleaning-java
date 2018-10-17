/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.Util;
import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.MustIncludeException;
import com.gti.windowcleaning.util.Json;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.NoSuchElementException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class CustomerModelTest {
    private URL dbPath;
    private StorageI storage;
    @Before
    public void before() {
        dbPath =  getClass().getResource("/mocks/test.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if(file_exists) {
            file.delete();
        }
        storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);
        storage.add(Customer.class,Util.assembleCustomers());
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
        URL dbPath =  getClass().getResource("/mocks/test_add_customer.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if(file_exists) {
            file.delete();
        }
        StorageI storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);
        CustomersModel customersModel = new CustomersModel(storage);
        List<Customer> customers = Util.assembleCustomers();
        
        customersModel.saveAll(customers);
        List<Customer> result = customersModel.getAll();
        
        Assert.assertEquals(customers.size(), result.size());
    }

    @Test(expected = NoSuchElementException.class)
    public void getCustomerById() throws MustIncludeException {
        CustomersModel customersModel = new CustomersModel(storage);
        Customer customer = customersModel.get(4);
       
        Assert.assertEquals("Corenda Barnham", customer.getName());
        Assert.assertEquals("Texarkana", customer.getLocation());
        Assert.assertEquals("TX", customer.getArea());


        customersModel.get(11);
    }

    @Test(expected = NoSuchElementException.class)
    public void removeById() throws MustIncludeException {
        CustomersModel customersModel = new CustomersModel(storage);
        customersModel.remove(3);
        Customer customer = customersModel.get(4);
         
        Assert.assertEquals("Corenda Barnham", customer.getName());
        Assert.assertEquals("Texarkana", customer.getLocation());
        Assert.assertEquals("TX", customer.getArea());


        customersModel.get(3);
    }

    @Test
    public void paginate() throws MustIncludeException {
        CustomersModel customersModel = new CustomersModel(storage);
        
        List<Customer> customers = customersModel.getRange(0, 5); 
        
        Assert.assertEquals(5, customers.size());
        Customer customer = customers.get(3);
       
        Assert.assertEquals("Corenda Barnham", customer.getName());
        Assert.assertEquals("Texarkana", customer.getLocation());
        Assert.assertEquals("TX", customer.getArea());

    }
}
