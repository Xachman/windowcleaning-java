/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *
 * @author xach
 */
public class MenuBarController {
    @FXML
    private MenuBar menuBar;
    @FXML
    private AnchorPane content; 

   @FXML
   private void openCustomers(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/view/customers.fxml"));
        Stage stage = (Stage) menuBar.getScene().getWindow();
        Scene scene = stage.getScene();
        content = (AnchorPane) scene.lookup("#rootPane");

        content.getChildren().setAll(root);
        System.out.println(event.getTarget());
   } 
}
