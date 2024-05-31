import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class OutOfFuelDialog extends JDialog {
    private Player player;
    private DefaultCell[][] board;
    private int turns = 0;

    // Panels
    private JPanel mainPanel;
    private JPanel turnPanel;
    private JPanel goToStartPanel;
    // Slider
    private JSlider turnSlider;
    // Labels
    private JLabel headerLabel;
    private JLabel turnLabel;
    // Buttons
    private JButton confirmTurnButton;
    private JButton goToStartButton;

    // Constructor
    public OutOfFuelDialog(JFrame parent, Player player, DefaultCell[][] board) {
        super(parent, "Out of Fuel", true);
        this.player = player;
        this.board = board;

        // Initialize components
        initializeComponents();
        layoutComponents();
        addEventHandlers();

        // Set up the dialog
        pack();
        setSize(800, 400);
        setLocationRelativeTo(parent);
    }

    // Initialize components
    private void initializeComponents() {
        // Labels
        headerLabel = new JLabel("Select one Out of Fuel Option:", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 16));
        // Turn Components
        turnPanel = new JPanel();
        turnLabel = new JLabel("Select the number of turns to wait:");
        turnSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 1);
        confirmTurnButton = new JButton("Confirm");
        customizeSlider(turnSlider);
        // Go to Start Components
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

    // Add event handlers
    private void addEventHandlers() {
        // Update the turns when the slider is changed
        turnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setTurns(turnSlider.getValue());
            }
        });

        // Confirm choice : Wait
        confirmTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.onRefuelChoice(turns, board);
                setVisible(false);
            }
        });

        // Confirm choice : Go to Start
        goToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                player.onRefuelChoice(0, board);
                setVisible(false);
            }
        });

        // Close the dialog
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });
    }


    // Slider customization
    private void customizeSlider(JSlider slider) {
        slider.setPreferredSize(new Dimension(200, 50));
        slider.setPaintTicks(true);
        slider.setPaintTrack(true);
        slider.setPaintLabels(true);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(2);
        slider.setOpaque(false);
    }

    // Set the number of turns
    public void setTurns(int turns) {
        this.turns = turns;
    }
}
