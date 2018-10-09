/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.SQLiteStorage;
import com.gti.windowcleaning.data.StorageI;
import com.gti.windowcleaning.model.CustomersModel;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import javafx.collections.FXCollections;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class AddEditCustomerControllerTest extends ControllerTest<AddEditCustomerController> {
    
   
    @Before
    public void setUp() {
        setConroller(new AddEditCustomerController());
        URL dbPath =  getClass().getResource("/mocks/test.db");
        File file = new File(dbPath.getPath());
        boolean file_exists = file.exists();
        if(file_exists) {
            file.delete();
        }
        StorageI storage = new SQLiteStorage(dbPath.toString());
        storage.create(Customer.class);

        getController().setModel(new CustomersModel(storage));
    }

    @Test
    public void testSetAndGetFXMLField() throws Exception {
        setFXMLField("name", new TextField("ted"));
        
        Assert.assertEquals("ted", getFXMLField("name", TextField.class).getText());
    }
    @Test
    public void testAddCustomer() throws Exception {
        CheckBox active = new CheckBox();
        active.setSelected(true);
        setFXMLField("active", active);
        
        setFXMLField("inactiveSince", new DatePicker(LocalDate.of(2018, 12, 1)));
        CheckBox AWWPur = new CheckBox();
        AWWPur.setSelected(false);
        setFXMLField("AWWPur", AWWPur);
        setFXMLField("name", new TextField("Terry"));
        setFXMLField("area", new TextField("Salsibury"));
        setFXMLField("location", new TextField("Salisbury"));
        ChoiceBox type = new ChoiceBox<>(FXCollections.observableArrayList("C", "R"));
        type.getSelectionModel().select(1);
        setFXMLField("type", type);
        ChoiceBox company = new ChoiceBox<>(FXCollections.observableArrayList("C", "R"));
        company.getSelectionModel().select(0);
        setFXMLField("monthlyBill", new TextField(""));
        setFXMLField("company", company);
        setFXMLField("customerSince", new DatePicker(LocalDate.of(1981, 9, 1)));
        setFXMLField("obtainedBy", new TextField("Tim"));
        setFXMLField("royaltyTo", new TextField("Jim"));
        setFXMLField("contact", new TextField("Harry"));
        setFXMLField("addressLine1", new TextField("123 elm st"));
        setFXMLField("addressLine2", new TextField());
        setFXMLField("city", new TextField("Salisbury"));
        setFXMLField("state", new TextField("MD"));
        setFXMLField("zip", new TextField("21456"));
        setFXMLField("phone", new TextField("123-456-7890"));
        setFXMLField("fax", new TextField("890-567-1234"));
        setFXMLField("contactB", new TextField("Harry"));
        setFXMLField("addressLine1B", new TextField("123 elm st"));
        setFXMLField("addressLine2B", new TextField());
        setFXMLField("cityB", new TextField("Salisbury"));
        setFXMLField("stateB", new TextField("MD"));
        setFXMLField("zipB", new TextField("21456"));
        setFXMLField("phoneB", new TextField("123-456-7890"));
        setFXMLField("faxB", new TextField("890-567-1234"));
        setFXMLField("billCharge", new TextField("10")); 
        setFXMLField("credit", new TextField("")); 
        setFXMLField("deposit", new TextField("5")); 
        setFXMLField("salesTax", new TextField("0.06")); 
        CheckBox fourWeekInvoice = new CheckBox();
        fourWeekInvoice.setSelected(false);
        setFXMLField("fourWeekInvoice", fourWeekInvoice); 
        CheckBox calendarMonthInvoice = new CheckBox();
        calendarMonthInvoice.setSelected(false);
        setFXMLField("calendarMonthInvoice", calendarMonthInvoice); 
        CheckBox quarterlyInvoice = new CheckBox();
        quarterlyInvoice.setSelected(false);
        setFXMLField("quarterlyInvoice", quarterlyInvoice); 
        setFXMLField("customerNotes", new TextArea("Some notes about the customer")); 
        setFXMLField("collectionNotes", new TextArea("Some notes about the customers bill")); 

        getController().updateCustomer();

        CustomersModel model = getController().getModel();

        Customer customer = model.get(11);
    
        Assert.assertEquals("Terry", customer.getName());

    }




    
}
