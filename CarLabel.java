import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.*;

public class CarLabel extends JLabel {
    CarLabel(ImageIcon icon){
        int size = 100;
        // Resize the icon
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(size, size, Image.SCALE_SMOOTH);
        icon = new ImageIcon(newImage);

        // Set the icon
        this.setIcon(icon);
        // Make the background transparent
        this.setOpaque(false);
        // Place the icon at the center
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        // Set the size of the label
        this.setPreferredSize(new Dimension(size, size));
    }
}