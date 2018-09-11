/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.data;

import java.util.List;

/**
 *
 * @author xach
 */
public interface StorageI {
    public List<Customer> getCustomers();
    public Customer getCustomer(int id);

    public List<Customer> getCustomersByName(String name);
    
}
