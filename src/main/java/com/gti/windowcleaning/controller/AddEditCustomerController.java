/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 *
 * @author xach
 */
public class AddEditCustomerController implements Initializable {
    private int id;
    CustomersModel model = new CustomersModel();

    @FXML
    private TextField name;
    
    public void setId(int id) {
        this.id = id;
        setupCustomer(id);
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupCustomer(id);
    }

    private void setupCustomer(int id){
        if(id < 1) return;
        Customer customer = model.get(id);
        
        name.setText(customer.getName());
    }

    @FXML
    public void updateCustomer() {
        System.out.println("update");
        name.setText("test");
    }

}
