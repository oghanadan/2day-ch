public class Die{
    private int lastRoll;

    //constructor
    public Die(){
        roll();
    }

    //assigns a random number between 1 and 6 to lastRoll
    public void roll(){
        lastRoll = (int)(Math.random()*6)+1;
    }

    //returns the value of lastRoll
    public int getLastRoll(){
        return lastRoll;
    }

    //returns the value of lastRoll and assigns a new random number to lastRoll
    public int rollAndGetNewValue(){
        roll();
        return lastRoll;
    }

    @Override
    public String toString(){
        return "Die: " + lastRoll;
    }
}