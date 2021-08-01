package models;

import models.data.Student;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import services.VerificationService;


public class DAOStudents {
    
    //get students from database and create an arraylist of students to be used in tableview 
    public ArrayList getStudents() throws SQLException{
        Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
        ArrayList<Student> students = null;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM `dorms-registration`.`students`");
            rs = pstmt.executeQuery();
            students = new ArrayList<>();
            while(rs.next()){
                students.add(new Student( rs.getString("fullName") ,rs.getString("bloodtype"),rs.getString("gender"),rs.getInt("idstudents"),rs.getString("email"),rs.getString("tel"), rs.getInt("room"), rs.getString("blockName"), rs.getInt("floorNb")));
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
        return students;
    }
    
    public Student getStudent(int id) throws SQLException{
        Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
        Student student = null;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM `dorms-registration`.`students` WHERE `idstudents` = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            
            if(rs.next()){
                student = new Student(rs.getString("fullName") ,rs.getString("bloodtype"),rs.getString("gender"),rs.getInt("idstudents"),rs.getString("email"),rs.getString("tel"), rs.getInt("room"), rs.getString("blockName"), rs.getInt("floorNb"));
            }
        }
        finally{
            DBUtil.close(conn, pstmt, rs);
        }
        return student;
    }
    public int updateStudent(String blockName, int floorNb,int id,String fullName,String bloodtype,int roomNb,String tel,String email) throws SQLException{
        Connection conn = null;
	PreparedStatement pstmt = null;
        int result =0;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("UPDATE `dorms-registration`.`students` SET `fullName` = ?,`bloodtype` = ? ,`room` = ?,`floorNb` = ? ,`blockName` = ? ,`email` = ? ,`tel` = ? WHERE `idstudents` = ?");
            pstmt.setString(1, fullName);pstmt.setString(2, bloodtype);pstmt.setInt(3,roomNb);pstmt.setInt(4, floorNb);pstmt.setString(5, blockName);pstmt.setString(6, email);pstmt.setString(7, tel);pstmt.setInt(8, id);
            
            result = pstmt.executeUpdate();
        }
        finally{
            DBUtil.close(conn, pstmt);
        }
        return result;
    }
    
    public int insertStudent(Student student) throws SQLException{
        Connection conn = null;
	PreparedStatement pstmt = null;
        int result =0;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("INSERT INTO `dorms-registration`.`students`(`idstudents`,`fullName`,`gender`,`bloodtype`,`room`,`floorNb`,`blockName`,`email`,`tel`) VALUES(?,?,?,?,?,?,?,?,?)");
            pstmt.setInt(1, student.getId());pstmt.setString(2, student.getName());pstmt.setString(3, student.getGender());pstmt.setString(4, student.getBloodType());pstmt.setInt(5, student.getRoom());pstmt.setInt(6, student.getFloor());pstmt.setString(7,student.getBlock());pstmt.setString(8, student.getEmail());pstmt.setString(9, student.getTel());
            
            result = pstmt.executeUpdate();
        }
        finally{
            DBUtil.close(conn, pstmt);
        }
        
        return result;
    }
    
    public int deleteStudent(int id) throws SQLException{
        Connection conn = null;
	PreparedStatement pstmt = null;
        int result =0;
        
        try{
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("DELETE FROM `dorms-registration`.`students` WHERE `idstudents` = ?");
            pstmt.setInt(1, id);
            
            result = pstmt.executeUpdate();
        }
        finally{
            DBUtil.close(conn, pstmt);
        }
        
        return result;
    }
    
    public int[] getIDs(){
        Connection db = null;
        Statement stmt = null;
        ResultSet rs = null;
        int[] ids = null;
        try {
            db = DBUtil.getConnection();
            stmt = db.createStatement();
            rs = stmt.executeQuery("SELECT `idstudents` FROM `dorms-registration`.`students` order by idstudents");
            int size = 0;
            if (rs.last()) {
              size = rs.getRow();
              rs.beforeFirst(); // not rs.first() because the rs.next() below will move on, missing the first element
            }
            ids = new int[size];
            for(int i=0; i<size; i++){
                rs.next();
                ids[i] = rs.getInt(1);
            }
        }
        catch (SQLException e) {
            System.out.println("failed to get data");
        }
        finally{
            DBUtil.close(db, stmt);
        }
        return ids;
    }
}
