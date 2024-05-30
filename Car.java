public class Car{
    private int fuel = 120;

    public void setFuel(int fuel){
        if(fuel > 0){
            this.fuel = fuel;
        }else{
            this.fuel = 0;
        }
    }

    public int getFuel(){
        return fuel;
    }

    public void decreaseFuel(int fuel){
        if(fuel > 0){
            int newFuel = this.fuel - fuel;
            setFuel(newFuel);
        }
    }

    public void increaseFuel(int fuel){
        if(fuel > 0){
            int newFuel = this.fuel + fuel;
            setFuel(newFuel);
        }
    }

    public int getFuelByPercentage(int percentage){
        if(percentage > 0){
            int newFuel = (int)(this.fuel * (percentage/100));
            return newFuel;
        }
        return 0;
    }

    public boolean isOutOfFuel(){
        return this.fuel == 0;
    }
    
}