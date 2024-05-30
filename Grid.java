import java.util.Arrays;
import java.util.Random;
import java.util.random.*;

public class Grid {
    public static void main(String[] args) {
        int N = 10;
        DefaultCell[][] position = new DefaultCell[N][N];
        // To fill Grid with Grey Cells
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                position[i][j]= new GreyCell();
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
            if (position[x][j].getColor()=="Grey"){
                position[x][j] = new GreenCell();
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
            if (position[x][j].getColor()=="Grey"){
                position[x][j] = new BlackCell();
                blackCount++;
            }
        }
        // To print NxN Table, DOESNT WORK
        int index = 0;
        String[] forTable = new String[N];
        for (int i=0; i<N; i++){
            for (int j=0; j<N; j++){
                    if (j==0 && index<N){
                        String b = position[i][j].getDisplayValue();
                        System.out.print(b);
                        forTable[index] = b + " "+ position[i][j].getDisplayValue();
                    }
                    else if (index<N) {forTable[index] = forTable + " "+position[i][j].getDisplayValue();} 
                index++;
            }
            System.out.println(forTable[i]);
        }       
    }
}
