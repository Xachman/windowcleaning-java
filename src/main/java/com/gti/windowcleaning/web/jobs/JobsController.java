/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web.jobs;

import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.model.JobsModel;
import com.gti.windowcleaning.model.Model;
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
public class JobsController extends Controller<EmptyPayload> {
    private JobsModel model;
    
    public JobsController(JobsModel model) {
        super(EmptyPayload.class, model);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        if(query.get("range") != null) {
            JSONParser parser = new JSONParser();
            try {
                JSONArray range = (JSONArray) parser.parse(query.get("range"));
                long start = Long.parseLong(range.get(0).toString());
                long end = Long.parseLong(range.get(1).toString());
                List<Job> customers = model.getRange(start, end+1);
                String json = dataToJson(customers);
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Range", (start+1)+"-"+(end+1)+"/"+model.getTotalCount());
                return new Answer(200, json, headers);
            } catch (ParseException ex) {
                Logger.getLogger(getClass().getName()).log(Level.SEVERE, null, ex);
                return new Answer(400, "{\"error\": \"ParseException\"}");
            }
        }
        List<Job> jobs = model.getAll();
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Range", 1+"-"+jobs.size()+"/"+model.getTotalCount());
        return new Answer(200, dataToJson(jobs), headers);
    }
    
}
