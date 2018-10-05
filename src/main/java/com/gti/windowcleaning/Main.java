/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gti.windowcleaning;

import com.gti.windowcleaning.data.Customer;
import com.gti.windowcleaning.data.Job;
import com.gti.windowcleaning.data.SQLiteStorage;
import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
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
        scene = new Scene(root, 1000, 720);
        scene.getStylesheets().add(getClass().getResource("/css/main.css").toString());
        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println(scene);
        System.out.println(scene.getStylesheets());
        String storagePath = System.getProperty("user.home")+File.separator+".windowcleaning";
        File storageDir = new File(storagePath); 
        if(!storageDir.exists()){
            storageDir.mkdir();
        }
         
        System.out.println(storagePath);
        SQLiteStorage storage = new SQLiteStorage(storagePath+File.separator+"data.db");
        storage.create(Customer.class);
        storage.create(Job.class);
    }

 
}
