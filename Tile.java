
import java.awt.Color;
import java.util.Random;


enum TileColor {
  GREY,
  GREEN,
  BLACK
}

enum TileType {
  START,
  END,
  NORMAL
}

public class Tile{
    private TileColor tileColor;
    private int fuelCost = 0;
    private TileType type = TileType.NORMAL;

    Random rand = new Random();

    public Tile(TileColor color, int fuelCost){
        setTileColor(color);
        setFuelCost(fuelCost);
    }

    public Tile(TileColor color){
        setTileColor(color);
        setRandomFuelCost();
    }

    public Tile(TileColor color, TileType type){
        setTileColor(color);
        setRandomFuelCost();
        setTileType(type);
    }

    public Tile(){
        setRandomColor();
        setRandomFuelCost();
    }

    public void setTileColor(TileColor color){
        this.tileColor = color;
    }

    public void setFuelCost(int fuelCost){
        if(tileColor == TileColor.GREY){
            if(fuelCost > 0 && fuelCost < 4){
                this.fuelCost = fuelCost;
            }else{
                throw new IllegalArgumentException("Fuel cost must be between 1 and 3");
            }
        }else{
            throw new IllegalArgumentException("Fuel cost can only be set for grey tiles");
        }
    }

    public void setTileType(TileType type){
        this.type = type;
    }

    private void setRandomFuelCost(){
        if(tileColor == TileColor.GREY){
            this.fuelCost = rand.nextInt((2) + 1) + 1;
        }
    }

    private void setRandomColor(){
        double randomTileChance = rand.nextDouble();
        if(randomTileChance < 0.75){
            this.tileColor = TileColor.GREY;
        }else if(randomTileChance < 0.9){
            this.tileColor = TileColor.GREEN;
        }else{ 
            this.tileColor = TileColor.BLACK;
        }
    }

    public TileColor getTileColor(){
        return tileColor;
    }

    public int getFuelCost(){
        return fuelCost;
    }

    public TileType getTileType(){
        return type;
    }

    public Color getTileUIColor(){
        switch(tileColor){
            case GREY:
                return Color.LIGHT_GRAY;
            case GREEN:
                return Color.GREEN;
            case BLACK:
                return Color.BLACK;
            default:
                return Color.LIGHT_GRAY;
        }
    }

    



}