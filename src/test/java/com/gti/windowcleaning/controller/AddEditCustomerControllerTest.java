/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import java.util.Date;
import javafx.scene.control.CheckBox;
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
        setFXMLField("inactiveSince", new Date(2018, 12, 1));
        CheckBox AWWPur = new CheckBox();
        AWWPur.setSelected(false);
        setFXMLField("AWWPur", AWWPur);
        setFXMLField("name", new TextField("Terry"));

        getController().updateCustomer();

        CustomersModel model = getController().getModel();

        Customer customer = model.get(11);
    
        Assert.assertEquals("Terry", customer.getName());

    }




    
}
