package controllers;



import models.DAOAdmin;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.AlertServices;

   

public class LoginPage_controller implements Initializable{
        
     @FXML
     TextField userName;
     @FXML
     PasswordField password;
     
     private final DAOAdmin adminDao;
     private final AlertServices alerts;

    public LoginPage_controller() {
        this.adminDao = new DAOAdmin();
        this.alerts = new AlertServices();
    }
     
    public void verify(ActionEvent actionevent)throws IOException, SQLException{
        String username = userName.getText();
        String userpassword = password.getText();
        //alert if any field is empty
        if(username.isEmpty() || userpassword.isEmpty()){
            alerts.alert(Alert.AlertType.ERROR, "Error", "Missing info", "Please fill both fields");
            return;
        }
        //if password and username are correct move to main page
        if(adminDao.verifyAdmin(username, userpassword)){
            FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)actionevent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
        }
        //alert if username or password are invalid
        else{
            userName.setText("");
            password.setText("");
            alerts.alert(Alert.AlertType.ERROR, "Error", "Invalid info", "Invalid username or password.");
         }
     }
     
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {    
        
    }    
    
}