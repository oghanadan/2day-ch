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
    private int turnsToMiss = 0;

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
    

    public Color getColor(){
        return this.color;
    }

    public String getColorName(){
        return this.color == Color.RED ? "RED" : "BLUE";
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

    public boolean isToMissTurn() {
        if(turnsToMiss > 0){
            turnsToMiss--;
            return true;
        }
        return false;
    }

    public PlayerMovementOutcome movePlayer(DefaultCell[][] board, boolean landed){
        int gridSize = board.length;

        if(this.car.isOutOfFuel()){
            return PlayerMovementOutcome.OUT_OF_FUEL;
        }

        updatePlayerPosition(gridSize);

        DefaultCell currentCell = board[this.position[0]][this.position[1]];

        if(!landed){
            if(currentCell.getColor() == TileType.GREY){
                onPlayerLandsOnGreyTile((GreyCell)currentCell);
            }
        }else{
            if(currentCell.getColor() == TileType.BLACK){
                System.out.println("Player " + this.color + " landed on black tile!");
                onPlayerLandsOnBlackTile(board);
                return PlayerMovementOutcome.RESET;
            }else if(currentCell.getColor() == TileType.GREEN){
                System.out.println("Player " + this.color + " landed on green tile!");
                onPlayerLandsOnGreenTile();
            }

            if(this.position[0] == 0 && this.position[1] == 0){
                System.out.println("Player " + this.color + " wins!");
                return PlayerMovementOutcome.WIN;
            }
        }


        if(this.position[0] == 0 && this.position[1] == 0){
            return PlayerMovementOutcome.WIN;
        }


        return PlayerMovementOutcome.SUCCESS;
    }

    public void updatePlayerPosition(int gridSize){
        boolean shouldMoveLeft = (this.position[0] == 0) || (this.position[0] % 2 != 0);
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

    public void onPlayerLandsOnGreyTile(GreyCell landedOnCell){
        int fuelConsumption = landedOnCell.getFuelConsumption();
        this.car.decreaseFuel(fuelConsumption);
    }

    public void onPlayerLandsOnGreenTile(){
        int fuelToBeAdded = this.car.getFuelByPercentage(50);
        System.out.println("Adding fuel: " + fuelToBeAdded);
        this.car.increaseFuel(fuelToBeAdded);
    }

    public void onPlayerLandsOnBlackTile(DefaultCell[][] board){
        resetPlayerPosition(board);
    }

    private void resetPlayerPosition(DefaultCell[][] board){
        this.position[0] = board.length - 1;
        this.position[1] = 0;
    }

    public void onRefuelChoice(int fuelRecharge, DefaultCell[][] board){
        if(fuelRecharge > 0){
            int turnsToMiss = 0;
            switch(fuelRecharge){
                case 20:
                    this.car.increaseFuel(20);
                    turnsToMiss = 1;
                    break;
                case 40:
                    this.car.increaseFuel(40);
                    turnsToMiss = 2;
                    break;
                case 60:
                    this.car.increaseFuel(60);
                    turnsToMiss = 3;
                    break;
                case 80:
                    this.car.increaseFuel(80);
                    turnsToMiss = 4;
                    break;
                case 100:
                    this.car.increaseFuel(100);
                    turnsToMiss = 5;
                    break;
                case 120:
                    this.car.increaseFuel(120);
                    turnsToMiss = 6;
                    break;
            }

            this.turnsToMiss = turnsToMiss;
        }else{
            resetPlayerPosition(board);
        }
    }


}