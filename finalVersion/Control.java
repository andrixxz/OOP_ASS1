package oop_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Control {
	// instance of the Gui class
    private Gui gui;

    public Control() {
    	// intialises the GUI
        gui = new Gui();
        // adds the ActionListener to the file button
        gui.getFileButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	// selects the file
                File[] files = FileSelector.selectFiles();
                // if files are selected
                if (files != null) {
                	// gets the search term from the input field
                    String searchWord = gui.getInputField().getText();
                    // iterates through each selected file
                    for (File file : files) {
                    	// searches for the search word in the file and updates GUI
                        FileProcessor.searchWordInFile(file, searchWord, gui);
                    }
                    // after searching in all files, prints files in highest ranking order
                    FileProcessor.printFilesInHighestRankingOrder(gui);
                    
                } else {
                	// if no files are selected
                    gui.appendToConsole("No File Selected Or Opened");
                }
            }
        });
    }

    public static void main(String[] args) {
    	// creates an instance of control to start the program 
        new Control();
        
    }
}
