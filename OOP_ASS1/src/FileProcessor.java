package oop_project;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessor {
    // Map to store ranking scores for each file
    private static Map<String, Double> rankingScoresMap = new HashMap<>();

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
                    output.append("\nThe search term '").append(searchWord).append("' was found in line: ").append(line);
                    matchCounter++;
                    index += searchWord.length();
                }
                
            }
	            if (matchCounter == 0) {
	                output.append("\nNo words were found with the search term: ").append(searchWord);
	            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // calculates the percentage
        double percentage = (double) matchCounter / wordCount * 100;

        // Ranking score based on frequency and length of the document
        // calculates the frequency of the search term in the txt file
        double frequency = (double) matchCounter / wordCount;

        // calculates the length factor and gets the inverse proportion of the txt file length
        // so that the length of the txt file doesn't disproportionately affect the ranking
        double lengthFactor = 1.0 / wordCount;

        // assigns weights to the frequency and length factor
        double frequencyWeight = 0.8; // 0.6 as frequency is more important - indicator of relevance
        double lengthWeight = 0.2;
        
        double rankingScore; // Declare the variable outside of the if-else block

        if (matchCounter > 0) {
            // combines the frequency and length factors using their weights to calculate the overall ranking score
            rankingScore = (frequencyWeight * frequency) + (lengthWeight * lengthFactor);
        } else {
            rankingScore = 0;
        }

        output.append("\nThe File Path is: \n").append(file.getAbsolutePath()).append("\n");
        gui.appendToConsole(output.toString());
        gui.appendToRank("Total matches found in " + file.getAbsoluteFile(), " are:\n", matchCounter);
        gui.appendPercentage("\nThe percentage of matches found in " + file.getName() + ": \n", percentage);
        // Append the ranking score to the GUI
        //gui.appendRankingScore("Ranking score for " + file.getName() + ": ", String.format("%.2f", rankingScore));
        // Store the ranking score for this file
        rankingScoresMap.put(file.getName(), rankingScore);
        
    }

    public static void printFilesInHighestRankingOrder(Gui gui) {
        // Create a list to store file names sorted by ranking scores
        List<String> sortedFiles = new ArrayList<>(rankingScoresMap.keySet());

        // Sort the files based on their ranking scores
        Collections.sort(sortedFiles, (file1, file2) -> {
            double score1 = rankingScoresMap.getOrDefault(file1, 0.0);
            double score2 = rankingScoresMap.getOrDefault(file2, 0.0);
            return Double.compare(score2, score1); // Descending order
        });

        // Print the ranking in the GUI
        gui.printRanking(sortedFiles, rankingScoresMap);
    }


}
