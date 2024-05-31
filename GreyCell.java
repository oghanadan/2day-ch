import java.awt.Color;


public class GreyCell implements DefaultCell {
    //The random (from1-3) displayed number
    private int fuelConsumption = (int)(Math.random()*3)+1;
    //The type of the tile, for the functions
    private static final TileType type = TileType.GREY;

    public TileType getColor(){
        return type;
    }

    public String getDisplayValue(){
        return String.valueOf(fuelConsumption);
    }

    public int getFuelConsumption(){
        return fuelConsumption;
    }

    public GreyCell(){
        getDisplayValue();
    }

    public Color getUIColor(){
        return Color.GRAY;
    }
}
