package com.gti.windowcleaning.web.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.web.Answer;
import com.gti.windowcleaning.web.Controller;
import com.gti.windowcleaning.web.valid.EmptyPayload;

import java.io.ByteArrayOutputStream;
import java.util.Map;

public class SchedulePDFController extends Controller<EmptyPayload, ByteArrayOutputStream> {
    Model<Customer> customerModel;
    Model<Job> jobModel;
    Model<Order> orderModel;
    public SchedulePDFController(Model<Customer> customersModel, Model<Job> jobsModel, Model<Order> ordersModel) {
        super(EmptyPayload.class);

        this.customerModel = customersModel;
        this.jobModel = jobsModel;
        this.orderModel = ordersModel;

    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, Map<String, String> query, boolean shouldReturnHtml) {
        return null;
    }
}
