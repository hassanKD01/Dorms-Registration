package models.data;


public class RoomDTO {
    private final int roomNb;
    private int nbOfStudents;

    public RoomDTO(int roomNb) {
        this.roomNb = roomNb;
    }

    public RoomDTO(int roomNb, int nbOfStudents) {
        this.roomNb = roomNb;
        this.nbOfStudents = nbOfStudents;
    }

    public int getRoomNb() {
        return roomNb;
    }

    public int getNbOfStudents() {
        return nbOfStudents;
    }
    
    public void setNbOfStudents(int nbOfStudents) {
        this.nbOfStudents = nbOfStudents;
    }
}
