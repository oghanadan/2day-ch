import java.awt.Color;

public class Game{
    private Player playerRed;
    private Player playerBlue;
    private DefaultCell[][] board;
    private Die die = new Die();
    private int round = 1;
    private int size = 5;


    public Game(){
        playerRed = new Player(Color.RED,0,0);
        playerBlue = new Player(Color.BLUE,0,0);
        setBoard();
        //startGame();
    }

    public void setBoard(){
        Grid grid = new Grid(size);
        this.board = grid.getGameBoard();
    }

    public DefaultCell[][] getBoard(){
        return board;
    }

    public Player getPlayerRed(){
        return playerRed;
    }

    public Player getPlayerBlue(){
        return playerBlue;
    }

    public void startGame(){
        while(playerRed.isAlive() && playerBlue.isAlive()){
            boolean isRedturn = round % 2 != 0;
            Player currentPlayer = isRedturn ? playerRed : playerBlue;
            playTurn(currentPlayer);

        }
    }

    private void playTurn(Player player){
        int dieRoll = die.rollAndGetNewValue();
        PlayerMovementOutcome movementOutcome = player.movePlayer(dieRoll, board);


    }


}