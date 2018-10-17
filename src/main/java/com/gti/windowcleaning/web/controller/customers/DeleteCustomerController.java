/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller.customers;

import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author xach
 */
public class DeleteCustomerController extends Controller<EmptyPayload> {

    private CustomersModel model;

    public DeleteCustomerController(CustomersModel model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }
    
    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        Map<String,String> message = new HashMap<>();
        if(urlParams.get(":id") != null) {
            Integer id = new Integer(urlParams.get(":id"));
            if(model.remove(id)) {
                message.put("success", "Customer "+id+" was deleted");
                return new Answer(200, dataToJson(message));
            }
            message.put("error", "Customer not found");
            return new Answer(400, dataToJson(message));
        }
        message.put("error", "No :id url parameter");
        return new Answer(400, dataToJson(message));
    }
    
}
