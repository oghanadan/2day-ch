import java.awt.Color;

public class BlackCell implements DefaultCell{
    //The displayed color
    private String value = "B";
    //The type of the tile, for the functions
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

    public Color getUIColor(){
        return Color.BLACK;
    }
}   