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
import com.gti.windowcleaning.pdf.Schedule;

import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SchedulePDFController extends Controller<EmptyPayload, ByteArrayOutputStream> {
    Model<Customer> customersModel;
    Model<Job> jobsModel;
    Model<Order> ordersModel;
    public SchedulePDFController(Model<Customer> customersModel, Model<Job> jobsModel, Model<Order> ordersModel) {
        super(EmptyPayload.class);

        this.customersModel = customersModel;
        this.jobsModel = jobsModel;
        this.ordersModel = ordersModel;

    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        JSONParser parser = new JSONParser();
        Map<String, String> headers = new HashMap<>();
        try {
            QueryBuilder qb = new QueryBuilder();
            if(urlParams.get(":field") != null || query.get("filter") != null) {
                String field = urlParams.get(":field");
                JSONObject filter = (JSONObject) parser.parse(query.get("filter"));
                if(filter.size() > 0) {
                    for(Object key: filter.keySet()) {
                        if(key.toString().equals("q")) {
                            String q = filter.get("q").toString();
                            qb.addFilter(field, q);
                            continue;
                        }
                        qb.addFilter(key.toString(), filter.get(key));
                    }

                }
            }
            if(query.get("between") != null) {
                JSONObject between = (JSONObject) parser.parse(query.get("between"));
                String field = between.keySet().iterator().next().toString();
                JSONArray values = (JSONArray) between.values().iterator().next();
                Object value1 = ordersModel.processField(field, values.get(0));
                Object value2 = ordersModel.processField(field,values.get(1));
                qb.addBetween(field, value1, value2);
            }

            List<Order> orders = ordersModel.execute(qb);

            Map<String,Object> data = new HashMap<>();
            List<Map<String,String>> items = new ArrayList<>();
            for(Order order: orders) {
                order.setJob(jobsModel.get(order.getJob().getId()));
                order.getJob().setCustomer(customersModel.get(order.getJob().getCustomer().getId()));
                Map<String,String> item1 = new HashMap<>();
                item1.put("customer", order.getJob().getCustomer().getName());
                item1.put("doneBy", order.getDoneBy());
                item1.put("location",  order.getJob().getCustomer().getLocation());
                item1.put("notes", (order.getJob().getNotes() != null)?
                        order.getJob().getNotes().replace("\n", "").replace("\r", "") : "");
                item1.put("amount", ""+order.getJob().getAmount());
                SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy H:mma");
                item1.put("dateTime", sdf.format(order.getServiceDate()));
                item1.put("invoice", "");
                item1.put("payment", ""+order.getJob().getAmount());
                item1.put("totals", ""+order.getJob().getAmount());
                items.add(item1);
            }
            data.put("schedule", items);

            Schedule scheduleGenerator = new Schedule();
            scheduleGenerator.setData(data);
            ByteArrayOutputStream pdfOutSteam = scheduleGenerator.process();
            headers.put("Content-Type", "application/pdf");
            return new Answer(200, pdfOutSteam, headers);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return new Answer(400, "Error");
    }
}
