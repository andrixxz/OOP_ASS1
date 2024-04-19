package oop_project;

import javax.swing.*;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.Map;

public class Gui {

    private JFrame frame;
    private JPanel panel;
    private JLabel inputLabel;
    private JTextField inputField;
    private JButton fileButton;
    private JButton saveButton; // Add Save button
    private JTextArea consoleField;
    private JTextArea rankfield;

    public Gui() {
        frame = new JFrame();
        panel = new JPanel();
        inputLabel = new JLabel("Enter A Word To Search For");
        inputField = new JTextField(20);
        fileButton = new JButton("Click Me To Access Files");
        saveButton = new JButton("Save"); // Initialize Save button
        consoleField = new JTextArea();
        rankfield = new JTextArea();

        setupUI();
    }

    private void setupUI() {
        frame.setLayout(null);
        panel.setLayout(null);

        frame.setSize(700, 690);
        frame.setResizable(false);
        frame.setTitle("JFrame Title");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        panel.setBounds(200, 0, 200, 300);
        panel.setVisible(true);

        inputLabel.setBounds(20, 0, 200, 50);
        panel.add(inputLabel);

        inputField.setBounds(25, 50, 150, 50);
        panel.add(inputField);

        fileButton.setBounds(0, 120, 200, 75);
        panel.add(fileButton);

        // Add Save button to panel
        saveButton.setBounds(150, 100, 100, 50);
        panel.add(saveButton);

        consoleField.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(consoleField);
        scrollPane.setBounds(100, 220, 400, 200);

        rankfield.setEditable(false);
        JScrollPane rankpane = new JScrollPane(rankfield);
        rankpane.setBounds(150, 430, 300, 200);

        frame.add(scrollPane);
        frame.add(panel);
        frame.add(rankpane);

        // Add ActionListener for Save button
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showSaveDialog(null);
                
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File selectedFile = fileChooser.getSelectedFile();
                    try (PrintWriter writer = new PrintWriter(selectedFile)) {
                        writer.println(rankfield.getText());
                        writer.println(consoleField.getText()); // Write console output to file
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
    }

    public void printRanking(List<String> sortedFiles, Map<String, Double> rankingScoresMap) {
        rankfield.append("\nFiles in the order of most relevant:\n\n");
        for (String filename : sortedFiles) {
            double score = rankingScoresMap.getOrDefault(filename, 0.0);
            rankfield.append(filename + " - Ranking Score: " + String.format("%.2f", score) + "\n");
        }
    }

    public void appendToConsole(String text) {
        consoleField.append(text + "\n");
    }

    public void appendToRank(String message, String message2, int match) {
        rankfield.append(message + message2 + match + "\n");
    }
    
    public void appendPercentage(String message, double percentage) {
        rankfield.append(message + String.format("%.2f%%", percentage) + "\n");
    }
    public void appendRankingScore(String message, String score) {
        rankfield.append(message + score + "\n");
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
