/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.util.Json;
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
}
