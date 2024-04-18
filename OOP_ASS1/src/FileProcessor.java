package oop_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class FileProcessor {

    public static void searchWordInFile(File file, String searchWord, Gui gui) {

        int matchCounter = 0;
        int wordCount = 0; // tracks the total word count in the file
        StringBuilder output = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = reader.readLine()) != null) {
            	wordCount += line.split("\\s+").length; // counts the total words in the line
            	
                int index = 0;

                while ((index = line.toLowerCase().indexOf(searchWord.trim().toLowerCase(), index)) != -1) {
                    output.append("\nThe Word '").append(searchWord).append("' was found in line: ").append(line);
                    matchCounter++;
                    index += searchWord.length();
                }
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        double percentage = (double) matchCounter /wordCount * 100; // calculates the percentage

        
        output.append("\nThe File Path is: ").append(file.getAbsolutePath()).append("\n");
        gui.appendToConsole(output.toString());
        gui.appendToRank(" Total matches found in " + file.getAbsoluteFile(), " are ", matchCounter);
        gui.appendPercentage("\n The percentage of matches found in " + file.getName() + ": ", percentage);
    }
}
