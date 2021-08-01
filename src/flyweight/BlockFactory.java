package flyweight;

import java.util.HashMap;
import models.data.BlockDTO;


public class BlockFactory {
    private static final HashMap blockMap = new HashMap();
    
    public static BlockDTO getBlock(String name){
        BlockDTO block = (BlockDTO)blockMap.get(name);
        if(block == null){
            block = new BlockDTO(name);
            blockMap.put(name, block);
        }
        return block;
    }
}
