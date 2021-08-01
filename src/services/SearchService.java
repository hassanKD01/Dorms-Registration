
package services;

import models.data.Student;
import java.sql.SQLException;
import java.util.ArrayList;


public class SearchService {
    public ArrayList<Student>students;
    
    //get student with the inserted id and return instance in arraylist to be used in tableview
    public ArrayList searchStudent(String name) throws SQLException{
        
        ArrayList<Student> searchedStudent =  new ArrayList();
        for(int i=0;i<students.size();i++){
            if(students.get(i).getName().toLowerCase().contains(name)) searchedStudent.add(students.get(i));
        }
        return searchedStudent;
    }
}
