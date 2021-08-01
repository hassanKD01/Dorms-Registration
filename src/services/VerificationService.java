package services;

import java.sql.SQLException;
import models.DAODorms;
import models.DAOStudents;


public class VerificationService {
    
    private final DAOStudents studentsDao;
    private final DAODorms dormsDao;

    public VerificationService() {
        this.studentsDao = new DAOStudents();
        this.dormsDao = new DAODorms();
    }
    
    public boolean alreadyExists(int id) throws SQLException{
        int []ids = studentsDao.getIDs();
        int l = 0, r=ids.length-1,m;
        while(l <= r){
            
            int t = (r-l)/2;
            System.out.println(t+" test");
            m = l+t;
            if(ids[m] == id)
                return true;
            if(ids[m] < id)
                l = m+1;
            else
                r = m-1;
        }
        return false;
    }
    public boolean emailVerification(String email){
        return email.matches("^[\\w-\\.+]*[\\w-\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$");
    }
    public boolean telVerification(String tel){
        if(tel.length() != 8 )return false;
        else{
            String prefix = tel.substring(0, 2);
            if(prefix.equals("03") || prefix.equals("70") || prefix.equals("71") || prefix.equals("78") || prefix.equals("79") || prefix.equals("81"))
                return true;
        }
        return false;
    }
    public boolean verifyInput(String gender,String block,int floor,int room){
        if(gender.equals("Female")){
            if(room > 36 || floor >3 || !block.matches("[A-E]")){
                return false;
            }
        }
        else if(gender.equals("Male")){
            if(room > 36 || floor >3 || !block.matches("[F-G]")){
                return false;
            }
        }
        return true;
    }
    public boolean roomIsFull(int roomNb, int floorNb, String blockName) throws SQLException{
        int selectedRoom = dormsDao.getNbOfStudents(roomNb,floorNb,blockName);
        
        if(selectedRoom == 2) return true;
        
        return false;
    }
    public boolean roomNotChanged(int oldRoom,int oldFloor,String oldBlock,int newRoom,int newFloor,String newBlock){
        if(newRoom == oldRoom && newFloor == oldFloor && newBlock.equals(oldBlock))
            return true;
        return false;
    }
}
