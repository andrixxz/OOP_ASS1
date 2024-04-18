package oop_project;

import javax.swing.*;

public class Gui {

    // Declaring Variables

    private JFrame frame;
    private JPanel panel;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton fileButton;
    private JTextArea consoleField;
    private JTextArea rankfield;

    public Gui() {

        // Constructors

        frame = new JFrame();
        panel = new JPanel();
        inputLabel = new JLabel("Enter A Word To Search For");
        inputField = new JTextField(20);
        fileButton = new JButton("Click Me To Access Files");
        consoleField = new JTextArea();
        rankfield = new JTextArea();

        setupUI();
    }

    private void setupUI() {

        // Stops any computer changes from taking place in the frame
        frame.setLayout(null);
        panel.setLayout(null);

        // Setting Up Initial Frame, sizes etc
        frame.setSize(600, 690);
        frame.setResizable(false);
        frame.setTitle("JFrame Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.setBounds(200, 0, 200, 300);
        panel.setVisible(true);

        // Setting up the input label which displays "Enter A Word To Search For"
        inputLabel.setBounds(20, 0, 200, 50);
        panel.add(inputLabel);

        // Setting up the box for user input
        inputField.setBounds(25, 50, 150, 50);
        panel.add(inputField);

        // Setting Up Button to allow users to upload a file
        fileButton.setBounds(0, 120, 200, 75);
        panel.add(fileButton);

        // Stops users from being able to type into the output box
        consoleField.setEditable(false);

        // Setting up the Output box to allow it to scroll
        // This allows for long text to be viewed by the user
        JScrollPane scrollPane = new JScrollPane(consoleField);
        scrollPane.setBounds(100, 220, 400, 200);

        // Setting up ranking area for displaying best matched file
        rankfield.setEditable(false);
        JScrollPane rankpane = new JScrollPane(rankfield);
        rankpane.setBounds(150, 430, 300, 200);

        // Adding the components to the frame
        frame.add(scrollPane);
        frame.add(panel);
        frame.add(rankpane);
    }

    // Getters to return various information

    public void appendToConsole(String text) {
        consoleField.append(text + "\n");
    }

    public void appendToRank(String message, String message2, int match) {
        rankfield.append(message + message2 + match + "\n");
    }
    
    public void appendPercentage(String message, double percentage) {
        rankfield.append(message + String.format("%.2f%%", percentage) + "\n");
    }

    public JTextField getInputField() {
        return inputField;
    }

    public JFrame getFrame() {
        return frame;
    }

    public JButton getFileButton() {
        return fileButton;
    }

    public JTextArea getConsoleField() {
        return consoleField;
    }
}
