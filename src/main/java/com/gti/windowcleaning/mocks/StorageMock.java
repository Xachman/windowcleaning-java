/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.mocks;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.StorageI;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 *
 * @author xach
 */
public class StorageMock implements StorageI {

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        JSONParser parser = new JSONParser();
        try {
            String fileName = getClass().getResource("/mock/data/customers.json").getFile();
            FileReader r = new FileReader(fileName);
            Object obj = parser.parse(r);
            JSONArray jCustomers = (JSONArray) obj;
            
            Iterator<JSONObject> iter = jCustomers.iterator();

            while(iter.hasNext()) {
                JSONObject jobj = iter.next();
                Customer customer = new Customer();
                customer.setId(new Integer(jobj.get("id").toString()));
                customer.setName(jobj.get("name").toString());
                customer.setAddress(jobj.get("address").toString());
                customer.setPhone(jobj.get("phone").toString());
                customers.add(customer);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(StorageMock.class.getName()).log(Level.SEVERE, null, ex);
        }
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Customer> getCustomersByName(String name) {
        List<Customer> customers = new ArrayList<>();
        for(Customer customer: getCustomers()) {
            if(customer.getName().contains(name)) {
                customers.add(customer);
            }
        }
        return customers;
    }
    
}
