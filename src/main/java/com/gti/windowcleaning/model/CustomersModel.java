/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.storage.StorageI;
import java.util.List;

/**
 *
 * @author xach
 * @param <Customer>
 */
public class CustomersModel extends Model<Customer> {

    public CustomersModel() {
        super(Customer.class);
    }

    public CustomersModel(StorageI storage) {
        super(storage, Customer.class);
    }
    
    public List<Customer> getCustomers() {
        return storage.get(Customer.class);
    } 
    public List<Customer> findByFieldName(String name) {
        return storage.getByField(Customer.class, name);
    }


    public void saveAll(List<Customer> customers) throws MustIncludeException {
        for(Customer customer: customers) {
            save(customer);
        } 
    }

    public List<Customer> getAll() {
        return storage.get(Customer.class);
    }

}
