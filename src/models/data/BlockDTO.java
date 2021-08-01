package models.data;


public class BlockDTO {
    private final String blockName;
    private String isFull;

    public BlockDTO(String blockName) {
        this.blockName = blockName;
    }

    public BlockDTO(String blockName, String isFull) {
        this.blockName = blockName;
        this.isFull = isFull;
    }

    public String getBlockName() {
        return blockName;
    }

    public String isFull() {
        return isFull;
    }
    
    public void setIsFull(String isFull) {
        this.isFull = isFull;
    }
}
