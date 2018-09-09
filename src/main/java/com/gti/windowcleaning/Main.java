/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import java.io.IOException;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author xach
 */
public class Main extends Application {
    Scene scene;
    @FXML
    private AnchorPane rootPane; 
    @FXML
    private VBox rootVBox;

    @FXML
    private MenuBar menuBar; 
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/main.fxml"));
        scene = new Scene(root, 800, 500);
        primaryStage.setScene(scene);
        primaryStage.show();

        System.out.println(scene);

    }

 
}
