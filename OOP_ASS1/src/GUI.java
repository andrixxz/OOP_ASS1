
import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.io.BufferedReader;

import java.io.File;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.io.IOException;

import java.util.ArrayList;

import java.util.Arrays;

 

import javax.swing.*;

 

public class Gui implements ActionListener{

               

    private JFrame frame;

    private JPanel panel;

    private JLabel promptLabel;

    private JLabel inputLabel;

    private JButton file_button;

   

    private JTextField inputfield;

   

    public Gui() {

               

        frame = new JFrame();

        panel = new JPanel();

        promptLabel = new JLabel("Enter a file below");

        inputLabel = new JLabel("Enter A Word To Search For");

       

        inputfield = new JTextField(20);

        panel.add(inputfield);

       

 

        setupUI();

    }

 

    private void setupUI() {

        panel.add(promptLabel);

        frame.add(panel);

       

 

        frame.setSize(600, 600);

        frame.setResizable(false);

        frame.setTitle("JFrame Title");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);

       

        

        file_button = new JButton();

                                file_button.setVisible(true);

                                file_button.setBounds(200, 30, 180, 40);

                                file_button.setText("Click Me To Access Files");

                                panel.add(file_button);

                                file_button.addActionListener(this);

                               

                               

                                panel.add(inputLabel);

                                frame.add(panel);

                                frame.setVisible(true);

                               

                               

                               

                               

    }

   

    

    

    public void actionPerformed(ActionEvent e) {

               

                if(e.getSource() == file_button) {

                               

                                int result;

                               

                                JFileChooser fileSelector = new JFileChooser();

                                result = fileSelector.showOpenDialog(fileSelector);

                               

                                // checks if the user selects a file and doesn't close the application

                                // Returned 0 opens file , returned 1 does not

                                if(result == JFileChooser.APPROVE_OPTION) {

                                                File file = new File(fileSelector.getSelectedFile().getAbsolutePath()); // Get the file path of the file on users machine

                                                System.out.println(file);

                                                                                               

                                                try {

                                                                FileReader reader = new FileReader(file);

                                                                int data = reader.read();

                                                                while(data != -1) { // read the file until there is nothing left to read

                                                                                System.out.print((char)data);

                                                                                data = reader.read();

                                                                               

                                                                               

                                                                               

                                                                }

                                                                reader.close();

                                                                getUserInput();

                                                               

                                                               

 

 

                                                } catch (FileNotFoundException x) {

                                                                x.printStackTrace();

                                                               

 

                                                } catch (IOException i) {

                                                                i.printStackTrace();

                                                               

                                }

                                               

                                } else {

                                                System.out.println("No File Selected Or Opened");

                                }

                               

                               

                               

                               

                }

               

    

    }

   

    public void getUserInput() {

               

                String wordToSearch = inputfield.getText(); // Retrieve text from JTextField

        System.out.println("Word to search: " + wordToSearch);

               

               

               

               

    }

 

 

 

}