/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * @author xach
 */
public class CustomersController {
    private CustomersModel customers = new CustomersModel();
    private VBox vbox;

    @FXML
    public ScrollPane scrollPane;
    @FXML
    public TextField findText;

    @FXML
    public void initialize() {
        setScrollPane(customers.getCustomers());
        
    } 
    
    private void highlightRow(HBox hbox) {
        vbox.getChildren().forEach(i -> {
            i.getStyleClass().remove("customer-list-highlight");
        });
        hbox.getStyleClass().add("customer-list-highlight");
    }

    @FXML
    private void findByName() {
        setScrollPane(customers.findByName(findText.getText()));
    }

    private void setScrollPane(List<Customer> customersParam) {
        
        int row = 1;
        GridPane gridPane = new GridPane();
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);
        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(50);
        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(25);

        List<Node> items = new ArrayList<>();
        
        HBox headers = new HBox();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        Label lName = new Label("Name");
        lName.getStyleClass().add("customer-list-header");
        headers.getChildren().add(lName);
        Label lAddress = new Label("Address");
        lAddress.getStyleClass().add("customer-list-header");
        headers.getChildren().add(lAddress);
        Label lPhone = new Label("Address");
        lPhone.getStyleClass().add("customer-list-header");
        headers.getChildren().add(lPhone);
        headers.getStyleClass().add("row");
        items.add(headers);
            System.out.println("Set Customers");
        for(Customer customer: customersParam) {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("row");
            Label cusName = new Label(customer.getName());
            cusName.getStyleClass().add("customer-list-item");
            Label cusAdd = new Label(customer.getAddress());
            cusAdd.getStyleClass().add("customer-list-item");
            Label cusPhone = new Label(customer.getPhone());
            cusPhone.getStyleClass().add("customer-list-item");
            cusName.setOnMouseClicked(e -> {
                highlightRow(hbox);
                System.out.println("Customer "+customer.getId()); 
            });
            cusAdd.setOnMouseClicked(e -> {
                highlightRow(hbox);
                System.out.println("Customer "+customer.getId()); 
            });
            cusPhone.setOnMouseClicked(e -> {
                highlightRow(hbox);
                System.out.println("Customer "+customer.getId()); 
            });
            hbox.getChildren().addAll(cusName, cusAdd, cusPhone);
            
            hbox.setCache(true);
            hbox.setCacheHint(CacheHint.SPEED);
            items.add(hbox);
        };
            System.out.println("end loop");
            vbox = new VBox();
            vbox.getStyleClass().add("row");
            vbox.getChildren().addAll(items);
            scrollPane.setContent(vbox);
    }
}
