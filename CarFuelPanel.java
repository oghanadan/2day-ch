import javax.swing.*;

public class CarFuelPanel extends JPanel{
    CarFuelPanel(CarLabel labelCar, JProgressBar fuelBar){
        // Add the car label and fuel bar to the panel
        this.add(labelCar);
        this.add(fuelBar);
        setOpaque(false);
        // Change the margin between the components
        this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }
}