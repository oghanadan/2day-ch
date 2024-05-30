import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Test {

    public static void main(String[] args) {
        createBoard();
    }

    static void createBoard() {
        int gridSize = 5;

        Tile[][] board = new Tile[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                if (i == gridSize - 1 && j == 0) {
                    board[i][j] = new Tile(TileColor.GREY, TileType.START);
                } else if (i == 0 && j == 0) {
                    board[i][j] = new Tile(TileColor.GREY, TileType.END);
                } else {
                    board[i][j] = new Tile();
                }
            }
        }
        
        JFrame frame = new JFrame("Game UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 720);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        JButton[][] buttons = new JButton[gridSize][gridSize];
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                Tile tile = board[i][j];
                JButton button = new JButton("Tile " + i + "X" + j + "|" + tile.getFuelCost() + "-" + tile.getTileColor());
                button.setOpaque(true);
                button.setContentAreaFilled(true);
                button.setBorderPainted(false);
                button.setBackground(tile.getTileUIColor());
                button.setForeground(Color.BLACK); // Ensure text color is different from background
                buttons[i][j] = button;
                gridPanel.add(button);
            }
        }

        Player redPlayer = new Player(Color.RED, gridSize - 1, 0);
        Player bluePlayer = new Player(Color.BLUE, gridSize - 1, 0);

        updateButton(buttons, redPlayer, bluePlayer);

        // Button panel for "Roll Dice" buttons
        JPanel buttonPanel = new JPanel();
        JButton rollDiceButtonRed = new JButton("Roll Dice (Red)");
        JButton rollDiceButtonBlue = new JButton("Roll Dice (Blue)");

        rollDiceButtonRed.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = (int) (Math.random() * 6) + 1;
                JOptionPane.showMessageDialog(frame, "Red player rolled a " + diceRoll);
                movePlayer(redPlayer, diceRoll, gridSize);
                SwingUtilities.invokeLater(() -> updateButton(buttons, redPlayer, bluePlayer));
            }
        });

        rollDiceButtonBlue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int diceRoll = (int) (Math.random() * 6) + 1;
                JOptionPane.showMessageDialog(frame, "Blue player rolled a " + diceRoll);
                movePlayer(bluePlayer, diceRoll, gridSize);
                SwingUtilities.invokeLater(() -> updateButton(buttons, redPlayer, bluePlayer));
            }
        });

        buttonPanel.add(rollDiceButtonRed);
        buttonPanel.add(rollDiceButtonBlue);

        // Add grid panel and button panel to main panel
        mainPanel.add(gridPanel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Add main panel to frame
        frame.getContentPane().add(mainPanel);
        frame.setVisible(true);
    }

    static void movePlayer(Player player, int steps, int gridSize) {
        for (int i = 0; i < steps; i++) {
            boolean shouldMoveLeft = player.row % 2 != 0;
            if (shouldMoveLeft) {
                if (player.col > 0) {
                    player.col--;
                } else if (player.row > 0) {
                    player.col = 0;
                    player.row--;
                }
            } else {
                if (player.col < gridSize - 1) {
                    player.col++;
                } else if (player.row > 0) {
                    player.col = gridSize - 1;
                    player.row--;
                }
            }
        }
    }

    static void updateButton(JButton[][] buttons, Player redPlayer, Player bluePlayer) {
        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                buttons[i][j].setText("Tile " + i + "X" + j); // Reset to initial text
                buttons[i][j].setForeground(Color.BLACK);
            }
        }

        if (redPlayer.row == bluePlayer.row && redPlayer.col == bluePlayer.col) {
            buttons[redPlayer.row][redPlayer.col].setText("Tile " + redPlayer.row + "X" + redPlayer.col + " R/B");
            buttons[redPlayer.row][redPlayer.col].setForeground(Color.MAGENTA);
        } else {
            buttons[redPlayer.row][redPlayer.col].setText("Tile " + redPlayer.row + "X" + redPlayer.col + " R");
            buttons[redPlayer.row][redPlayer.col].setForeground(Color.RED);
            buttons[bluePlayer.row][bluePlayer.col].setText("Tile " + bluePlayer.row + "X" + bluePlayer.col + " B");
            buttons[bluePlayer.row][bluePlayer.col].setForeground(Color.BLUE);
        }
    }
}

class Player {
    public Color color;
    public int row;
    public int col;

    public Player(Color color, int row, int col) {
        this.color = color;
        this.row = row;
        this.col = col;
    }
}
