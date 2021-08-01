
package controllers;

import models.data.CurrentStudent;
import models.data.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import models.DAOStudents;
import services.AlertServices;


public class EndOfRegistrationController implements Initializable {

    
    @FXML Label name;
    @FXML Label email;
    @FXML Label id;
    @FXML Label tel;
    @FXML Label bloodType;
    @FXML Label roomLabel;
    
    private final DAOStudents studentsDao;
    private final AlertServices alerts;

    public EndOfRegistrationController() {
        this.studentsDao = new DAOStudents();
        this.alerts = new AlertServices();
    }
    
    public void mainPage(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Main page");
        stage.show();
    }
    //get the student's data and insert in students table, alert if insertion doesn't work
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        CurrentStudent currStudent = CurrentStudent.getCurrentStudent();
        Student student = currStudent.getStudent();
        name.setText(name.getText()+student.getName());
        id.setText(id.getText()+student.getId());
        email.setText(email.getText()+student.getEmail());
        tel.setText(tel.getText()+student.getTel());
        bloodType.setText(bloodType.getText()+student.getBloodType());
        roomLabel.setText(roomLabel.getText()+student.getBlock()+student.getFloor()+student.getRoom());
        int inserted =0;
        try {
            inserted = studentsDao.insertStudent(student);
        } catch (SQLException ex) {
            Logger.getLogger(EndOfRegistrationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (inserted ==0 ){
            alerts.alert(Alert.AlertType.ERROR, "Error", "Registration failed", "Something went wrong while adding student");
        }
    }    
    
}
