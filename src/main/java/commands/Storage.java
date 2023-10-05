package commands;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

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
     * Adds a new task to the data file and increments the task list size.
     *
     * <p>This method appends the specified textToAdd to the end of the data file, creating
     * a new task entry. It uses a {@code FileWriter} to write the text efficiently, adds a
     * newline character before writing the new entry, and then closes the writer. After adding
     * the task, it increments the task list size.
     * </p>
     *
     * @param textToAdd The text representing the new task to be added to the data file.
     * @throws IOException If there is an error while writing to the data file.
     */
    public void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.dukeDataFile, true);
        // Create a BufferedWriter for efficient writing
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile));
        //we only append newline if the file is not empty
        if (reader.readLine() != null) {
            bufferedWriter.newLine();
        }
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
        this.taskListSize += 1;
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
     * Removes a specific line from the file denoted by `dukeDataFile` without creating a new file.
     *
     * @param lineToRemove The line number to be removed (1-based index).
     */
    public void removeFromFile(int lineToRemove) {
        ArrayList<String> modifiedLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile))) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                if (lineNumber == lineToRemove) {
                    // Skip the line to remove
                    continue;
                }

                modifiedLines.add(currentLine);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
            return;
        }
        this.taskListSize -= 1;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.dukeDataFile))) {
            int oldTaskListSize = this.taskListSize + 1;
            int lineNumber = 0;
            // Write the modified lines back to the original file
            for (String line : modifiedLines) {
                lineNumber++;
                writer.write(line);
                if (lineNumber != this.taskListSize ) {
                    if(lineToRemove == oldTaskListSize  && lineNumber + 1 == lineToRemove){
                        continue;
                    }else{
                        writer.newLine(); // Add a new line after writing
                    }
                }
            }
            System.out.println("Line removed successfully.");
        } catch (IOException e) {
            System.err.println("Error writing to the file: " + e.getMessage());
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
