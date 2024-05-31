import java.awt.Color;
// For the different types of tiles
enum TileType {
  GREY,
  GREEN,
  BLACK
}
//Basic interface for the cells
interface DefaultCell{
    public TileType getColor();
    public String getDisplayValue();
    public Color getUIColor();
}


