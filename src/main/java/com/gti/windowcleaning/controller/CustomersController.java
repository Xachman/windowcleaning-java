/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 *
 * @author xach
 */
public class CustomersController {
    private CustomersModel customers = new CustomersModel();
    @FXML
    public VBox scrollPaneVBox;

    @FXML
    public void initialize() {
        System.out.println(scrollPaneVBox);
        int row = 1;
        GridPane gridPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);
        gridPane.getColumnConstraints().addAll(col1, col2, col3);

        gridPane.add(new Label("Name"),1,0);
        gridPane.add(new Label("Address"),2,0);
        gridPane.add(new Label("Phone"),3,0);
        for(Customer customer: customers.getCustomers()) {
            gridPane.add(new Label(customer.getName()),1,row);
            gridPane.add(new Label(customer.getAddress()),2,row);
            gridPane.add(new Label(customer.getPhone()),3,row);
            row++;
        };
        scrollPaneVBox.getChildren().add(gridPane);
         
        
    } 

}
