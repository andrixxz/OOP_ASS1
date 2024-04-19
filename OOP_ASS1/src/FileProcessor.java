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
    // map that stores ranking scores for each file
    private static Map<String, Double> rankingScoresMap = new HashMap<>();

    // method used to search the search term in the files
    public static void searchWordInFile(File file, String searchWord, Gui gui) {
    	// tracking the amount of search term matches
        int matchCounter = 0;
        // tracks the total word count in the file
        int wordCount = 0; 
        
        // object used to dynamically build the output messages
        StringBuilder output = new StringBuilder();
        
        // reads the character files efficiently that the user specifies
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;

            // reads each line from the file until the end
            while ((line = reader.readLine()) != null) {
            	// counts the total words in the line
                wordCount += line.split("\\s+").length; 

                int index = 0;
                
                // searches for the search term in the line
                // trim used to remove any white spaces before and after the word
                // doesn't stop until the file is equal to -1, nothing left to check
                while ((index = line.toLowerCase().indexOf(searchWord.trim().toLowerCase(), index)) != -1) {
                    output.append("\nThe search term '").append(searchWord).append("' was found in line: ").append(line);
                    
                    matchCounter++; // increments the search term matches count
                    
                    // splits the current line into words based on whitespace 
                    // then adds the number of words to the wordCount
                    // updates the index so that once search term found the search continues from that same position
                    index += searchWord.length();
                }
                
            }	// appends message if file contains no words matching search term
	            if (matchCounter == 0) {
	                output.append("\nNo matches were found with the search term: ").append(searchWord);
	            }
	      // catches any IOException that might occur during file reading      
        } catch (IOException ex) {
        	// prints the stack trace of the exception for debugging purposes
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
        double frequencyWeight = 0.6; // 0.6 as frequency is more important - indicator of relevance
        double lengthWeight = 0.4;
        
        double rankingScore; 
        
        // calculating the ranking score
        if (matchCounter > 0) {
            // combines the frequency and length factors using their weights to calculate the overall ranking score
            rankingScore = (frequencyWeight * frequency) + (lengthWeight * lengthFactor);
        } else {
            rankingScore = 0;
        }
        
        // appends the file path and search results to the GUI
        output.append("\nThe File Path is: \n").append(file.getAbsolutePath()).append("\n");
        gui.appendToConsole(output.toString());
        gui.appendToRank("Total matches found in " + file.getAbsoluteFile(), " are:\n", matchCounter);
        gui.appendPercentage("The percentage of matches found in " + file.getName() + ": \n", percentage);

        // store the ranking score for file so it can be used for ranking
        rankingScoresMap.put(file.getName(), rankingScore);
        
    }

    // method used to print files from highest to lowest based on ranking score
    public static void printFilesInHighestRankingOrder(Gui gui) {
        // creates a new array list to store file names sorted by ranking scores
        List<String> sortedFiles = new ArrayList<>(rankingScoresMap.keySet());

        // method that sort the files based on their ranking scores
        Collections.sort(sortedFiles, (file1, file2) -> {
        	// retrieves the ranking score for file1 from the rankingScoresMap, or use 0.0 if not found
            double score1 = rankingScoresMap.getOrDefault(file1, 0.0);
            double score2 = rankingScoresMap.getOrDefault(file2, 0.0);
            // compares the ranking scores of files in descending order
            // returns -1 if score2 is greater, 0 if equal, positive int if score1 is greater
            return Double.compare(score2, score1);
        });

        // prints the ranking in the GUI
        gui.printRanking(sortedFiles, rankingScoresMap);
    }

    // method to clear the ranking scores map
    public static void clearRankingScoresMap() {
        rankingScoresMap.clear();

    }
}
