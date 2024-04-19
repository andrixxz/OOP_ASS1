package oop_project;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Control {
    private Gui gui;

    public Control() {
        gui = new Gui();
        gui.getFileButton().addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                File[] files = FileSelector.selectFiles();
                if (files != null) {
                    String searchWord = gui.getInputField().getText();
                    for (File file : files) {
                        FileProcessor.searchWordInFile(file, searchWord, gui);
                    }
                    // After searching in all files, print files in highest ranking order
                    FileProcessor.printFilesInHighestRankingOrder(gui);
                    
                } else {
                    gui.appendToConsole("No File Selected Or Opened");
                }
            }
        });
    }

    public static void main(String[] args) {
        new Control();
    }
}
