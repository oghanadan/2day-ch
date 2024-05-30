import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameUi {
    Game game = new Game();
    JFrame frame = new JFrame("Game UI");
    JPanel mainPanel = new JPanel();

    public void main() {
        createGameBoardUI();
    }

    public void createGameBoardUI(){
        DefaultCell[][] board = this.game.getBoard();
        int gridSize = board.length;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 720);

        // Main panel with BorderLayout
        mainPanel.setLayout(new BorderLayout());

        // Grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

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
        JButton rollDiceButton = new JButton("Roll Dice");
        rollDiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = (int) (Math.random() * 6) + 1;
                JOptionPane.showMessageDialog(frame, "You rolled a " + diceRoll);
            }
        });
        buttonPanel.add(rollDiceButton);

        // Add grid panel and button panel to main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }
}