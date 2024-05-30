import java.awt.Color;

public class Game{
    private Player playerRed;
    private Player playerBlue;
    private DefaultCell[][] board;
    private Die die = new Die();
    private int round = 1;



    public Game(){
        playerRed = new Player(Color.RED,0,0);
        playerBlue = new Player(Color.BLUE,0,0);
        setBoard();
    }

    public void setBoard(){
        Grid grid = new Grid(10);
        this.board = grid.getGameBoard();
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
    }


}