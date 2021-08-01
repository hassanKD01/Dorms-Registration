package models.data;


public class CurrentStudent {
    
    Student student;
    
    private static CurrentStudent currentStudent;
    private CurrentStudent(){ }
    public static CurrentStudent getCurrentStudent(){
        if(currentStudent == null){
            currentStudent = new CurrentStudent();
        }
        return currentStudent;
    }
    public void setStudent(Student student){
        currentStudent.student = student;
    }
    public Student getStudent(){
        return currentStudent.student;
    }
}
