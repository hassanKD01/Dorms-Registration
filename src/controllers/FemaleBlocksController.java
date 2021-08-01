
package controllers;

import flyweight.BlockFactory;
import models.data.BlockDTO;
import models.data.CurrentStudent;
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


public class FemaleBlocksController implements Initializable {
    
    @FXML Button A;
    @FXML Button B;
    @FXML Button C;
    @FXML Button D;
    @FXML Button E;
    ArrayList<Button> buttons = new ArrayList();
    
    private final DAODorms dormsDao;

    public FemaleBlocksController() {
        this.dormsDao = new DAODorms();
    }
    
    //to go back to information registration page
    public void backButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Register_fxml.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Registration page");
            stage.show();
    }
   //set block proprety in the current student instance to the chosen block's name and load next page
    public void blockSelected(ActionEvent event) throws IOException{
        Button button =(Button) event.getSource();
        CurrentStudent currStudent = CurrentStudent.getCurrentStudent();
        Student student = currStudent.getStudent();
        student.setBlock(button.getText());
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Floors.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Floors");
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        buttons.add(A);
        buttons.add(B);
        buttons.add(C);
        buttons.add(D);
        buttons.add(E);
        try {
            dormsDao.getBlocks("f");
        } catch (SQLException ex) {
            Logger.getLogger(FemaleBlocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        for(Button button: buttons){
            BlockDTO block = (BlockDTO)BlockFactory.getBlock(button.getText());
            if(block.isFull().equals("yes")){button.setStyle("-fx-background-color: red");button.setDisable(true);break;}
        }
    }    
    
}
