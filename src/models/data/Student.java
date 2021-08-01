

package models.data;


public class Student{
   
    
    private String bloodType;
    private int id;
    private String gender; 
    private String email;  
    private String tel;
    private String name;
    private int room;
    private String block;
    private int floor;
    
    public Student (String fullname,String bloodType,String gender, int id,String email,String tel){
        this.bloodType = bloodType;
        this.gender = gender;
        this.id =  id;
        this.name = fullname;
        this.email = email;
        this.tel = tel;
    }
    public Student (String fullname,String bloodType,String gender, int id,String email,String tel,int room, String block, int floor){
        this.bloodType = bloodType;
        this.gender = gender;
        this.id =  id;
        this.name = fullname;
        this.email = email;
        this.tel = tel;
        this.room = room;
        this.block = block;
        this.floor = floor;
    }
    
    //getters and setters
    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}
    public String getTel() {return tel;}
    public void setTel(String tel) {this.tel = tel;}
    public String getGender(){return gender;}
    public String getBloodType(){return bloodType;}
    public int getId(){return id;}
    public String getName(){return name;}
    public void setName(String newName){ name = newName;}
    public void setGender(String newGender){ gender = newGender;}
    public void setBloodType(String newBloodType){ bloodType = newBloodType;}
    public void setId(int newId){ id = newId;}
    public int getRoom() {return room;}
    public void setRoom(int room) {this.room = room;}
    public String getBlock() {return block;}
    public void setBlock(String block) {this.block = block;}
    public int getFloor(){return floor;}
    public void setFloor(int floor) {this.floor = floor;}

}
