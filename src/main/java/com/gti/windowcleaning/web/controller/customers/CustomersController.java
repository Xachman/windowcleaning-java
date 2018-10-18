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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

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
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        JSONParser parser = new JSONParser();
        if(query.get("range") != null) {
            try {
                JSONArray range = (JSONArray) parser.parse(query.get("range"));
                long start = Long.parseLong(range.get(0).toString());
                long end = Long.parseLong(range.get(1).toString());
                List<Customer> customers = model.getRange(start, end+1);
                String json = dataToJson(customers);
                Map<String, String> lHeaders = new HashMap<>();
                lHeaders.put("Content-Range", (start+1)+"-"+(end+1)+"/"+model.getTotalCount());
                return new Answer(200, json, lHeaders);
            } catch (ParseException ex) {
                Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        List<Customer> customers = model.getAll();
        String json = dataToJson(customers);
        Map<String, String> lHeaders = new HashMap<>();
        lHeaders.put("Content-Range", 1+"-"+customers.size()+"/"+model.getTotalCount());
        return new Answer(200, json, lHeaders);
    }
    
}
