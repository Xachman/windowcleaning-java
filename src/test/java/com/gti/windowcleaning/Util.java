/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.mock.Customer;
import com.gti.windowcleaning.mock.Job;
import com.gti.windowcleaning.storage.SQLiteStorage;
import com.gti.windowcleaning.storage.StorageI;
import com.gti.windowcleaning.util.Json;

import java.io.File;
import java.util.List;

/**
 *
 * @author xach
 */
public class Util {
    
    static public List<Customer> assembleCustomers() {
        Json jsonUtil = new Json();

        return jsonUtil.getCustomers(Util.class.getResource("/mocks/data/customers.json").getFile());
    }
    static public List<Job> assembleJobs(List<Customer> customers) {
        Json jsonUtil = new Json();

        return jsonUtil.getJobs(Util.class.getResource("/mocks/data/jobs.json").getFile(), customers);
    }

    static public StorageI getTestDB() {
        File file = new File("out/test.db");

        StorageI storage = new SQLiteStorage(file.getAbsolutePath());
        storage.drop(Job.class);
        storage.drop(Customer.class);
        storage.create(Customer.class);
        storage.create(Job.class);
        List<Customer> customers = Util.assembleCustomers();
        List<Job> jobs = Util.assembleJobs(customers);
        storage.add(Customer.class, customers);
        storage.add(Job.class, jobs);
        return storage;
    }
}
