import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameUi {
    Game game = new Game();

    // Display the game UI
    public void main() {
        createGameBoardUI();
    }

    // Create the game board UI
    public void createGameBoardUI(){
        DefaultCell[][] board = this.game.getBoard();
        int gridSize = board.length;

        // Setup
        GameFrame frame = new GameFrame();
        JPanel mainPanel = new JPanel();

        // Add cars
        JPanel redCarPanel = new JPanel();
        JPanel blueCarPanel = new JPanel();
        CarLabel labelRedCar = new CarLabel(new ImageIcon("images/redCar.png"));
        CarLabel labelBlueCar = new CarLabel(new ImageIcon("images/blueCar.png"));
        redCarPanel.add(labelRedCar);
        blueCarPanel.add(labelBlueCar);

        // Main panel with BorderLayout
        mainPanel.setLayout(new BorderLayout());


        // Grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));


        // Add buttons to grid panel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                DefaultCell tile = board[i][j];
                JButton button = new JButton(tile.getDisplayValue());
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.setBorderPainted(false);
                //button.setBackground(tile.getTileUIColor());
                button.setForeground(Color.RED);
                button.addActionListener(
                        new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                JOptionPane.showMessageDialog(
                                        frame,
                                        ((JButton) e.getSource()).getText() + " clicked!"
                                );
                            }
                        }
                );
                gridPanel.add(button);
            }
        }


        // Button panel for "Roll Dice" button
        JPanel buttonPanel = new JPanel();
        JButton rollDiceButton = new JButton();
        // Content of the button
        rollDiceButton.setText("Roll Dice");
        rollDiceButton.setFocusable(false);
        // Icon of the button
        int diceIconSize = 25;
        ImageIcon diceIcon = new ImageIcon("images/dice.png");
        Image diceImage = diceIcon.getImage();
        Image newDiceImage = diceImage.getScaledInstance(diceIconSize, diceIconSize, diceImage.SCALE_SMOOTH);
        ImageIcon smallDiceIcon = new ImageIcon(newDiceImage);
        // Icon content alignment
        rollDiceButton.setIcon(smallDiceIcon);
        rollDiceButton.setHorizontalTextPosition(JButton.CENTER);
        rollDiceButton.setVerticalTextPosition(JButton.BOTTOM);
        // Roll dice button action
        rollDiceButton.addActionListener(e -> {
            int diceRoll = (int) (Math.random() * 6) + 1;
            JOptionPane.showMessageDialog(frame, "You rolled a " + diceRoll);
        });
        buttonPanel.add(rollDiceButton);


        // Add grid panel and button panel to main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add cars to main panel
        mainPanel.add(redCarPanel, BorderLayout.WEST);
        mainPanel.add(blueCarPanel, BorderLayout.EAST);


        // Add main panel to frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}