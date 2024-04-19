package oop_project;

import javax.swing.*;
import java.io.File;

public class FileSelector {
    public static File[] selectFiles() {
        int result;
        JFileChooser fileSelector = new JFileChooser();
        // allows multiple files to be chosen
        fileSelector.setMultiSelectionEnabled(true);

        result = fileSelector.showOpenDialog(fileSelector);

        // checks if the user has selected and opened a file
        if (result == JFileChooser.APPROVE_OPTION) {
            return fileSelector.getSelectedFiles();
        } else {
            return null;
        }
    }
}
