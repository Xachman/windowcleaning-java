package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;

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
        return null;
    }
}
