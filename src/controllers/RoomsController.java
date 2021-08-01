
package controllers;

import flyweight.RoomFactory;
import models.data.CurrentStudent;
import models.data.RoomDTO;
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


public class RoomsController implements Initializable {
    
    private final DAODorms dormsDao;
    CurrentStudent currStudent = CurrentStudent.getCurrentStudent();
    Student student = currStudent.getStudent();
   
    // <editor-fold defaultstate="collapsed" desc="FXML rooms buttons references">
    @FXML Button r1;
    @FXML Button r2  ;
    @FXML Button r3  ;
    @FXML Button r4  ;
    @FXML Button r5  ;
    @FXML Button r6  ;
    @FXML Button r7  ;
    @FXML Button r8  ;
    @FXML Button r9  ;
    @FXML Button r10 ;
    @FXML Button r11 ;
    @FXML Button r12 ;
    @FXML Button r13 ;
    @FXML Button r14 ;
    @FXML Button r15 ;
    @FXML Button r16 ;
    @FXML Button r17 ;
    @FXML Button r18 ;
    @FXML Button r19 ;
    @FXML Button r20 ;
    @FXML Button r21 ;
    @FXML Button r22 ;
    @FXML Button r23 ;
    @FXML Button r24 ;
    @FXML Button r25 ;
    @FXML Button r26 ;
    @FXML Button r27 ;
    @FXML Button r28 ;
    @FXML Button r29 ;
    @FXML Button r30 ;
    @FXML Button r31 ;
    @FXML Button r32 ;
    @FXML Button r33 ;
    @FXML Button r34 ;
    @FXML Button r35 ;
    @FXML Button r36 ;  
         // </editor-fold>

    ArrayList<Button> buttons = new ArrayList();

    public RoomsController() {
        this.dormsDao = new DAODorms();
    }
    
    //button to go back to floors page
    public void backButton(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Floors.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Floors");
        stage.show();
    }
    //set room proprety in the current student instance to the chosen room's name and load next page
    public void roomSelected(ActionEvent event) throws IOException{
        
        Button button =(Button) event.getSource();
        student.setRoom(Integer.parseInt(button.getText()));
        
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/EndOfRegistration.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Registration Completed");
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            dormsDao.getRooms(student.getBlock(), student.getFloor());
        } catch (SQLException ex) {
            Logger.getLogger(RoomsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        // <editor-fold defaultstate="collapsed" desc="add rooms buttons to buttons arraylist">
        buttons.add( 0 ,r1 );
        buttons.add( 1 ,r2 );
        buttons.add( 2 ,r3 );
        buttons.add( 3 ,r4 );
        buttons.add( 4 ,r5 );
        buttons.add( 5 ,r6 );
        buttons.add( 6 ,r7 );
        buttons.add( 7 ,r8 );
        buttons.add( 8 ,r9 );
        buttons.add( 9 ,r10 );
        buttons.add( 10 ,r11 );
        buttons.add( 11 ,r12 );
        buttons.add( 12 ,r13 );
        buttons.add( 13 ,r14 );
        buttons.add( 14 ,r15 );
        buttons.add( 15 ,r16 );
        buttons.add( 16 ,r17 );
        buttons.add( 17 ,r18 );
        buttons.add( 18 ,r19 );
        buttons.add( 19 ,r20 );
        buttons.add( 20 ,r21 );
        buttons.add( 21 ,r22 );
        buttons.add( 22 ,r23 );
        buttons.add( 23 ,r24 );
        buttons.add( 24 ,r25 );
        buttons.add( 25 ,r26 );
        buttons.add( 26 ,r27 );
        buttons.add( 27 ,r28 );
        buttons.add( 28 ,r29 );
        buttons.add( 29 ,r30 );
        buttons.add( 30 ,r31 );
        buttons.add( 31 ,r32 );
        buttons.add( 32 ,r33 );
        buttons.add( 33 ,r34 );
        buttons.add( 34 ,r35 );
        buttons.add( 35 ,r36 );
        //</editor-fold>
        //initializing rooms based on the number of students inside every room if = 2 room is disabled with red background color
        for(int i=0; i<buttons.size(); i++){
            RoomDTO room = (RoomDTO)RoomFactory.getRoom(i);
            if(room.getNbOfStudents() == 2){buttons.get(i).setStyle("-fx-background-color: red");buttons.get(i).setDisable(true);}
        }
    }    
    
}
