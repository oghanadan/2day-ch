import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameUi {
    private Game game;
    private int dieRoll = 0;
    private GameFrame frame;
    private JPanel mainPanel;
    private JPanel gridPanel;
    private JPanel buttonPanel;
    private CarLabel labelRedCar;
    private CarLabel labelBlueCar;
    private JButton dieRollButton;
    private JProgressBar redCarFuelBar;
    private JProgressBar blueCarFuelBar;

    public GameUi() {
        this.game = new Game();
    }

    // Display the game UI
    public void main() {
        createGameBoardUI();
    }

    public void setDieRoll(int dieRoll) {
        this.dieRoll = dieRoll;
        updateDieRollUI();
    }

    // Create the game board UI
    public void createGameBoardUI() {
        DefaultCell[][] board = this.game.getBoard();
        int gridSize = board.length;

        // Setup frame and main panel
        frame = new GameFrame();
        mainPanel = new JPanel();

        // Car labels
        labelRedCar = new CarLabel(new ImageIcon("images/redCar.png"));
        labelBlueCar = new CarLabel(new ImageIcon("images/blueCar.png"));

        // Fuel bars
        redCarFuelBar = new JProgressBar(0, 100);
        blueCarFuelBar = new JProgressBar(0, 100);
        redCarFuelBar.setValue(game.getPlayerRed().getCar().getFuel());
        blueCarFuelBar.setValue(game.getPlayerBlue().getCar().getFuel());
        redCarFuelBar.setStringPainted(true);
        blueCarFuelBar.setStringPainted(true);

        // Get car positions
        int[] redCarPosition = game.getPlayerRed().getPosition();
        int[] blueCarPosition = game.getPlayerBlue().getPosition();

        // Main panel with BorderLayout
        mainPanel.setLayout(new BorderLayout());

        // Grid panel
        gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        // Add buttons to grid panel
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                DefaultCell tile = board[i][j];
                JPanel cellPanel = new JPanel(new GridBagLayout());
                cellPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                cellPanel.setBackground(tile.getUIColor());
                JButton button = new JButton(tile.getDisplayValue());
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.setBorderPainted(false);
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

                // Add button with constraints
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                cellPanel.add(button, gbc);

                // Add car labels to the correct cell with constraints
                if (i == redCarPosition[0] && j == redCarPosition[1]) {
                    gbc.gridy = 1; // Position red car label above the button
                    cellPanel.add(labelRedCar, gbc);
                }
                if (i == blueCarPosition[0] && j == blueCarPosition[1]) {
                    gbc.gridy = 2; // Position blue car label below the red car label
                    cellPanel.add(labelBlueCar, gbc);
                }

                gridPanel.add(cellPanel);
            }
        }

        // Button panel for "Roll Dice" button
        buttonPanel = new JPanel();
        RollDiceButton rollDiceButton = new RollDiceButton();

        // Roll dice button action
        rollDiceButton.addActionListener(e -> {
            this.game.onTurnPlayed();
            int lastDieRoll = this.game.getDie().getLastRoll();
            setDieRoll(lastDieRoll);

            // Get the current player
            Player currentPlayer = this.game.getCurrentTurnPlayer();

            // Create a timer to update the player's position with a delay
            Timer timer = new Timer(500, new ActionListener() {
                int count = 0;

                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count < lastDieRoll) {
                        boolean landed = count == lastDieRoll - 1;
                        System.out.println("Land: " + landed);

                        removeCarLabelFromPreviousPosition(currentPlayer);

                        // Move the player
                        game.movePlayer(currentPlayer, landed);

                        // Update the player's new position
                        updatePlayerUIPosition(currentPlayer);

                        updateFuelBars();
                        count++;
                    } else {
                        // Stop the timer once the die roll count is reached
                        ((Timer) e.getSource()).stop();
                    }
                }
            });
            timer.setInitialDelay(0);
            timer.start();
        });
        buttonPanel.add(rollDiceButton);

        // Initialize die roll button
        dieRollButton = new JButton("Last Die Roll: " + this.dieRoll);
        buttonPanel.add(dieRollButton);

        // Add fuel bars to button panel
        buttonPanel.add(new JLabel("Red Car Fuel:"));
        buttonPanel.add(redCarFuelBar);
        buttonPanel.add(new JLabel("Blue Car Fuel:"));
        buttonPanel.add(blueCarFuelBar);

        // Add grid panel and button panel to main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    // Method to update the die roll UI
    public void updateDieRollUI() {
        dieRollButton.setText("Last Die Roll: " + this.dieRoll);
    }

    public void updatePlayerUIPosition(Player player) {
        System.out.println("Player" + player.getColor() + " position: " + player.getPosition()[0] + ", " + player.getPosition()[1]);

        // Get player's position
        int[] position = player.getPosition();
        int row = position[0];
        int col = position[1];

        // Determine the car label based on player's color
        CarLabel carLabel = player.getColor() == Color.RED ? labelRedCar : labelBlueCar;

        // Calculate the index of the cell panel in the grid panel
        int gridSize = game.getBoard().length;
        int cellIndex = row * gridSize + col;

        // Get the cell panel
        JPanel cellPanel = (JPanel) gridPanel.getComponent(cellIndex);

        // Check if the cell already contains the car label, if not add it
        boolean containsCarLabel = false;
        for (Component comp : cellPanel.getComponents()) {
            if (comp == carLabel) {
                containsCarLabel = true;
                break;
            }
        }
        if (!containsCarLabel) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = (carLabel == labelRedCar) ? 1 : 2; // Position red car label above, blue below
            gbc.anchor = GridBagConstraints.CENTER;
            cellPanel.add(carLabel, gbc);
        }

        // Always revalidate and repaint the cell panel to update the UI
        cellPanel.revalidate();
        cellPanel.repaint();
    }

    public void removeCarLabelFromPreviousPosition(Player player) {
        // Get player's previous position
        int[] position = player.getPosition();
        int row = position[0];
        int col = position[1];

        // Determine the car label based on player's color
        CarLabel carLabel = player.getColor() == Color.RED ? labelRedCar : labelBlueCar;

        // Calculate the index of the cell panel in the grid panel
        int gridSize = game.getBoard().length;
        int cellIndex = row * gridSize + col;

        // Get the cell panel
        JPanel cellPanel = (JPanel) gridPanel.getComponent(cellIndex);

        // Remove the car label from the previous position
        cellPanel.remove(carLabel);

        // Always revalidate and repaint the cell panel to update the UI
        cellPanel.revalidate();
        cellPanel.repaint();
    }

    public void updateFuelBars() {
        int redCarFuel = game.getPlayerRed().getCar().getFuel();
        int blueCarFuel = game.getPlayerBlue().getCar().getFuel();

        redCarFuelBar.setValue(redCarFuel * 100 / 120);
        blueCarFuelBar.setValue(blueCarFuel * 100 / 120);
    }
}
