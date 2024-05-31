public class Car{
    private int fuel = 120;

    //sets the fuel of the car and handles the invalid cases
    public void setFuel(int fuel){
        if(fuel > 0){
            if(fuel <= 120){
                this.fuel = fuel;
            }else{
                this.fuel = 120;
            }
        }else{
            this.fuel = 0;
        }
    }

    //returns the fuel of the car
    public int getFuel(){   
        return fuel;
    }

    //decreases the fuel of the car by a given amount 
    public void decreaseFuel(int fuel){
        if(fuel > 0){
            int newFuel = this.fuel - fuel;
            setFuel(newFuel);
        }else{
            setFuel(0);
        }


        System.out.println("New Fuel: " + this.fuel);
    }

    //increases the fuel of the car by a given amount
    public void increaseFuel(int fuel){
        if(fuel > 0){
            int newFuel = this.fuel + fuel;
            setFuel(newFuel);
        }
    }


    //returns a percentage of the current amount fuel in the car
    public int getFuelByPercentage(int percentage) {
        if (percentage > 0) {
            int newFuel = (int)(this.fuel * ((float)percentage / 100));
            System.out.println("New Fuel: " + newFuel);
            return newFuel;
        }
        return 0;
    }

    //returns true if the car is out of fuel
    public boolean isOutOfFuel(){
        return this.fuel == 0;
    }
    
}