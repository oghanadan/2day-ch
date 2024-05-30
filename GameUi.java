import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GameUi {
    Game game = new Game();

    public void main() {
        createGameBoardUI();

    }

    public void createGameBoardUI(){
        DefaultCell[][] board = this.game.getBoard();
        JFrame frame = new JFrame("Game UI");
        int gridSize = board.length;
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 720);

        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Grid panel
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(gridSize, gridSize));

        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                DefaultCell tile = board[i][j];
                JButton button = new JButton("Tile " + (i) + "X" + (j) + "|");
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
    }
}