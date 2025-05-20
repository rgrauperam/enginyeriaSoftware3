package mvc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

public class TemperatureView implements ActionListener, Observer {

    private TemperatureModelInterface model;
    private TemperatureControllerInterface controller;
    private JFrame viewFrame;
    private JPanel viewPanel;
    private JLabel tempOutputLabel; //Displays target temperature
    private JFrame controlFrame;
    private JPanel controlPanel;
    private JLabel tempLabel;
    private JLabel tempLabelView;
    private JTextField tempTextField;
    private JButton setTempButton; //Set temperature button (Set)
    private JButton increaseTempButton; //Increase temperature button (+)
    private JButton decreaseTempButton; //Decrease temperature button (-)
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem startMenuItem; //Start Menu Item
    private JMenuItem stopMenuItem; //Stop Menu Item
    private JLabel currentTempLabel;
    private JLabel currentTemp; //Displays current temperature


    public TemperatureView(TemperatureControllerInterface controller, TemperatureModelInterface model) {
        this.controller = controller;
        this.model = model;
        this.model.registerObserver(this); // Register view as an observer of the model
    }

    public void createView() {
        // Create all Swing components here
        viewPanel = new JPanel(new GridLayout(1, 2));
        viewFrame = new JFrame("View");
        viewFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        viewFrame.setPreferredSize(new Dimension(300, 160));
        tempOutputLabel = new JLabel("offline", SwingConstants.CENTER);
        tempLabelView = new JLabel("Target Temp", SwingConstants.CENTER);
        currentTempLabel = new JLabel("Current Temp", SwingConstants.CENTER);
        currentTemp = new JLabel("",SwingConstants.CENTER);

        //Font size
        int fontSizeToUse = currentTemp.getFont().getSize();
        fontSizeToUse *= 3;
        Font newFont = new Font(currentTempLabel.getName(), Font.PLAIN, fontSizeToUse);

        currentTemp.setFont(newFont);
        tempOutputLabel.setFont(newFont);

        //Target Temperature
        JPanel targetTempPanel = new JPanel(new GridLayout(2, 1));
        targetTempPanel.add(tempLabelView);
        targetTempPanel.add(tempOutputLabel);
        viewPanel.add(targetTempPanel);


        //Current Temperature
        JPanel currentTempPanel = new JPanel(new GridLayout(2, 1));
        currentTempPanel.add(currentTempLabel);
        currentTempPanel.add(currentTemp);
        viewPanel.add(currentTempPanel);

        viewFrame.getContentPane().add(viewPanel, BorderLayout.CENTER);
        viewFrame.pack();
        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        viewFrame.setLocation((int) (screenWidth/2.5), (int)(screenHeight/2.5));
        viewFrame.setVisible(true);
    }


    public void createControls() {
        // Create all Swing components here
        JFrame.setDefaultLookAndFeelDecorated(true);
        controlFrame = new JFrame("Control");
        controlFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controlFrame.setSize(new Dimension(100, 80));

        controlPanel = new JPanel(new GridLayout(1, 2));

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        startMenuItem = new JMenuItem("Start");
        menu.add(startMenuItem);
        startMenuItem.addActionListener((event) -> controller.start());

        stopMenuItem = new JMenuItem("Stop");
        menu.add(stopMenuItem);
        stopMenuItem.addActionListener((event) -> controller.stop());

        JMenuItem exit = new JMenuItem("Quit");
        exit.addActionListener((event) -> System.exit(0));


        menu.add(exit);
        menuBar.add(menu);
        controlFrame.setJMenuBar(menuBar);

        tempTextField = new JTextField(2);
        tempLabel = new JLabel("Enter Temperature:", SwingConstants.RIGHT);
        setTempButton = new JButton("Set");
        setTempButton.setSize(new Dimension(10,40));
        increaseTempButton = new JButton("+");
        decreaseTempButton = new JButton("-");
        setTempButton.addActionListener(this);
        increaseTempButton.addActionListener(this);
        decreaseTempButton.addActionListener(this);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 2));

        buttonPanel.add(decreaseTempButton);
        buttonPanel.add(increaseTempButton);

        JPanel enterPanel = new JPanel(new GridLayout(1, 2));
        enterPanel.add(tempLabel);
        enterPanel.add(tempTextField);
        JPanel insideControlPanel = new JPanel(new GridLayout(3, 1));
        insideControlPanel.add(enterPanel);
        insideControlPanel.add(setTempButton);
        insideControlPanel.add(buttonPanel);
        controlPanel.add(insideControlPanel);

        tempLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        tempOutputLabel.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        controlFrame.getRootPane().setDefaultButton(setTempButton);
        controlFrame.getContentPane().add(controlPanel, BorderLayout.CENTER);

        controlFrame.pack();

        int screenWidth = Toolkit.getDefaultToolkit().getScreenSize().width;
        int screenHeight = Toolkit.getDefaultToolkit().getScreenSize().height;
        controlFrame.setLocation((int) (screenWidth/2.2), (int)(screenHeight/2.2));

        controlFrame.setVisible(true);
    }

    public void enableStartMenuItem() {
        startMenuItem.setEnabled(true);
    }

    public void disableStartMenuItem() {
        startMenuItem.setEnabled(false);
    }

    public void enableStopMenuItem() {
        stopMenuItem.setEnabled(true);
    }

    public void disableStopMenuItem() {
        stopMenuItem.setEnabled(false);
    }

    public void enableIncreaseButton() {
        increaseTempButton.setEnabled(true);
    }

    public void disableIncreaseButton() {
        increaseTempButton.setEnabled(false);
    }

    public void enableDecreaseButton() {
        decreaseTempButton.setEnabled(true);
    }

    public void disableDecreaseButton() {
        decreaseTempButton.setEnabled(false);
    }

    public void enableSetButton() {
        setTempButton.setEnabled(true);
    }

    public void disableSetButton() {
        setTempButton.setEnabled(false);
    }

    public void setTargetTemperatureEditable(boolean editable) {
        tempTextField.setEditable(editable);
    }

    public void setTargetTemperatureDisplay(String text) {
        tempOutputLabel.setText(text);
    }

    public void setCurrentTemperatureDisplay(String text) {
        currentTemp.setText(text);
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        Object source = event.getSource();
        if (source == increaseTempButton) {
            controller.increaseTargetTemperature();
        } else if (source == decreaseTempButton) {
            controller.decreaseTargetTemperature();
        } else if (source == setTempButton) {
            try {
                int temp = Integer.parseInt(tempTextField.getText());
                controller.setTargetTemperature(temp);
            } catch (NumberFormatException nfe) {
                JOptionPane.showMessageDialog(controlFrame, "Please enter a valid number for temperature.", "Input Error", JOptionPane.ERROR_MESSAGE);
                tempTextField.setText(""); // Clear invalid input
            }
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof TemperatureModelInterface) {
            TemperatureModelInterface currentModel = (TemperatureModelInterface) o;
            this.currentTemp.setText(String.valueOf(currentModel.getCurrentTemperature()));

            // Only update target temperature display from model if controls are enabled (i.e., text field is editable)
            // Otherwise, the controller is responsible for setting tempOutputLabel to "N/A", "offline", etc.
            if (this.tempTextField.isEditable()) {
                this.tempOutputLabel.setText(String.valueOf(currentModel.getTargetTemperature()));
            }
        }
    }
}