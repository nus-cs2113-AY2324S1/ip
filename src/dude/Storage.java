package dude;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The `Storage` class manages file input and output operations, responsible for
 * saving tasks to a file and loading tasks from a file.
 */
public class Storage {

    private final String filePath;

    /**
     * Constructs a `Storage` instance with the specified file path.
     *
     * @param filePath The path to the file used for storing tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Checks if the directory and file specified by the file path exist. If they
     * don't exist, this method creates the necessary directory and file.
     */
    public void setupFile() {
        // Check if the directory exists, if not, create it
        File file = new File(filePath);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        // Check if the file exists, if not, create it
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Error creating file: " + e.getMessage());
            }
        }
    }

    /**
     * Loads tasks from the file specified by the file path and returns them as an
     * ArrayList of tasks.
     *
     * @return An ArrayList containing the tasks loaded from the file.
     */
    public ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            Scanner fileReader = new Scanner(file);

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                char taskType = line.charAt(0);
                switch (taskType) {
                case 'T':
                    tasks.add(Task.fromFileFormat(line));
                    break;
                case 'D':
                    tasks.add(Deadline.fromFileFormat(line));
                    break;
                case 'E':
                    tasks.add(Event.fromFileFormat(line));
                    break;
                }
            }

            fileReader.close();
        } catch (IOException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
        return tasks;
    }

    /**
     * Saves the provided ArrayList of tasks to the file specified by the file
     * path. Each task is written to a separate line in the file.
     *
     * @param tasks The ArrayList of tasks to be saved to the file.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
}
