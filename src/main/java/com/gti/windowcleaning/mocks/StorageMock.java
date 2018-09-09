/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.mocks;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.StorageI;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author xach
 */
public class StorageMock implements StorageI {

    @Override
    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        Customer customer1 = new Customer();

        customer1.setName("Tim Jones");
        customer1.setAddress("123 elm St");
        customer1.setPhone("555-555-5551");

        customers.add(customer1);
        return customers;
    }

    @Override
    public Customer getCustomer(int id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
