package duke;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Handles the loading and saving of tasks from/to a data file.
 */
public class Storage {
    private String filePath;

    /**
     * Creates a Storage object with the specified file path.
     *
     * @param filePath The path to the data file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Load tasks from the data file when Matinbot starts.
     *
     * @return An ArrayList containing the loaded tasks.
     * @throws DukeException If there is an error loading tasks from the file.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (file.exists()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while ((line = reader.readLine()) != null) {
                    Task task = Task.fromFileString(line);
                    tasks.add(task);
                }
                reader.close();
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Save tasks to the data file.
     *
     * @param tasks The ArrayList of tasks to be saved.
     */
    public void saveTasks(ArrayList<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                if (task instanceof Todo) {
                    writer.write(((Todo) task).toFileString());
                } else if (task instanceof Deadline) {
                    writer.write(((Deadline) task).toFileString());
                } else if (task instanceof Event) {
                    writer.write(((Event) task).toFileString());
                }
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }
}