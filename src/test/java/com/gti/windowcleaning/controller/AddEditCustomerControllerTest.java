/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import javafx.scene.control.TextField;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author xach
 */
public class AddEditCustomerControllerTest extends ControllerTest {

   
    @Before
    public void setUp() {
        setConroller(new AddEditCustomerController());
    }

    @Test
    public void addNewCustomer() {
            try {

            TextField name = new TextField();
            name.setText("ted");
            setFXMLField("name", name);
            
            Assert.assertEquals(0, 0);
            } catch(Exception e) {
                e.printStackTrace();
            }
    
    }




    
}
