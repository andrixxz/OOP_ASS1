package oop_project;

import javax.swing.*;
import java.io.File;

public class FileSelector {
    public static File[] selectFiles() {
        int result;
        JFileChooser fileSelector = new JFileChooser();
        fileSelector.setMultiSelectionEnabled(true);

        result = fileSelector.showOpenDialog(fileSelector);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileSelector.getSelectedFiles();
        } else {
            return null;
        }
    }
}
