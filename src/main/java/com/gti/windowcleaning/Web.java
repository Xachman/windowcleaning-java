/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.web.controller.customers.CustomerController;
import com.gti.windowcleaning.web.controller.customers.CustomersController;
import com.gti.windowcleaning.web.controller.customers.DeleteCustomerController;
import com.gti.windowcleaning.web.controller.customers.EditCreateCustomerController;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;
import static spark.Spark.*;
/**
 *
 * @author xach
 */
public class Web {
    public static void main(String[] args) {
        String storagePath = System.getProperty("user.home")+File.separator+".windowcleaning";
        File storageDir = new File(storagePath); 
        if(!storageDir.exists()){
            storageDir.mkdir();
        }
         
        System.out.println(storagePath);
        SQLiteStorage storage = new SQLiteStorage(storagePath+File.separator+"data.db");
        storage.create(Customer.class);
        storage.create(Job.class);
        port(8080);
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
            response.header("Access-Control-Expose-Headers", "Content-Range");
            System.out.println(requestInfoToString(request));
        });

        get("/customers",new CustomersController(new CustomersModel(storage)));
        options("/customers",new CustomersController(new CustomersModel(storage)));
        
        get("/customers/:id",new CustomerController(new CustomersModel(storage)));
        options("/customers/:id",new CustomerController(new CustomersModel(storage)));
        delete("/customers/:id",new DeleteCustomerController(new CustomersModel(storage)));
        post("/customers",new EditCreateCustomerController(new CustomersModel(storage)));
        put("/customers/:id",new EditCreateCustomerController(new CustomersModel(storage)));
    } 

    private static String requestInfoToString(Request request) {
        StringBuilder sb = new StringBuilder();
        sb.append(request.requestMethod());
        sb.append(" " + request.url());
        sb.append(" " + request.body());
        if(request.queryString() != null) {
            try {
                sb.append(" " + URLDecoder.decode(request.queryString(), "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Web.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }
}
