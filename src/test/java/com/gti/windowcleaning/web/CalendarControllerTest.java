/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.web;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.Order;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.web.controller.CalendarController;
import com.gti.windowcleaning.web.valid.EmptyPayload;
import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;


/**
 *
 * @author xach
 */
public class CalendarControllerTest {
    StorageI storage;
    List<Job> jobs;
    List<Customer> customers;
    List<Order> orders;

    @Before
    public void setup() {
        jobs = new ArrayList<>();
        customers = new ArrayList<>();
        orders = new ArrayList<>();

        Customer customer1 = new Customer();
        customer1.setId(1);
        customer1.setActive(true);
        customer1.setArea("area1");
        customer1.setContact("contact1");
        customer1.setAddressLine1("street addy 1");
        customer1.setCity("city 1");
        customer1.setState("state 1");
        customer1.setContactB("contactB1");
        customer1.setAddressLine1B("street addy b 1");
        customer1.setCityB("cityB1");
        customer1.setStateB("stateB1");
        customer1.setAWWLine(true);
        customer1.setAWWPUR(true);
        customers.add(customer1);
        Customer customer2 = new Customer();
        customer2.setId(2);
        customer2.setActive(true);
        customer2.setArea("area2");
        customer2.setContact("contact2");
        customer2.setAddressLine1("street addy 2");
        customer2.setCity("city 2");
        customer2.setState("state 2");
        customer2.setContactB("contactB2");
        customer2.setAddressLine1B("street addy b 2");
        customer2.setCityB("cityB2");
        customer2.setStateB("stateB2");
        customer2.setAWWLine(true);
        customer2.setAWWPUR(true);
        customers.add(customer2);
        Customer customer3 = new Customer();
        customer3.setId(3);
        customer3.setActive(true);
        customer3.setArea("area3");
        customer3.setContact("contact3");
        customer3.setAddressLine1("street addy 3");
        customer3.setCity("city 3");
        customer3.setState("state 3");
        customer3.setContactB("contactB3");
        customer3.setAddressLine1B("street addy b 3");
        customer3.setCityB("cityB3");
        customer3.setStateB("stateB3");
        customer3.setAWWLine(true);
        customer3.setAWWPUR(true);
        customers.add(customer3);

        Job job1 = new Job();
        job1.setId(1);
        job1.setNotes("job notes 1");
        job1.setCustomer(customer1);
        job1.setPrintServiceStatement(true);
        job1.setDaysBetween(0);
        job1.setAmount(100);
        job1.setDifficulty(5);
        job1.setPercent(0.0);
        jobs.add(job1);
        Job job2 = new Job();
        job2.setId(2);
        job2.setNotes("job notes 2");
        job2.setCustomer(customer2);
        job2.setPrintServiceStatement(true);
        job2.setDaysBetween(0);
        job2.setAmount(200);
        job2.setDifficulty(5);
        job2.setPercent(0.0);
        jobs.add(job2);
        Job job3 = new Job();
        job3.setId(3);
        job3.setNotes("job notes 3");
        job3.setCustomer(customer3);
        job3.setPrintServiceStatement(true);
        job3.setDaysBetween(0);
        job3.setAmount(300);
        job3.setDifficulty(5);
        job3.setPercent(0.0);
        jobs.add(job3);



        Order order1 = new Order();
        order1.setId(1);
        order1.setDoneBy("Ted");
        order1.setJob(job1);
        order1.setStatus("C");
        order1.setServiceDate(new Date(1542240000000L));
        orders.add(order1);
        Order order2 = new Order();
        order2.setId(2);
        order2.setDoneBy("Ted");
        order2.setJob(job2);
        order2.setStatus("C");
        order2.setServiceDate(new Date(1542340000000L));
        orders.add(order2);
        Order order3 = new Order();
        order3.setId(3);
        order3.setDoneBy("Ted");
        order3.setJob(job2);
        order3.setStatus("C");
        order3.setServiceDate(new Date(1542440000000L));
        orders.add(order3);
        Order order4 = new Order();
        order4.setId(4);
        order4.setDoneBy("Ted");
        order4.setJob(job3);
        order4.setStatus("C");
        order4.setServiceDate(new Date(1542540000000L));
        orders.add(order4);
        Order order5 = new Order();
        order5.setId(5);
        order5.setDoneBy("Ted");
        order5.setJob(job2);
        order5.setStatus("C");
        order1.setServiceDate(new Date(1542640000000L));
        orders.add(order5);
        Order order6 = new Order();
        order6.setId(6);
        order6.setDoneBy("Ted");
        order6.setJob(job1);
        order6.setStatus("C");
        order6.setServiceDate(new Date(1542740000000L));
        orders.add(order6);
    }

    @Test
    public void getSchedule() throws JSONException, URISyntaxException, IOException {
        Model jobModel = mock(Model.class);
        Model customerModel = mock(Model.class);
        Model orderModel = mock(Model.class);
        EmptyPayload payload = new EmptyPayload();


        
        CalendarController controller = new CalendarController(orderModel, jobModel, customerModel);


    }
}
