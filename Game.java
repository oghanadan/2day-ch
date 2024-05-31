import java.awt.Color;

public class Game{
    private Player playerRed;
    private Player playerBlue;
    private DefaultCell[][] board;
    private Die die = new Die();
    private int round = 1;
    private int size = 5;


    public Game(){
        setBoard();
        playerRed = new Player(Color.RED,size-1,0);
        playerBlue = new Player(Color.BLUE,size-1,0);
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

    public Die getDie(){
        return die;
    }

    public void incrementRound(){
        round++;
    }

    public Player getCurrentTurnPlayer(){
        boolean isRedturn = round % 2 != 0;
        return isRedturn ? playerBlue : playerRed;
    }

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


    public PlayerMovementOutcome movePlayer(Player player, boolean landed){
        PlayerMovementOutcome outcome = player.movePlayer(board,landed);
        return outcome;
    }




}