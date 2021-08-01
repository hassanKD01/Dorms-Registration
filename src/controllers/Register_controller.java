package controllers;


import models.data.CurrentStudent;
import models.data.Student;
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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import services.AlertServices;
import services.VerificationService;

public class Register_controller implements Initializable {
        
    @FXML
    TextField firstName;
    @FXML
    TextField lastName;
    @FXML
    TextField middleName;
    @FXML
    TextField id;
    @FXML
    TextField tel;
    @FXML
    TextField email;
    @FXML
    ComboBox comboBox;
    
    private final VerificationService service;
    private final AlertServices alerts;

    public Register_controller() {
        this.service = new VerificationService();
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
    public void createStudent(ActionEvent event) throws IOException, SQLException{
        
        /*-----verifying input--------*/
        if(firstName.getText().isEmpty() || middleName.getText().isEmpty() || lastName.getText().isEmpty() || id.getText().isEmpty() || tel.getText().isEmpty() || email.getText().isEmpty()){
             alerts.alert(Alert.AlertType.ERROR, "Error", "Missing info", "Please fill all fields");
             return;
        }
        /*-----verifying input--------*/
        int intId =Integer.parseInt(id.getText());
        String inEmail = email.getText().trim();
        String inTel = tel.getText().trim();
        if(!validInput(inEmail,inTel,intId)) return ;
        
        /*-----creating student instance--------*/
        Button button =(Button) event.getSource();
        Student student = new Student(firstName.getText().trim()+" "+middleName.getText().trim()+" "+lastName.getText().trim(), comboBox.getValue().toString(), button.getText(), intId, inEmail , inTel);
        CurrentStudent currStudent = CurrentStudent.getCurrentStudent();
        currStudent.setStudent(student);
        /*-----creating student instance--------*/
        
        /*move to the page according to the students gender*/
        FXMLLoader loader;
        if(button.getText().equals("Female")){
            loader=new FXMLLoader(getClass().getResource("/views/FemaleBlocks.fxml"));
        }
        else  loader=new FXMLLoader(getClass().getResource("/views/MaleBlocks.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Blocks");
        stage.show();
    }
    
    /*verifying that id and telephone inputs are numeric values*/
    public void numericInput(KeyEvent key ){
        TextField input = (TextField) key.getSource();
        if(!input.getText().matches("\\d+")){
            input.setText("");
            alerts.alert(Alert.AlertType.ERROR, "Error", "Wrong input type", input.getId()+" must be a number(integer)");
            key.consume();
        }
    }
    //verifying that the the name inserted contains only characters
    public void alphabetInput(KeyEvent key){
        TextField input = (TextField) key.getSource();
        if(!input.getText().matches("[a-z]+")){
            input.setText("");
            alerts.alert(Alert.AlertType.ERROR, "Error", "Wrong input type", input.getId()+" can't contain numbers or symbols");
            key.consume();
        }
    }
    
    //checking if the inserted ID already exists, and verifying the telephone number and email format
    public boolean validInput(String email,String tel,int id) throws SQLException{
        boolean flag = true;
        if(service.alreadyExists(id)){
            this.id.setText("");
            flag = false;
            alerts.alert(Alert.AlertType.ERROR, "Error", "Duplicate ID", "The inserted ID already belongs to a student");
        }
        if(!service.emailVerification(email)){
            flag = false;
            alerts.alert(Alert.AlertType.ERROR, "Error", "Invalid email", "The inserted email is invalid");
        }
        if(!service.telVerification(tel)){
            flag = false;
            alerts.alert(Alert.AlertType.ERROR, "Error", "Invalid tel", "The inserted tel is invalid");
        }
        return flag;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        comboBox.getItems().add("A+");
        comboBox.getItems().add("A-");
        comboBox.getItems().add("B+");
        comboBox.getItems().add("B-");
        comboBox.getItems().add("AB+");
        comboBox.getItems().add("AB-");
        comboBox.getItems().add("O+");
        comboBox.getItems().add("O-");
    }    
    
}
