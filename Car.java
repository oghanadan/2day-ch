public class Car{
    private int fuel = 10;

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
            if(fuel <= 120){
                int newFuel = this.fuel - fuel;
                setFuel(newFuel);
            }else{
                setFuel(120);
            }
        }else{
            setFuel(0);
        }


        System.out.println("New Fuel: " + this.fuel);
    }

    public void increaseFuel(int fuel){
        if(fuel > 0){
            int newFuel = this.fuel + fuel;
            setFuel(newFuel);
        }
    }

    public int getFuelByPercentage(int percentage) {
        if (percentage > 0) {
            int newFuel = (int)(this.fuel * ((float)percentage / 100));
            System.out.println("New Fuel: " + newFuel);
            return newFuel;
        }
        return 0;
    }

    public boolean isOutOfFuel(){
        return this.fuel == 0;
    }
    
}