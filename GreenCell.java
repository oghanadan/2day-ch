import java.awt.Color;

public class GreenCell implements DefaultCell{
    //The displayed color
    private String value = "G";
    //The type of the tile, for the functions
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

    public Color getUIColor(){
        return Color.GREEN;
    }
}   
