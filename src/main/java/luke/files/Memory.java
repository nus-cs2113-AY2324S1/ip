package luke.files;

import luke.user.LukeException;
import luke.tasks.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Memory Class provides methods for reading from and storing tasks to a file.
 */
public class Memory {

    /**
     * Reads tasks from a specified file and returns them as an ArrayList of Task objects.
     *
     * @param filePath The path to the file to read tasks from.
     * @return An ArrayList of Task objects read from the file.
     * @throws FileNotFoundException If the specified file is not found.
     */
    public static ArrayList<Task> readMemory(String filePath) throws FileNotFoundException {
        System.out.println("\n\tRetrieving memory...");

        ArrayList<Task> tasks = new ArrayList<>();

        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String currentLine = s.nextLine();
            System.out.println("\t" + currentLine);

            String taskDetails = currentLine.substring(0,5);
            String taskDescription = currentLine.substring(7);
            char[] characters = taskDetails.toCharArray();
            try {
                Task newTask;
                switch (characters[1]) {
                    case 'T':
                        newTask = new Todo(taskDescription);
                        break;
                    case 'D':
                        newTask = new Deadline(taskDescription);
                        break;
                    case 'E':
                        newTask = new Event(taskDescription);
                        break;
                    default: //memory.txt is corrupted/in the wrong format
                        newTask = new Todo("error");
                        break;
                }
                switch (characters[4]) {
                    case 'X':
                        newTask.setDone(true);
                        break;
                    case ' ':
                        newTask.setDone(false);
                        break;
                }
                tasks.add(newTask);
            } catch (LukeException e) {
                System.out.println("\t☹ An error occurred." + e.getMessage());
            }
        }
        System.out.println("\tMemory retrieval successful!\n");
        return tasks;
    }

    /**
     * Stores a list of Task objects to a specified file.
     *
     * @param filePath  The path to the file to store tasks in.
     * @param taskList  The ArrayList of Task objects to be stored.
     */
    public static void storeMemory(String filePath, ArrayList<Task> taskList){
        try {
            FileWriter fw = new FileWriter(filePath); //overwrite file

            for (Task currentTask : taskList) {
                fw.write(currentTask.memoryString() + "\n");
            }

            fw.close();

            System.out.println("\tMemory Stored Safely!");

        } catch (IOException e) {
            System.out.println(("\t☹ An error occurred." + e.getMessage()));
        }

    }
}