public class Game{
    private Player playerRed;
    private Player playerBlue;
    private DefaultCell[][] board;
    private Die die = new Die();
    private int round = 1;



    public Game(){
        playerRed = new Player("Red");
        playerBlue = new Player("Blue");
        setBoard();
    }

    public void setBoard(){
        Grid grid = new Grid(10);
        this.board = grid.getGameBoard();
    }

    public void startGame(){
        while(playerRed.isAlive() && playerBlue.isAlive()){
            int dieRoll = die.rollAndGetNewValue();
            boolean isRedturn = round % 2 != 0;
            
        }
    }


}