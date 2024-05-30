public class BlackCell implements DefaultCell{
    private String value = "B";
    private static final TileType type = TileType.BLACK;

    public TileType getColor(){
        return type;
    }
    
    public String getDisplayValue(){
        return value;
    }
    
    public BlackCell(){
        getDisplayValue();
    }
}   