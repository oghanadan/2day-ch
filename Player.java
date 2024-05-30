import java.awt.Color;
enum PlayerMovementOutcome {
    SUCCESS,
    OUT_OF_FUEL,
    RESET,
    WIN,
}

public class Player{
    private Color color;
    private int[] position; // [row, col]
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

    public void setRow(int row){
        this.position[0] = row;
    }

    public void setCol(int col){
        this.position[1] = col;
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
            DefaultCell currentCell = board[this.position[0]][this.position[1]];
            boolean shouldMoveLeft = this.position[0] % 2 != 0;
            if (shouldMoveLeft) {
                if (this.position[1] > 0) {
                    setCol(this.position[1] - 1);
                } else if (this.position[0] > 0) {
                    setCol(0);
                    setRow(this.position[0] - 1);
                }
            } else {
                if (this.position[1] < gridSize - 1) {
                    setCol(this.position[1] + 1);
                } else if (this.position[0] > 0) {
                    setCol(gridSize - 1);
                    setRow(this.position[0] - 1);
                }
            }
        }

        return PlayerMovementOutcome.SUCCESS;
    }

}