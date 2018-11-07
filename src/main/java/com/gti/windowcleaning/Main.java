/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.model.Model;
import com.gti.windowcleaning.storage.SQLiteStorage;
import com.gti.windowcleaning.web.controller.DeleteController;
import com.gti.windowcleaning.web.controller.ListController;
import com.gti.windowcleaning.web.controller.EditCreateController;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.logging.Level;
import java.util.logging.Logger;
import spark.Request;

import javax.swing.*;

import static spark.Spark.*;
/**
 *
 * @author xach
 */
public class Main {
    private static String workDir;
    public static void main(String[] args) {
        workDir = System.getProperty("user.dir");
        startWeb();
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
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return sb.toString();
    }

    public static void startWeb() {
        String storagePath = System.getProperty("user.home")+File.separator+".windowcleaning";
        File storageDir = new File(storagePath); 
        if(!storageDir.exists()){
            storageDir.mkdir();
        }
         
        System.out.println(storagePath);
        SQLiteStorage storage = new SQLiteStorage(storagePath+File.separator+"data.db");
        storage.create(Customer.class);
        storage.create(Job.class);
        staticFiles.location("/webui");
        port(8080);
        before((request, response) -> {
            response.header("Access-Control-Allow-Origin", "*");
            response.header("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
            response.header("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
            response.header("Access-Control-Expose-Headers", "Content-Range");
            System.out.println(requestInfoToString(request));
        });
        get("/ui",(req, res) -> {
            res.redirect("http://localhost:3000");
            return null;
        });

        options("/*", (req, res) -> "");

        Model<Job> jobModel = new Model<>(Job.class, storage);
        Model<Customer> customerModel = new Model<>(Customer.class, storage);

        get("/customers",new ListController(customerModel));
        delete("/customers/:id",new DeleteController(customerModel));
        post("/customers",new EditCreateController(customerModel));
        put("/customers/:id",new EditCreateController(customerModel));
        get("/customers/filter/:field",new ListController(customerModel));

        get("/jobs", new ListController(jobModel));
        post("/jobs", new EditCreateController(jobModel));
        put("/jobs/:id", new EditCreateController(jobModel));
        delete("/job/:id",new DeleteController(jobModel));


    }

    

    public static boolean openBrowser(String uri) {
        try {
            // enter code here


            Process p = new ProcessBuilder().command(
                    new File(workDir+"/executables/nw/nw").getAbsolutePath(),
                    new File(workDir+"/nw").getAbsolutePath()
            ).inheritIO().start();
            int exitCode = p.waitFor();
            System.exit(0);
            return true;
            // enter code here
        } catch (Exception err) {
            err.printStackTrace();
        }
        return false;
    }

    public static void popUpMessage(String infoMessage, String titleBar) {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
}
