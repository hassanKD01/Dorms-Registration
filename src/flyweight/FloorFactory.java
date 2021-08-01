package flyweight;

import java.util.HashMap;
import models.data.FloorDTO;


public class FloorFactory {
    private static final HashMap floorMap = new HashMap();
    
    public static FloorDTO getFloor(int number){
        FloorDTO block = (FloorDTO)floorMap.get(number);
        if(block == null){
            block = new FloorDTO(number);
            floorMap.put(number, block);
        }
        return block;
    }
}
