import java.awt.Color;
import javax.swing.JFrame;

// Different possible outcomes of a player movement
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
    private int turnsToMiss = 0;

    public Player(Color color, int row, int col){
        this.color = color;
        this.position = new int[2];
        setPosition(row, col);
        this.car = new Car();
    }

    // sets the position of the player on the game board
    public void setPosition(int row, int col){
        this.position[0] = row;
        this.position[1] = col;
    }

    // sets the row of the player on the game board
    public void setRow(int row){
        this.position[0] = row;
    }

    // sets the column of the player on the game board
    public void setCol(int col){
        this.position[1] = col;
    }
    

    // returns the color of the player
    public Color getColor(){
        return this.color;
    }

    // returns the String that represents the color of the player
    public String getColorName(){
        return this.color == Color.RED ? "RED" : "BLUE";
    }


    // returns the position of the player on the game board
    public int[] getPosition(){
        return this.position;
    }

    // returns the car instance associated with the player
    public Car getCar() {
        return car;
    }

    // returns whether the player is alive or not
    public boolean isAlive() {
        return isAlive;
    }

    // checks if the player is to miss a turn and decrements the number of turns to miss if true
    public boolean isToMissTurn() {
        if(turnsToMiss > 0){
            turnsToMiss--;
            return true;
        }
        return false;
    }

    // returns the number of turns the player is to miss
    public int getTurnsToMiss(){
        return turnsToMiss;
    }


    //handles the movement of the player on the game board
    public PlayerMovementOutcome movePlayer(DefaultCell[][] board, boolean landed){
        int gridSize = board.length;

        // Check if the player is out of fuel
        if(this.car.isOutOfFuel()){
            return PlayerMovementOutcome.OUT_OF_FUEL;
        }

        // Update the player's position
        updatePlayerPosition(gridSize);

        // Get the cell the player landed on
        DefaultCell currentCell = board[this.position[0]][this.position[1]];

        // if the current cell is the final cell of the movement
        if(!landed){
            if(currentCell.getColor() == TileType.GREY){
                //handles landingo on a grey cell
                onPlayerLandsOnGreyTile((GreyCell)currentCell);
            }
        }else{
            if(currentCell.getColor() == TileType.BLACK){
                System.out.println("Player " + this.color + " landed on black tile!");
                //handles landing on a black cell
                onPlayerLandsOnBlackTile(board);
                return PlayerMovementOutcome.RESET;
            }else if(currentCell.getColor() == TileType.GREEN){
                System.out.println("Player " + this.color + " landed on green tile!");
                //handles landing on a green cell
                onPlayerLandsOnGreenTile();
            }

            if(this.position[0] == 0 && this.position[1] == 0){
                // Check if the player has won the game
                System.out.println("Player " + this.color + " wins!");
                return PlayerMovementOutcome.WIN;
            }
        }

        //if nothing goes wrong, return success

        return PlayerMovementOutcome.SUCCESS;
    }

    public void updatePlayerPosition(int gridSize){
        // checks if player should go left or right depending on the current row and grid size (used to make it move in a zig zag way)
        boolean shouldMoveLeft = this.shouldMoveLeft(gridSize);
        if (shouldMoveLeft) {
            System.out.println(" - -Moving left - -");
            if (this.position[1] > 0) {
                System.out.println("from 2");
                setCol(this.position[1] - 1);
            } else if (this.position[0] > 0) {
                if(this.position[0] == 1){
                    setCol(gridSize - 1);
                    setRow(0);
                }else{
                    setCol(0);
                    setRow(this.position[0] - 1);
                }
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

    private boolean shouldMoveLeft(int gridSize){
        // returns the appropriate logic based on whether the grid size is even or odd
        if(gridSize % 2 == 0){
            System.out.println("GRID IS EVEN");
            return (this.position[0] % 2 == 0) && (this.position[0] != 0) && (this.position[0] != gridSize - 1);
            
            
        }else{
            System.out.println("GRID IS ODD");
            return (this.position[0] == 0) || (this.position[0] % 2 != 0);
        }
    }

    public void onPlayerLandsOnGreyTile(GreyCell landedOnCell){
        // decrease the fuel of the player based on the fuel consumption of the cell
        int fuelConsumption = landedOnCell.getFuelConsumption();
        this.car.decreaseFuel(fuelConsumption);
    }

    public void onPlayerLandsOnGreenTile(){
        // adds 50% of the current fuel to the player's car
        int fuelToBeAdded = this.car.getFuelByPercentage(50);
        this.car.increaseFuel(fuelToBeAdded);
    }

    public void onPlayerLandsOnBlackTile(DefaultCell[][] board){
        // reset the player's position to the starting position if player lands on a black cell
        resetPlayerPosition(board);
    }

    private void resetPlayerPosition(DefaultCell[][] board){
        // reset the player's position to the starting position
        this.position[0] = board.length - 1;
        this.position[1] = 0;
    }

    public void onOutOfFuel(DefaultCell[][] board, JFrame frame){
        // Display the out of fuel dialog along with the options
        OutOfFuelDialog outOfFuelDialog = new OutOfFuelDialog(frame,this,board);
        outOfFuelDialog.setVisible(true);
        
    }

    public void onRefuelChoice(int fuelRecharge, DefaultCell[][] board){
        // refuel the player's car based on the fuel recharge option chosen by the player
        System.out.println("Refueling with: " + fuelRecharge);
        if(fuelRecharge > 0){
            switch(fuelRecharge){
                case 1:
                    this.car.increaseFuel(20);
                    this.turnsToMiss = 1;
                    break;
                case 2:
                    this.car.increaseFuel(40);
                    this.turnsToMiss = 2;
                    break;
                case 3:
                    this.car.increaseFuel(60);
                    this.turnsToMiss = 3;
                    break;
                case 4:
                    this.car.increaseFuel(80);
                    this.turnsToMiss = 4;
                    break;
                case 5:
                    this.car.increaseFuel(100);
                    this.turnsToMiss = 5;
                    break;
                case 6:
                    this.car.increaseFuel(120);
                    this.turnsToMiss = 6;
                    break;
            }
        }else{
            // reset the player's position if the player chooses to go back to the starting position
            resetPlayerPosition(board);
            // refuel the player's car to a random value between 1 and 120
            this.car.setFuel((int)(Math.random()*120)+1);
        }
    }


}
