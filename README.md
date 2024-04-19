Java Project Semester 2

Sam Courtney: C22314173

Andrea Luca: C22390831

Search Engine

## Overview

This java code when run, opens a GUI and allows a user to enter a word or phrase that they would like to find in some files of
their chosing. The user then clicks a button to open up their file manager and selects files they want to search.
The files are then searched, line by line, taking a count of how may times the search word/ phrase has been found
and printing the lines the word/ phrase was found in to a panel in the GUI.

The program then calculates the ranking based on relevance. The ranking considers factors such as the frequency of the search term 
in each file and the length of the text file. This ranking score helps prioritize the files with the most relevant matches.

At the end of the program the total number of occurences is printed to the screen along with the file path. 
It also returns the percentage of matches found in each file and file names are returned in the order of highest ranking score.
This returns the file with the most relevant matches first and lowest last.

User can choose to save this information into a file or clear the output panel.


## Files Included

Control.java : Contains the main control class to manage GUI and file processing.

FileProcessor.java : Handles the searching of the word within the text files and ranking.

FileSelector.java : Provides a method for selecting multiple files.

Gui.java : Implements the graphical user interface using java swing components.

## How To Use

1. Download the files
2. Compile the files
3. Enter a search word into the box provided
4. Click the "Click me to access files" button
5. Upload txt files you wish to search
6. Wait for files to be searched
7. Files will be ranked in the last panel the the bottom of the GUI
8. Press the save button to save information into a new file.
9. Press the clear button to clear the output panel for a fresh search.
