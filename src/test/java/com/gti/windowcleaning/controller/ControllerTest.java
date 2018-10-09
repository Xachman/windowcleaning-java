/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXML;
import org.junit.BeforeClass;

/**
 *
 * @author xach
 */
public class ControllerTest<T> {
    public T controller;
    

    @BeforeClass
    static public void init() {
        new JFXPanel();
    }
    protected void setConroller(T controller) {
        this.controller = controller;    
    }

    protected void setFXMLField(String fieldName, Object value) throws Exception {
        Field field = controller.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        FXML fxml = field.getAnnotation(FXML.class);
        if(fxml == null) {
            throw new Exception("No FXML annotation");
        }
        System.out.println(fxml);

        System.out.println(field.getName());
        field.set(controller, value); 
    }

    protected <Y> Y getFXMLField(String fieldName, Class<Y> clazz) throws Exception {
        Field field = controller.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        FXML fxml = field.getAnnotation(FXML.class);
        if(fxml == null) {
            throw new Exception("No FXML annotation");
        }
        return (Y) field.get(controller);
    }

    protected T getController() {
        return controller;
    }
    
    
}