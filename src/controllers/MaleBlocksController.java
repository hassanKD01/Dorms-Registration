
package controllers;

import flyweight.BlockFactory;
import models.data.BlockDTO;
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
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.DAODorms;

public class MaleBlocksController implements Initializable {
    
    @FXML Button F;
    @FXML Button G;
    
    private final DAODorms dormsDao;

    public MaleBlocksController() {
        this.dormsDao = new DAODorms();
    }
    
    //to go back to information registration page
    public void mainPageButton(ActionEvent event) throws IOException {
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
            stage.setTitle("Floors ");
            stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dormsDao.getBlocks("m");
        } catch (SQLException ex) {
            Logger.getLogger(MaleBlocksController.class.getName()).log(Level.SEVERE, null, ex);
        }
        //initialize blocks if block is full the block button will be disabled with red background
        BlockDTO block = (BlockDTO)BlockFactory.getBlock(F.getText());
        if(block.isFull().equals("yes")){F.setStyle("-fx-background-color: red");F.setDisable(true);}
        block = BlockFactory.getBlock(G.getText());
        if(block.isFull().equals("yes")){G.setStyle("-fx-background-color: red");G.setDisable(true);}
    }    
    
}
