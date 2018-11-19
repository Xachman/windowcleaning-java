package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.storage.QueryBuilder;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalendarController extends Controller<EmptyPayload> {
    Model<Job> jobsModel;
    Model<Customer> customersModel;
    Model<Order> ordersModel;

    public CalendarController(Model<Job> jobsModel, Model<Customer> customersModel,Model<Order> ordersModel) {
        super(EmptyPayload.class);

        this.jobsModel = jobsModel;
        this.customersModel = customersModel;
        this.ordersModel = ordersModel;

    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        JSONParser parser = new JSONParser();
        Map<String, String> headers = new HashMap<>();
        try {
            QueryBuilder qb = new QueryBuilder();
            if(query.get("between") != null) {
                JSONObject between = (JSONObject) parser.parse(query.get("between"));
                String field = between.keySet().iterator().next().toString();
                JSONArray values = (JSONArray) between.values().iterator().next();
                Object value1 = ordersModel.processField(field, values.get(0));
                Object value2 = ordersModel.processField(field,values.get(1));
                qb.addBetween(field, value1, value2);
            }

            List<Order> orders = ordersModel.execute(qb);

            for(Order order: orders) {
                order.setJob(jobsModel.get(order.getJob().getId()));
                order.getJob().setCustomer(customersModel.get(order.getJob().getCustomer().getId()));
            }
            headers.put("Content-Range", "1-"+orders.size()+"/"+orders.size());
            return new Answer(200, dataToJson(orders), headers);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Answer(400, "Error");
    }
}
