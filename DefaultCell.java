import java.awt.Color;

enum TileType {
  GREY,
  GREEN,
  BLACK
}

interface DefaultCell{
    public TileType getColor();
    public String getDisplayValue();
    public Color getUIColor();
}


