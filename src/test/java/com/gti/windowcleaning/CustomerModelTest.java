/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.MustIncludeException;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class CustomerModelTest {
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
}
