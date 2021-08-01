/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

/**
 functions to navigate between pages
 or USER
 **/
public class MainpageController implements Initializable {

    public void Register_function(ActionEvent event) throws IOException{
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Register_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Add student page");
            stage.show();

     }
     public void Update_function(ActionEvent event) throws IOException{
          FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Update_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Remove student page");
            stage.show();
     }
     public void Search_function(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search student page");
            stage.show();
     }
     public void Remove_function(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Remove_fxml.fxml"));
         Parent root=(Parent) loader.load();
         Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         stage.setScene(new Scene(root));
         stage.setTitle("Update registration page");
         stage.show();      
     }
     public void logoutButton(ActionEvent event) throws IOException{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Warning");
        alert.setHeaderText("Logout confirmation");
        alert.setContentText("Are you sure you want to logout?");
        Optional<ButtonType> result = alert.showAndWait();
        if(result.get() == ButtonType.OK){
            //ok button is pressed
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/LoginPage_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Login page");
            stage.show();
        }
        else return;
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
