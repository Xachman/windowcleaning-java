/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.model;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.StorageI;
import java.util.List;

/**
 *
 * @author xach
 */
public class CustomersModel extends Model {

    public CustomersModel() {
        super();
    }

    public CustomersModel(StorageI storage) {
        super(storage);
    }
    
    public List<Customer> getCustomers() {
        return storage.get(Customer.class);
    } 
    public List<Customer> findByFieldName(String name) {
        return storage.getByField(Customer.class, name);
    }

    public void save(Customer customer) throws MustIncludeException {
        super.save(customer);
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
