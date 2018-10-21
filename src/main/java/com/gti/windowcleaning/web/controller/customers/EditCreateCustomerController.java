/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller.customers;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.model.MustIncludeException;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.payload.CustomerPayload;
import com.gti.windowcleaning.web.payload.CustomersPayload;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xach
 */
public class EditCreateCustomerController extends Controller<Customer> {
    private CustomersModel model;

    public EditCreateCustomerController(CustomersModel model) {
        super(Customer.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(Customer value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        if(value.isValid()) {
            try {
                if(urlParams.get(":id") == null) {
                    Customer customer = model.save(value);
                    Map<String,String> result = new HashMap<>();
                    result.put("id", ""+customer.getId());
                    return new Answer(200, dataToJson(result));
                }
                Customer customer = model.update(value);
                Map<String,String> result = new HashMap<>();
                result.put("id", ""+customer.getId());
                return new Answer(200, dataToJson(result));

            } catch (MustIncludeException ex) {
                Logger.getLogger(EditCreateCustomerController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Answer(400, "{\"error\": \"An error occured when inserting customer\"}");
    }

    
    
}
