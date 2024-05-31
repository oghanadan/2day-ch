import java.util.Arrays;
import java.util.Random;
import java.util.random.*;

public class Grid {

    private int N;
    private DefaultCell[][] gameBoard;

    public Grid(int N) {
        this.N = N;
        this.generateGrid(N);
    }

    public void generateGrid(int N){
        DefaultCell[][] board = new DefaultCell[N][N];
        // To fill Grid with Grey Cells
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                board[i][j]= new GreyCell();
            }
        }
        // To Randomly insert Green Cells
        Random rand = new Random();
        int greenCount = 0;
        double a = N*N*0.15;
        //Using percentage (15%) for the amount of green cells
        int greenAmount = (int)a;
        while (greenCount<greenAmount){
            //int x = (int)(Math.random()*N);
            //int j = (int)(Math.random()*N);
            int x = rand.nextInt(N);
            int j = rand.nextInt(N);
            if (board[x][j].getColor()==TileType.GREY){
                board[x][j] = new GreenCell();
                greenCount++;
            }
        }
        // To randomly insert Black Cells
        int blackCount = 0;
        //Using percentage (15%) for the amount of black cells, a is the same since both black and green have same percentage ratio.
        int blackAmount = (int)a;
        while (blackCount<blackAmount){
            int x = (int)(Math.random()*N);
            int j = (int)(Math.random()*N);
            if((x != 0 && j != 0) && (x != N-1 && j != 0)){
                if (board[x][j].getColor()== TileType.GREY){
                    board[x][j] = new BlackCell();
                    blackCount++;
                }
            }
        }
        // To print NxN Table, DOESNT WORK
        int index = 0;
        String[] forTable = new String[N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                    if (j==0 && index<N){
                        String b = board[i][j].getDisplayValue();
                        System.out.print(b);
                        forTable[index] = b + " "+ board[i][j].getDisplayValue();
                    }
                    else if (index<N) {forTable[index] = forTable + " "+board[i][j].getDisplayValue();} 
                index++;
            }
            System.out.println(forTable[i]);
        }       

        this.gameBoard = board;
    }

    public DefaultCell[][] getGameBoard(){
        return gameBoard;
    }
}
