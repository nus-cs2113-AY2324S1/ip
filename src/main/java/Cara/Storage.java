package Cara;

import Tasks.Task;
import Tasks.TasksHandler;

import java.io.*;
import java.util.ArrayList;

/**
 * The Storage class is responsible for loading and saving tasks from/to a file.
 */
public class Storage {

    /**
     * Constructs a Storage instance with the specified file path (ignored).
     *
     * @param ignoredFilePath The path to the file (ignored).
     */
    public Storage(String ignoredFilePath) {
    }

    /**
     * Loads tasks from a file and returns them as an ArrayList.
     *
     * @return An ArrayList of tasks loaded from the file.
     * @throws IOException   If an I/O error occurs during file reading.
     * @throws CaraException If there is an issue with Duke while reading the file.
     */
    public ArrayList<Task> loadTasks() throws IOException, CaraException {
        ArrayList<Task> tasks = new ArrayList<>();
        TasksHandler.readFromFile(tasks); // Pass an ArrayList<Task> to the readFromFile() method
        return tasks;
    }

    /**
     * Saves tasks to a file.
     *
     * @param tasks The ArrayList of tasks to be saved to the file.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public static void saveTasks(ArrayList<Task> tasks) throws IOException {
        TasksHandler.writeToFile(tasks); // Pass the provided ArrayList<Task> to the writeToFile method
    }
}
