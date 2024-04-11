package oop_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {
    public static void searchWordInFile(File file, String searchWord, Gui gui) {
        int matchCounter = 0;
        StringBuilder output = new StringBuilder(gui.getConsoleField().getText());

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.toLowerCase().contains(searchWord.toLowerCase())) {
                    output.append("\nThe Word '").append(searchWord).append("' was found in line: ").append(line);
                    matchCounter++;
                }
            }
            output.append("\nThe File Path is: ").append(file.getAbsolutePath()).append("\nTotal matches found in ")
                    .append(file.getName()).append(": ").append(matchCounter).append("\n");
            gui.appendToConsole(output.toString());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
