package controllers;



import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.DAOStudents;
import services.AlertServices;


public class Remove_contoller implements Initializable {
    
    @FXML TextField id;
    private final DAOStudents studentsDao;
    private final AlertServices alerts;

    public Remove_contoller() {
        this.studentsDao = new DAOStudents();
        this.alerts = new AlertServices();
    }

    /*back to main page button*/
    public void mainPageButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
    }
    
    /*to search page button*/
    public void toSearchPage(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search student page");
            stage.show();
     }
    
    //get id from user remove student with the id inserted alert if deletion failed ,else ask if user wants to go to mainpage
    public void remove(ActionEvent event) throws IOException, SQLException{
        int intid = Integer.parseInt(id.getText());
        int deleted = studentsDao.deleteStudent(intid);
        if(deleted == 0){
            alerts.alert(Alert.AlertType.ERROR, "Error", "Removing failed", "Something went wrong while removing \n Check if the id inserted is correct");
            event.consume();
        }
        else{//student removed successfully
            //the user has the option to stay in remove page(presses cancel) or return to main page(presses ok)
            Optional<ButtonType> result = alerts.confirmationAlert("Success", "Student removed", "Student removed successfully would you like to return to the main page?");
            
            if(result.get() == ButtonType.OK){
                
                 //ok button is pressed
                FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
                Parent root=(Parent) loader.load();
                Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                stage.setScene(new Scene(root));
                stage.setTitle("Main page");
                stage.show();
            }
            // cancel button is pressed
            else if(result.get() == ButtonType.CANCEL)
                event.consume();
        }
    }
    
    public void numericInput(KeyEvent key ){
        TextField input = (TextField) key.getSource();
        if(!input.getText().matches("\\d+")){
            input.setText("");
            alerts.alert(Alert.AlertType.ERROR, "Error", "Wrong input type", input.getId()+" must be a number(integer)");
            key.consume();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
