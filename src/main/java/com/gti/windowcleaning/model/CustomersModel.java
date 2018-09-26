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
        return storage.get(Customer.class);
    } 
    public List<Customer> findByFieldName(String name) {
        return storage.getByField(Customer.class, name);
    }

    public void save(Customer customer) throws MustIncludeException {
        super.save(customer);
    }

}
