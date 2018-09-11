/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.Customer;
import java.util.List;

/**
 *
 * @author xach
 */
public class CustomersModel extends Model {

    public List<Customer> getCustomers() {
        System.out.println(storage.getCustomers());
        return storage.getCustomers();
    } 
    public List<Customer> findByName(String name) {
        return storage.getCustomersByName(name);
    }
}
