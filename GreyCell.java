public class GreyCell implements DefaultCell {
    private int fuelConsumption = (int)(Math.random()*3)+1;

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
}
