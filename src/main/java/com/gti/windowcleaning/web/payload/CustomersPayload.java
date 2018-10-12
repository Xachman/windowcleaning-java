/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.payload;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.web.ValidI;
import java.util.List;

/**
 *
 * @author xach
 */
public class CustomersPayload implements ValidI{
    private List<Customer> customers; 

    @Override
    public boolean isValid() {
        return customers.size() > 0;
    }
}
