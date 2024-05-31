import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class OutOfFuelDialog extends JDialog {
    private Player player;
    private DefaultCell[][] board;
    private int option;
    private int turns = 0;

    private JPanel mainPanel;
    private JPanel turnPanel;
    private JPanel goToStartPanel;

    private JSlider turnSlider;

    private JLabel headerLabel;
    private JLabel turnLabel;

    private JButton confirmTurnButton;
    private JButton goToStartButton;

    public OutOfFuelDialog(JFrame parent, Player player, DefaultCell[][] board) {
        super(parent, "Out of Fuel", true);
        this.player = player;
        this.board = board;

        initializeComponents();
        layoutComponents();
        addEventHandlers();

        pack();
        setSize(800, 400);
        setLocationRelativeTo(parent);
    }

    private void initializeComponents() {
        headerLabel = new JLabel("Select one Out of Fuel Option:", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));

        turnPanel = new JPanel();
        turnLabel = new JLabel("Select the number of turns to wait:");
        turnSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 1);
        confirmTurnButton = new JButton("Confirm");

        customizeSlider(turnSlider);

        goToStartPanel = new JPanel();
        goToStartButton = new JButton("Go to Start");
    }

    private void layoutComponents() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        
        mainPanel.add(headerLabel, gbc);

        gbc.gridy++;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(turnLabel, gbc);

        gbc.gridx++;
        gbc.anchor = GridBagConstraints.EAST;
        mainPanel.add(turnSlider, gbc);

        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(confirmTurnButton, gbc);

        gbc.gridy++;
        mainPanel.add(goToStartButton, gbc);

        add(mainPanel);
    }

    private void addEventHandlers() {
        turnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setTurns(turnSlider.getValue());
            }
        });

        confirmTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.onRefuelChoice(turns, board);
                setVisible(false);
            }
        });

        goToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.onRefuelChoice(0, board);
                setVisible(false);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }

    private void customizeSlider(JSlider slider) {
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(2);
        slider.setOpaque(false);
    }

    public int getOption() {
        return option;
    }

    public int getTurns() {
        return turns;
    }

    public void setOption(int option) {
        if (option == 1 || option == 2) {
            this.option = option;
        } else {
            this.option = 1;
        }
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }
}
