package controllers;



import models.data.Student;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.DAOStudents;
import services.AlertServices;
import services.VerificationService;


public class Update_controller implements Initializable {
    
    private final DAOStudents studentsDao;
    private final VerificationService service;
    private final AlertServices alerts;

    public Update_controller() {
        this.studentsDao = new DAOStudents();
        this.service = new VerificationService();
        this.alerts = new AlertServices();
    }
    
    Student student = null;
    
    // <editor-fold defaultstate="collapsed" desc="FXML input references">
    @FXML TextField name;
    @FXML
    TextField bloodType;
    @FXML
    TextField id;
    @FXML
    TextField tel;
    @FXML
    TextField email;
    @FXML TextField room;
    @FXML TextField floor;
    @FXML TextField block;
    @FXML VBox vbox;
    @FXML Button buton;
     // </editor-fold>
    
    public void mainPageButton(ActionEvent event) throws IOException {
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Main page");
            stage.show();
    }
    
    /*get student info on button click and set it to the corresponding textfield enable update button,
    ask user if he wants to search for student in case of a false id*/
    public void getInfo(ActionEvent event) throws SQLException, IOException{
        
            int intId = Integer.parseInt(id.getText());
            student = studentsDao.getStudent(intId);
            //to check if the inserted id already exists in the database (getStudent returns null if the id doesn't exist)
            if(student == null){
                id.setText("");
                Optional<ButtonType> result = alerts.confirmationAlert("Error", "Wrong ID", "The inserted ID doesn't belong to any student.\nWould you like to go to search page?");
                if(result.get() == ButtonType.OK){
                     //ok button is pressed
                     FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
                    Parent root=(Parent) loader.load();
                    Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
                    stage.setScene(new Scene(root));
                    stage.setTitle("Search page");
                    stage.show();
                }
                return;
            }
                    
        name.setText(student.getName());email.setText(student.getEmail());tel.setText(student.getTel());bloodType.setText(student.getBloodType());room.setText(""+student.getRoom());floor.setText(""+student.getFloor());block.setText(student.getBlock());
            
        vbox.setVisible(true);
        buton.setDisable(false);
        event.consume();
    }
    
    //get updated values and send to databse alert if update fails
    public void update(ActionEvent event) throws SQLException{
        int roomNum =Integer.parseInt(room.getText());
        int floorNum = Integer.parseInt(floor.getText());
        int updated;
        
        //if the room wasnt changed we do not need to check if the room is full
        if(service.roomNotChanged(student.getRoom(),student.getFloor(),student.getBlock(),roomNum, floorNum , block.getText().toUpperCase()))
            updated = studentsDao.updateStudent(block.getText().toUpperCase(), floorNum, Integer.parseInt(id.getText()), name.getText(), bloodType.getText(), roomNum, tel.getText(), email.getText());
        
        else{
    //verify if the chosen room is appropriate to the students gender (males are allowed in blocks F,G females:A->E) 
            if(!service.verifyInput(student.getGender(),block.getText().toUpperCase() , floorNum ,roomNum  )){
                alerts.alert(Alert.AlertType.ERROR, "Error", "Out of range", "Room number out of range");
                return;
            }
        
            if(service.roomIsFull(roomNum, floorNum , block.getText().toUpperCase())){
                alerts.alert(Alert.AlertType.ERROR, "Error", "Full room", "The inserted room is full");
                 return;
            }
            else
                updated = studentsDao.updateStudent(block.getText().toUpperCase(), floorNum, Integer.parseInt(id.getText()), name.getText(), bloodType.getText(), roomNum, tel.getText(), email.getText());
        }
        
        if(updated == 0){
            alerts.alert(Alert.AlertType.ERROR, "Error", "Update failed", "Something went wrong while updating please check the input and try again");
            event.consume();
        }
        
        else{
            alerts.alert(Alert.AlertType.INFORMATION, "Updated", "Update successful", "Student information updated successfully!");
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
    
    public void toSearchPage(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Search.fxml"));
            Parent root=(Parent) loader.load();
            Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setTitle("Search student page");
            stage.show();
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
