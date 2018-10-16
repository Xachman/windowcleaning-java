/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.payload.CustomerPayload;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xach
 */
public class CustomerController extends Controller<CustomerPayload> {

    private CustomersModel model;
    
    public CustomerController(CustomersModel model) {
        super(CustomerPayload.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(CustomerPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        if(value.isValid()) {
            Customer customer = model.get(value.getCustomer().getId());
            return new Answer(200, dataToJson(customer));
        }
        Map<String,String> error = new HashMap<>();
        error.put("error", "Customer not valid");
        return new Answer(400, dataToJson(error));
    }
    
}
