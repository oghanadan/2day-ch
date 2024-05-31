import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;

public class OutOfFuelDialog extends JDialog{
    // Variables
    private int option;
    private int turns;
    // Panels
    JPanel turnPanel;
    JPanel goToStartPanel;
    // Slider
    JSlider turnSlider;
    // Labels
    JLabel headerLabel;
    JLabel goToStartLabel;
    JLabel turnLabel;
    // Buttons
    JButton turnButton;
    JButton goToStartButton;
    JButton confirmTurnButton;


    OutOfFuelDialog(JFrame parent){
        super(parent, "Out of Fuel", true);

        // Create a header label
        headerLabel = new JLabel("Select one Out of Fuel Options:");

        // Create a panel for the turn option
        turnPanel = new JPanel();
        turnLabel = new JLabel("Select the number of turns to wait:");
        turnSlider = new JSlider(JSlider.HORIZONTAL, 1, 6, 1);
        confirmTurnButton = new JButton("Confirm");

        // Customize the slider
        turnSlider.setPreferredSize(new Dimension(100, 50));
        turnSlider.setPaintTicks(true);
        turnSlider.setPaintTrack(true);
        turnSlider.setPaintLabels(true);
        turnSlider.setMinorTickSpacing(1);
        turnSlider.setMajorTickSpacing(2);
        turnSlider.setOpaque(false);

        // Add components to the turn panel
        turnPanel.add(turnLabel);
        turnPanel.add(turnSlider);
        turnPanel.add(confirmTurnButton);


        // Create a panel for the go to start option
        goToStartPanel = new JPanel();
        goToStartButton = new JButton("Go to Start");

        // Add components to the go to start panel
        goToStartPanel.add(goToStartButton);

        // Set the layout manager for the dialog
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        // Add panels to the dialog
        add(headerLabel);
        add(turnPanel);
        add(goToStartPanel);

        // Start the select option method
        selectOption();


        // Add a window listener to dispose the dialog
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
            }
        });


        pack();
        setSize(600, 400);
        setLocationRelativeTo(parent);
    }

    // Getters
    public int getOption() {
        return option;
    }

    public int getTurns() {
        return turns;
    }


    // Setters
    public void setOption(int option) {
        // If a mistake happens in the code set the option to 1 as default
        if (this.option == 1 || this.option == 2) {
            this.option = option;
        } else {
            this.option = 1;
        }
    }

    public void setTurns(int turns) {
        this.turns = turns;
    }


    // Select option method
    public void selectOption(){
        // Add action listeners to the slider
        turnSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setTurns(turnSlider.getValue());
            }
        });

        // Add action listeners to the confirm button
        confirmTurnButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOption(1);
            }
        });

        // Add action listeners to the go to start button
        goToStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setOption(2);
            }
        });
    }
}