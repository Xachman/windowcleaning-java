/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller.customers;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.util.List;
import java.util.Map;

/**
 *
 * @author xach
 */
public class CustomersController extends Controller<EmptyPayload> {

    private CustomersModel model;

    public CustomersController(CustomersModel model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        List<Customer> customers = model.getAll();

        String json = dataToJson(customers);
        return new Answer(200, json);
    }
    
}
