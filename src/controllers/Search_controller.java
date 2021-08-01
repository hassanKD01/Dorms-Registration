package controllers;

import services.SearchService;
import models.data.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import models.DAOStudents;


public class Search_controller implements Initializable {

    private final SearchService searchService ;
    private final DAOStudents studentsDao;
    
    @FXML TableView students;
    //assign every column with the expected type
    @FXML TableColumn<Student,String> name;
    @FXML TableColumn<Student,Integer> id;
    @FXML TableColumn<Student,String> block;
    @FXML TableColumn<Student,String> room;
    @FXML TableColumn<Student,String> floor;
    @FXML TableColumn<Student,String> tel;
    @FXML TableColumn<Student,String> email;
    @FXML TableColumn<Student,String> bloodType;
    
    @FXML TextField searchId;

    public Search_controller() {
        this.searchService =  new SearchService();
        this.studentsDao = new DAOStudents();
    }
    
    //get id from user get student from database and set tableview content to the returned student
    public void search(KeyEvent event) throws SQLException{
        String studentName = searchId.getText().toLowerCase();
        ObservableList dbData = FXCollections.observableArrayList(searchService.searchStudent(studentName));
        students.setItems(dbData);
    }
    
    public void mainPageButton(ActionEvent event) throws IOException {
        FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/MainpageFXML.fxml"));
        Parent root=(Parent) loader.load();
        Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.setTitle("Main page");
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
     public void Remove_function(ActionEvent event) throws IOException{
         FXMLLoader loader=new FXMLLoader(getClass().getResource("/views/Remove_fxml.fxml"));
         Parent root=(Parent) loader.load();
         Stage stage=(Stage) ((Node)event.getSource()).getScene().getWindow();
         stage.setScene(new Scene(root));
         stage.setTitle("Update registration page");
         stage.show();      
     }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //set the variable name corresponding to evey column to look for in Leaf(student) objects
        ObservableList dbData = null;
        try {
            searchService.students = studentsDao.getStudents();
            dbData = FXCollections.observableArrayList(searchService.students);
        } catch (SQLException ex) {
            Logger.getLogger(Search_controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        bloodType.setCellValueFactory(new PropertyValueFactory<>("bloodType"));
        room.setCellValueFactory(new PropertyValueFactory<>("room"));
        floor.setCellValueFactory(new PropertyValueFactory<>("floor"));
        block.setCellValueFactory(new PropertyValueFactory<>("block"));
        email.setCellValueFactory(new PropertyValueFactory <> ("email"));
        tel.setCellValueFactory(new PropertyValueFactory<>("tel"));
        students.setItems(dbData);
    }    
    
}
