enum PlayerMovementOutcome {
    SUCCESS,
    OUT_OF_FUEL,
    RESET,
    WIN,
}

public class Player{
    private Color color;
    private [int][int] position; // [row][col]
    private Car car;
    private boolean isAlive = true;

    public Player(Color color, int row, int col){
        this.color = color;
        this.position = new int[2];
        setPosition(row, col);
        this.car = new Car();
    }


    public void setPosition(int row, int col){
        this.position[0] = row;
        this.position[1] = col;
    }

    public void setPosition(int[] position){
        this.position = position;
    }

    public int[] getPosition(){
        return this.position;
    }

    public Car getCar() {
        return car;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public PlayerMovementOutcome movePlayer(int dieRoll, DefaultCell[][] board){
        int gridSize = board.length;
        for (int i = 0; i < dieRoll; i++) {
            boolean shouldMoveLeft = player.row % 2 != 0;
            if (shouldMoveLeft) {
                if (player > 0) {
                    player.col--;
                } else if (player.row > 0) {
                    player.col = 0;
                    player.row--;
                }
            } else {
                if (player.col < gridSize - 1) {
                    player.col++;
                } else if (player.row > 0) {
                    player.col = gridSize - 1;
                    player.row--;
                }
            }
        }
    }

}