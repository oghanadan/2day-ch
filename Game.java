import java.awt.Color;

public class Game{
    private Player playerRed;
    private Player playerBlue;
    private DefaultCell[][] board;
    private Die die = new Die();
    private int round = 1;
    private int size = 10;


    //constructor
    public Game(){
        setBoard();
        playerRed = new Player(Color.RED,size-1,0);
        playerBlue = new Player(Color.BLUE,size-1,0);
    }

    //assigns the generated board to the board property
    public void setBoard(){
        Grid grid = new Grid(size);
        this.board = grid.getGameBoard();
    }

    //returns the board 
    public DefaultCell[][] getBoard(){
        return board;
    }

    //returns the player with the red color
    public Player getPlayerRed(){
        return playerRed;
    }

    //returns the player with the blue color
    public Player getPlayerBlue(){
        return playerBlue;
    }

    //returns the die object
    public Die getDie(){
        return die;
    }

    //increments the current round by 1
    public void incrementRound(){
        round++;
    }

    //returns whose turn it is
    public Player getCurrentTurnPlayer(){
        boolean isRedturn = round % 2 == 0;
        return isRedturn ? playerBlue : playerRed;
    }

    //handles each players turn
    public boolean onTurnPlayed(){
        boolean canMove = true;
        Player currentPlayer = getCurrentTurnPlayer();

        if(!currentPlayer.isToMissTurn()){
            int dieRoll = die.rollAndGetNewValue();
        }else{
            System.out.println("Player " + currentPlayer.getColor() + " misses turn!");
            canMove = false;
        }
        

        return canMove;
    }


    //moves the player on the board
    public PlayerMovementOutcome movePlayer(Player player, boolean landed){
        PlayerMovementOutcome outcome = player.movePlayer(board,landed);
        return outcome;
    }




}