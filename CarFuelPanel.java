import javax.swing.*;

public class CarFuelPanel extends JPanel{
    CarFuelPanel(CarLabel labelCar, JProgressBar fuelBar){
        // Add the car label and fuel bar to the panel
        this.add(labelCar);
        this.add(fuelBar);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}