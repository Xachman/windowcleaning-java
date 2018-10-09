/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import com.gti.windowcleaning.model.MustIncludeException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;

/**
 *
 * @author xach
 */
public class AddEditCustomerController implements Initializable {
    private int id;
    CustomersModel model = new CustomersModel();

    @FXML
    private TextField name;
    @FXML
    private CheckBox active;
    @FXML
    private DatePicker inactiveSince;
    @FXML
    private CheckBox AWWPur;
    @FXML
    private TextField location;
    @FXML
    private TextField area;
    @FXML
    private ChoiceBox type;
    @FXML
    private ChoiceBox company;
    @FXML
    private TextField monthlyBill;
    @FXML
    private DatePicker customerSince;
    @FXML
    private TextField obtainedBy;
    @FXML
    private TextField royaltyTo;
    @FXML
    private TextField contact;
    @FXML
    private TextField addressLine1;
    @FXML
    private TextField addressLine2;
    @FXML
    private TextField city;
    @FXML
    private TextField state;
    @FXML
    private TextField zip;
    @FXML
    private TextField fax;
    @FXML
    private TextField phone;
    @FXML
    private TextField contactB;
    @FXML
    private TextField addressLine1B;
    @FXML
    private TextField addressLine2B;
    @FXML
    private TextField cityB;
    @FXML
    private TextField stateB;
    @FXML
    private TextField zipB;
    @FXML
    private TextField faxB;
    @FXML
    private TextField phoneB;
    @FXML
    private TextField billCharge;
    @FXML
    private TextField credit;
    @FXML
    private TextField deposit;
    @FXML
    private TextField salesTax;
    @FXML
    private CheckBox fourWeekInvoice;
    @FXML
    private CheckBox calendarMonthInvoice;
    @FXML
    private CheckBox quarterlyInvoice;
    @FXML
    private TextArea customerNotes;
    @FXML
    private TextArea collectionNotes;


    
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
    public void updateCustomer() throws MustIncludeException {
        Customer customer = new Customer();
        customer.setName(name.getText());
        customer.setLocation(location.getText());
        customer.setArea(area.getText());
        customer.setAWWPUR(AWWPur.isSelected());

        model.save(customer);
       
    }

    public CustomersModel getModel() {
        return model; 
    }

    public void setModel(CustomersModel model) {
        this.model = model;
    }
}
