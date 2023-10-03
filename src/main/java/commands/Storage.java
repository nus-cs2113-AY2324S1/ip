package commands;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {

    private final String dukeDataFile;
    private int taskListSize;

    public Storage(String dukeDataFile){
        this.dukeDataFile = dukeDataFile;
//        this.taskListSize = taskListSize;
    }

    public void addTaskSize(int size){
        this.taskListSize = size;
    }

    public void addToFile(String textToAdd) throws IOException {
        FileWriter fw = new FileWriter(this.dukeDataFile,true);
        // Create a BufferedWriter for efficient writing
        BufferedWriter bufferedWriter = new BufferedWriter(fw);
        bufferedWriter.newLine();
        bufferedWriter.write(textToAdd);
        bufferedWriter.close();
        fw.close();
        this.taskListSize += 1;
    }

    public void updateMark(int lineToEdit, int action){

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
                    if(lineNumber == this.taskListSize){
                        break;
                    }
                    writer.newLine(); // Add a new line after writing
                }else{
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

    public void removeFromFile(int lineToRemove){

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
                    writer.write(currentLine);
                    //if it's the last item, we don't add a newLine to the next line
                    if(lineNumber == this.taskListSize){
                        break;
                    }
                    writer.newLine(); // Add a new line after writing
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
                System.out.println(currentLine);
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
