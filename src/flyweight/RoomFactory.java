package flyweight;

import java.util.HashMap;
import models.data.RoomDTO;


public class RoomFactory {
    private static final HashMap roomMap = new HashMap();
    
    public static RoomDTO getRoom(int number){
        RoomDTO block = (RoomDTO)roomMap.get(number);
        if(block == null){
            block = new RoomDTO(number);
            roomMap.put(number, block);
        }
        return block;
    }
}
