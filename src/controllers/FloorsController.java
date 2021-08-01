
package controllers;

import flyweight.FloorFactory;
import models.data.CurrentStudent;
import models.data.FloorDTO;
import models.data.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.DAODorms;


public class FloorsController implements Initializable {
    
    @FXML Button f0;
    @FXML Button f1;
    @FXML Button f2;
    @FXML Button f3;
    
    private final DAODorms dormsDao;

    public FloorsController() {
        this.dormsDao = new DAODorms();
    }
    
    CurrentStudent currStudent = CurrentStudent.getCurrentStudent();
    Student student = currStudent.getStudent();
    
    ArrayList<Button> buttons = new ArrayList();
    
    public void backButton(ActionEvent event) throws IOException {
        
        FXMLLoader loader;
        if(student.getBlock().charAt(0) > 'E')loader=new FXMLLoader(getClass().getResource("/views/MaleBlocks.fxml"));
        else loader=new FXMLLoader(getClass().getResource("/views/FemaleBlocks.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Blocks");
        stage.show();
    }
    //set floor proprety in the current student instance to the chosen floor's name and load next page
    public void floorSelected(ActionEvent event) throws IOException{
        Button button =(Button) event.getSource();
        student.setFloor(Integer.parseInt(button.getText()));
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Rooms.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Rooms");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dormsDao.getFloors(student.getBlock());
        } catch (SQLException ex) {
            Logger.getLogger(FloorsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize floor buttons based on the current block, floors that are full are disabled with red bakcground
        buttons.add(f0);
        buttons.add(f1);
        buttons.add(f2);
        buttons.add(f3);
        for(Button button: buttons){
            FloorDTO floor = (FloorDTO)FloorFactory.getFloor(Integer.parseInt(button.getText()));
            if(floor.isFull().equals("yes")){button.setStyle("-fx-background-color: red");button.setDisable(true);break;}
        }
    }    
    
}
