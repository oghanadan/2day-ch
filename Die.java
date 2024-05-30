public class Die{
    private int lastRoll;

    public Die(){
        roll();
    }

    public void roll(){
        lastRoll = (int)(Math.random()*6)+1;
    }

    public int getLastRoll(){
        return lastRoll;
    }

    public int rollAndGetNewValue(){
        roll();
        return lastRoll;
    }

    @Override
    public String toString(){
        return "Die: " + lastRoll;
    }
}