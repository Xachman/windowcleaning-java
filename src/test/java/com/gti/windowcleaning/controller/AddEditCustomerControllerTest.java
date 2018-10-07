/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;
import org.testfx.util.WaitForAsyncUtils;

/**
 *
 * @author xach
 */
public class AddEditCustomerControllerTest extends ApplicationTest {
    private Parent root; 
    
    private CheckBox active; 
    private Button update;
    private TextField name;
    
    @Override
    public void start(Stage stage) throws IOException {
            root = FXMLLoader.load(AddEditCustomerController.class.getResource("/view/customer/add_edit.fxml"));
            stage.setScene(new Scene(root));
            stage.show();
            stage.toFront();
    }
   
        /* Just a shortcut to retrieve widgets in the GUI. */
    public <T extends Node> T find(final String query) {
        /** TestFX provides many operations to retrieve elements from the loaded GUI. */
        return lookup(query).query();
    }
   
    @Before
    public void setUp() {
        /* Just retrieving the tested widgets from the GUI. */
        active = find("#active");
        update = find("#update");
        name = find("#name");
    }
    @Test
    public void addNewCustomer() {
        clickOn(active);
        
        WaitForAsyncUtils.waitForFxEvents();
        clickOn(update);
        WaitForAsyncUtils.waitForFxEvents();
        Assert.assertEquals("test", name.getText());
    }
    
}
