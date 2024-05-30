public class GreenCell implements DefaultCell{
    private String value = "G";
    private static final TileType type = TileType.GREEN;

    public TileType getColor(){
        return type;
    }
    
    public String getDisplayValue(){
        return value;
    }
    public GreenCell(){
        getDisplayValue();
    }
}   
