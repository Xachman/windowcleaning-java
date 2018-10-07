/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.model.CustomersModel;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.CacheHint;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author xach
 */
public class CustomersController {
    private final CustomersModel customers = new CustomersModel();
    private VBox vbox;
    private int selectedItem = 0;

    @FXML
    private AnchorPane content; 
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
        setScrollPane(customers.findByFieldName("name"));
    }

    private void setScrollPane(List<Customer> customersParam) {
        
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
        Label lPhone = new Label("Phone");
        lPhone.getStyleClass().add("customer-list-header");
        headers.getChildren().add(lPhone);
        headers.getStyleClass().add("row");
        items.add(headers);
        customersParam.stream().map((customer) -> {
            HBox hbox = new HBox();
            hbox.getStyleClass().add("row");
            Label cusName = new Label(customer.getName());
            cusName.getStyleClass().add("customer-list-item");
            Label cusAdd = new Label(customer.getAddressLine1());
            cusAdd.getStyleClass().add("customer-list-item");
            Label cusPhone = new Label(customer.getPhone());
            cusPhone.getStyleClass().add("customer-list-item");
            cusName.setOnMouseClicked(e -> {
                selectItem(customer, hbox);
            });
            cusAdd.setOnMouseClicked(e -> {
                selectItem(customer, hbox);
            });
            cusPhone.setOnMouseClicked(e -> {
                selectItem(customer, hbox);
            });
            hbox.getChildren().addAll(cusName, cusAdd, cusPhone);
            return hbox;            
        }).map((hbox) -> {
            hbox.setCache(true);
            hbox.setCacheHint(CacheHint.SPEED);
            return hbox;
        }).forEachOrdered((hbox) -> {
            items.add(hbox);
        });
        System.out.println("end loop");
        vbox = new VBox();
        vbox.getStyleClass().add("row");
        vbox.getChildren().addAll(items);
        scrollPane.setContent(vbox);
    }

    private boolean isSelected(int itemId) {
        return itemId == selectedItem;
    }

    private void openAddEdit(int id) {
        Parent root;
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/customer/add_edit.fxml"));
            root = loader.load();
            Stage stage = (Stage) scrollPane.getScene().getWindow();
            Scene scene = stage.getScene();
            AddEditCustomerController aeController = loader.<AddEditCustomerController>getController();
            aeController.setId(id);
            content = (AnchorPane) scene.lookup("#rootPane");
            content.getChildren().setAll(root);
        } catch (IOException ex) {
            Logger.getLogger(CustomersController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public void selectItem(Customer customer, HBox hbox) {
        highlightRow(hbox);
        if(isSelected(customer.getId())) {
            openAddEdit(customer.getId());
        }
        setSelectedItem(customer.getId());
    }
}
