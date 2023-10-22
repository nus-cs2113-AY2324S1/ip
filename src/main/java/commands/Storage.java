package commands;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The `Storage` class handles the storage and retrieval of task data from a specified text file.
 */
public class Storage {

    private final String dukeDataFile;
    private int taskListSize;

    public Storage(String dukeDataFile) {
        this.dukeDataFile = dukeDataFile;
    }

    public void addTaskSize(int size) {
        this.taskListSize = size;
    }

    /**
     * This method takes in the path of a txt file and adds 'textToAdd' to the last line
     * of the file
     *
     * @param textToAdd String to be added to the end of the txt file
     */
    public void addToFile(String textToAdd) {
        try {
            // Create a FileWriter object with the specified file path in append mode (true).
            FileWriter fileWriter = new FileWriter(this.dukeDataFile, true);

            // Create a BufferedWriter to efficiently write text.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            // Write the text to the file.
            bufferedWriter.write(textToAdd);

            // Write a newline character to separate lines.
            bufferedWriter.newLine();

            // Close the BufferedWriter to release resources.
            bufferedWriter.close();

            this.taskListSize += 1;

            //System.out.println("Text added to the file successfully.");
        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Updates a specific line in the file denoted by `dukeDataFile` without creating a new file.
     *
     * @param lineToEdit The line number to be edited (1-based index).
     * @param action     The new action value to set in the line (0 or 1).
     */
    public void updateMark(int lineToEdit, int action) {
        ArrayList<String> modifiedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile))) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                if (lineNumber == lineToEdit) {
                    // Modify the line if it's the one to edit
                    char[] charArray = currentLine.toCharArray();
                    charArray[2] = (char) (action + '0');
                    modifiedLines.add(new String(charArray));
                } else {
                    // Keep the original line if it's not the one to edit
                    modifiedLines.add(currentLine);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.dukeDataFile))) {
            // Write the modified lines back to the original file
            for (String line : modifiedLines) {
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Line edited successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
        }
    }

    /**
     * Takes in the location of a txt file and deletes the specified line from the file
     *
     * @param lineToDelete line number to delete from txt file
     */
    public void removeFromFile(int lineToDelete) {
        try {
            // Read the existing content of the file into memory.
            FileReader fileReader = new FileReader(this.dukeDataFile);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            List<String> fileContent = new ArrayList<>();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContent.add(line);
            }

            this.taskListSize -= 1;

            // Close the BufferedReader to release resources.
            bufferedReader.close();

            // Remove the specified line from the list.
            fileContent.remove(lineToDelete - 1);

            // Write the modified content back to the file.
            FileWriter fileWriter = new FileWriter(this.dukeDataFile);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (String contentLine : fileContent) {
                bufferedWriter.write(contentLine);
                bufferedWriter.newLine(); // Add a newline character after each line.
            }

            // Close the BufferedWriter to release resources.
            bufferedWriter.close();

        } catch (IOException e) {
            System.out.println("An IOException occurred: " + e.getMessage());
        }
    }

    /**
     * Reads task data from a file and returns an ArrayList of tasks.
     * <p>
     * This method reads task data from a specified file and parses it into an ArrayList
     * of Task objects. Each line in the file represents a task, and the format of each
     * line should be as follows:
     * <p>
     * - "T|0|description" for a ToDo task
     * - "D|0|description|deadline" for a Deadline task
     * - "E|0|description|eventDate|eventTime" for an Event task
     * <p>
     * The first character in each line specifies the task type (T for ToDo, D for Deadline,
     * E for Event), followed by a status indicator (0 for incomplete, 1 for completed),
     * and then task-specific details separated by '|' characters.
     *
     * @return An ArrayList of Task objects containing the tasks read from the file.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> getTaskData() throws IOException {
        File f = new File(dukeDataFile);
        if (!f.exists()) {
            return new ArrayList<>();
        }
        ArrayList<Task> currentArray = new ArrayList<>();

        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            String[] wordArray = currentLine.split("\\|");
            switch (wordArray[0]) {
            case "T": {
                ToDo currTask = new ToDo(wordArray[2]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            case "D": {
                Deadline currTask = new Deadline(wordArray[2], wordArray[3]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            case "E": {
                Event currTask = new Event(wordArray[2], wordArray[3], wordArray[4]);
                if (Integer.parseInt(wordArray[1]) == 1) {
                    currTask.markStatus();
                }
                currentArray.add(currTask);
                break;
            }
            default: {
                System.out.println("Should not be here");
                break;
            }
            }
        }
        return currentArray;
    }

}


