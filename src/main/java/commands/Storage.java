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
        if(reader.readLine() != null){
            bufferedWriter.newLine();
        }
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
        this.taskListSize += 1;
    }

    /**
     * Updates the status mark of a task in the data file.
     *
     * <p>This method modifies the status mark of a task at the specified line index (lineToEdit)
     * in the data file. It takes an integer action as a parameter, which is used to determine
     * the new status mark value. It creates a temporary file to write the modified content, reads
     * the original file line by line, updates the status mark of the specified line, and replaces
     * the original file with the temporary file.
     * </p>
     *
     * <p>The action parameter should be an integer value that represents the new status mark value.
     * Typically, 0 represents an incomplete task, and 1 represents a completed task, but this may
     * vary depending on your application's conventions.
     * </p>
     *
     * @param lineToEdit The index of the line representing the task to be edited in the data file.
     * @param action     An integer value indicating the new status mark (e.g., 0 for incomplete, 1 for completed).
     */
    public void updateMark(int lineToEdit, int action) {

        // Create a temporary file to write the modified content
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                // Check if the current line is the one to remove
                if (lineNumber != lineToEdit) {
                    writer.write(currentLine);
                    //if it's the last item, we don't add a newLine to the next line
                    if (lineNumber == this.taskListSize) {
                        break;
                    }
                    writer.newLine(); // Add a new line after writing
                } else {
                    char[] charArray = currentLine.toCharArray();
                    //to ensure that it is not converted to 'a'
                    charArray[2] = (char) (action + '0');
                    writer.write(new String(charArray));
                    writer.newLine();
                }

            }
        } catch (IOException e) {
            System.err.println("Error reading/writing the file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (tempFile.renameTo(new File(dukeDataFile))) {
            System.out.println("Line edited successfully.");
        } else {
            System.err.println("Error renaming the temporary file.");
        }

    }

    /**
     * Removes a specified line from the data file and updates the task list size.
     *
     * <p>This method removes the line at the specified index (lineToRemove) from the data file
     * and updates the task list size accordingly. It creates a temporary file to write the modified
     * content, reads the original file line by line, and skips the line to remove. After processing,
     * it replaces the original file with the temporary file.
     * </p>
     *
     * @param lineToRemove The index of the line to remove from the data file.
     */
    public void removeFromFile(int lineToRemove) {

        // Create a temporary file to write the modified content
        File tempFile = new File("temp.txt");

        try (
                BufferedReader reader = new BufferedReader(new FileReader(this.dukeDataFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));
        ) {
            String currentLine;
            int lineNumber = 0;

            while ((currentLine = reader.readLine()) != null) {
                lineNumber++;

                // Check if the current line is the one to remove
                if (lineNumber != lineToRemove) {
                    //if it's the last item, we don't add a newLine to the next line

                    writer.write(currentLine);
                    if (lineNumber != this.taskListSize && (lineNumber + 1) != lineToRemove) {
                        writer.newLine(); // Add a new line after writing
                    }

                }

            }
            this.taskListSize -= 1;
        } catch (IOException e) {
            System.err.println("Error reading/writing the file: " + e.getMessage());
        }

        // Replace the original file with the temporary file
        if (tempFile.renameTo(new File(dukeDataFile))) {
            System.out.println("Line removed successfully.");
        } else {
            System.err.println("Error renaming the temporary file.");
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
