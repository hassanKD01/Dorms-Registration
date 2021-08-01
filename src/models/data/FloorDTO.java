package models.data;


public class FloorDTO {
    private final int floorNb;
    private String isFull;

    public FloorDTO(int floorNb) {
        this.floorNb = floorNb;
    }

    public FloorDTO(int floorNb, String isFull) {
        this.floorNb = floorNb;
        this.isFull = isFull;
    }

    public int getFloorNb() {
        return floorNb;
    }

    public String isFull() {
        return isFull;
    }
    
    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }
}
