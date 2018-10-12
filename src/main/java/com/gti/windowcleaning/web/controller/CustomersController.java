/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import java.util.Map;

/**
 *
 * @author xach
 */
public class CustomersController extends Controller<EmptyPayload> {

    private CustomersModel model;

    public CustomersController(CustomersModel model) {
        super(EmptyPayload.class, model);
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
        return new Answer(200, "{}");
    }
    
}
