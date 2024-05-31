import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameUi {
    // Variables
    private Game game;
    private int dieRoll = 0;
    private GameFrame frame;
    private JPanel mainPanel;
    private JPanel gridPanel;
    private JPanel buttonPanel;
    private CarLabel labelRedCar;
    private CarLabel labelBlueCar;
    private CarFuelPanel redCarFuelPanel;
    private CarFuelPanel blueCarFuelPanel;
    private JButton dieRollButton;
    private JProgressBar redCarFuelBar;
    private JProgressBar blueCarFuelBar;

    private boolean freezeInputs = false;

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

    private void toggleInputs() {
        this.freezeInputs = !this.freezeInputs;
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

        // Create a panel for the bars and cars
        redCarFuelPanel = new CarFuelPanel(labelRedCar, redCarFuelBar);
        blueCarFuelPanel = new CarFuelPanel(labelBlueCar, blueCarFuelBar);

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
                JLabel label = new JLabel(tile.getDisplayValue());
                if(i == gridSize - 1 && j == 0){
                    label.setText("Start");
                }else if(i == 0 && j == 0){
                    label.setText("Finish");
                }

                // Add button with constraints
                GridBagConstraints gbc = new GridBagConstraints();
                gbc.gridx = 0;
                gbc.gridy = 0;
                gbc.anchor = GridBagConstraints.CENTER;
                cellPanel.add(label, gbc);

                // Add car labels to the correct cell with constraints
                if (i == redCarPosition[0] && j == redCarPosition[1]) {
                    gbc.gridy = 1; // Position red car label above the button
                    cellPanel.add(redCarFuelPanel, gbc);
                }
                if (i == blueCarPosition[0] && j == blueCarPosition[1]) {
                    gbc.gridy = 2; // Position blue car label below the red car label
                    cellPanel.add(blueCarFuelPanel, gbc);
                }

                gridPanel.add(cellPanel);
            }
        }

        // Button panel for "Roll Dice" button
        buttonPanel = new JPanel();
        RollDiceButton rollDiceButton = new RollDiceButton();


        // Roll dice button action
        rollDiceButton.addActionListener(e -> {
            if (freezeInputs) {
                System.out.println("Inputs are frozen");
                return;
            }

            // Freeze inputs
            toggleInputs();

            boolean canMove = this.game.onTurnPlayed();
            

            // Get the current player
            Player currentPlayer = this.game.getCurrentTurnPlayer();


            // Display a message of how many turns the player has to miss if they chose to wait to refuel
            if(!canMove){
                JOptionPane.showMessageDialog(
                    frame,
                    "Player " + currentPlayer.getColorName() + "misses turn! Remaining turns: " + currentPlayer.getTurnsToMiss()
                );
                toggleInputs();
                //goes to the next round
                game.incrementRound();
                return;
            }

            // Roll the die
            int lastDieRoll = this.game.getDie().getLastRoll();
            setDieRoll(lastDieRoll);

            // Create a timer to update the player's position with a delay
            Timer timer = new Timer(500, new ActionListener() {
                int count = 0;

                // handles the movement of the player
                // loops over with a delay of 2 seconds for each value of the dice roll
                // dice roll 6 = 6 loops
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (count < lastDieRoll) {
                        // Check ig this is the final cycle of the dice movement
                        boolean landed = count == lastDieRoll - 1;
                        //removes player's car from its current position on the grid
                        removeCarLabelFromPreviousPosition(currentPlayer);
                        // Move the player
                        PlayerMovementOutcome movementOutcome = game.movePlayer(currentPlayer, landed);

                        // handles the outcome of the player's movement
                        if (movementOutcome == PlayerMovementOutcome.OUT_OF_FUEL){
                            currentPlayer.onOutOfFuel(board, frame); // show fuel recharge options dialg
                            count = lastDieRoll; // stop the movement
                        } else if (movementOutcome == PlayerMovementOutcome.WIN) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Player " + currentPlayer.getColorName() + " wins!"
                            );
                            count = lastDieRoll; // stop the movement
                            frame.dispose(); // close the game window
                        } else if (movementOutcome == PlayerMovementOutcome.RESET) {
                            JOptionPane.showMessageDialog(
                                    frame,
                                    "Player " + currentPlayer.getColorName() + " landed on black tile!"
                            );
                        }


                        // update the player's new position
                        updatePlayerUIPosition(currentPlayer);

                        // updates the fuel bars
                        updateFuelBars();
                        count++;
                    } else {
                        // stops the timer once the die roll count is reached
                        ((Timer) e.getSource()).stop();
                        toggleInputs();
                    }
                }
            });
            // sets the initial delay to 0 and start the timer
            timer.setInitialDelay(0);
            timer.start();

            // goes to the next round
            game.incrementRound();
        });
        buttonPanel.add(rollDiceButton);

        // Initialize die roll button
        dieRollButton = new JButton("Last Die Roll: " + this.dieRoll);
        buttonPanel.add(dieRollButton);

        // adds grid panel and button panel to main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // add main panel to frame
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

        // get the car label based on player's color
        CarFuelPanel carPanel = player.getColor() == Color.RED ? redCarFuelPanel : blueCarFuelPanel;

        // get the index of the cell panel in the grid panel
        int gridSize = game.getBoard().length;
        int cellIndex = row * gridSize + col;

        // get the cell panel
        JPanel cellPanel = (JPanel) gridPanel.getComponent(cellIndex);

        // see if the cell already contains the car label, if not add it
        boolean containsCarLabel = false;
        for (Component comp : cellPanel.getComponents()) {
            if (comp == carPanel) {
                containsCarLabel = true;
                break;
            }
        }
        if (!containsCarLabel) {
            GridBagConstraints gbc = new GridBagConstraints();
            gbc.gridx = 0;
            gbc.gridy = (carPanel == redCarFuelPanel) ? 1 : 2; // puts red car label above, blue below
            gbc.anchor = GridBagConstraints.CENTER;
            cellPanel.add(carPanel, gbc);
        }

        // revalidate and repaint UI
        cellPanel.revalidate();
        cellPanel.repaint();
    }

    public void removeCarLabelFromPreviousPosition(Player player) {
        // player's previous position
        int[] position = player.getPosition();
        int row = position[0];
        int col = position[1];

        // get the car label based on player's color
        CarFuelPanel carPanel = player.getColor() == Color.RED ? redCarFuelPanel : blueCarFuelPanel;

        // get the index of the cell panel in the grid panel
        int gridSize = game.getBoard().length;
        int cellIndex = row * gridSize + col;

        // get the cell panel
        JPanel cellPanel = (JPanel) gridPanel.getComponent(cellIndex);

        // remove the car label from the previous position
        cellPanel.remove(carPanel);

        // update and revslidate the ui
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
