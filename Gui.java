package oop_project;

import javax.swing.*;

public class Gui {
    private JFrame frame;
    private JPanel panel;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton fileButton;
    private JTextArea consoleField;

    public Gui() 
    {
        frame = new JFrame();
        panel = new JPanel();
        inputLabel = new JLabel("Enter A Word To Search For");
        inputField = new JTextField(20);
        fileButton = new JButton("Click Me To Access Files");
        consoleField = new JTextArea();
    }
}