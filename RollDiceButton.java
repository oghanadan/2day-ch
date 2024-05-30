import javax.swing.*;
import java.awt.*;

public class RollDiceButton extends JButton {
    RollDiceButton(){
        // Content of the button
        this.setText("Roll Dice");
        this.setFocusable(false);

        // Icon of the button
        int diceIconSize = 25;
        ImageIcon diceIcon = new ImageIcon("images/dice.png");
        Image diceImage = diceIcon.getImage();
        Image newDiceImage = diceImage.getScaledInstance(diceIconSize, diceIconSize, diceImage.SCALE_SMOOTH);
        ImageIcon smallDiceIcon = new ImageIcon(newDiceImage);

        // Icon content alignment
        this.setIcon(smallDiceIcon);
        this.setHorizontalTextPosition(JButton.CENTER);
        this.setVerticalTextPosition(JButton.BOTTOM);
    }
}