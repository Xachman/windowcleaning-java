/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.data.BaseData;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.model.MustIncludeException;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author xach
 */
public class EditCreateController extends Controller<BaseData> {
    private Model model;

    public EditCreateController(Model model) {
        super(model.getClazz(), model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(BaseData value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        if(value.isValid()) {
            try {
                if(urlParams.get(":id") == null) {
                    Object result = model.save(value);
                    return new Answer(200, dataToJson(result));
                }
                System.out.println(value);
                Object result = model.update(value);
                return new Answer(200, dataToJson(result));

            } catch (MustIncludeException ex) {
                Logger.getLogger(EditCreateController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return new Answer(400, "{\"error\": \"An error occured when inserting item\"}");
    }

    
    
}
