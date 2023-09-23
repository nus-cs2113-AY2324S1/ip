package duke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * The Storage class is responsible for managing the storage directory and file for saving and loading task data.
 * It provides methods to create and handle the storage file, as well as load and save task data from and to the file.
 */
public class Storage {

    private static File folder;
    private static File file;
    
    /**
     * Constructs a new instance of the Storage class, responsible for managing the storage
     * directory and file for saving and loading task data.
     *
     * @param filepath The file path to be used for data storage.
     * @throws DukeException If there is an issue initializing the storage directory or file.
     */
    public Storage(String filepath) throws DukeException {
        folder = new File(filepath.substring(0, filepath.lastIndexOf('/')));
        file = new File(filepath);

        try {
            if (!folder.exists()) {
                    folder.mkdirs(); // Create the "data" folder if it doesn't exist
                }

            if (!file.exists()) {
                file.createNewFile(); // Create the file "duke.txt" if it doesn't exist
            }
        } catch(IOException e) {
            throw new DukeException("Error creating the storage directory or file: " + e.getMessage(), e);
        }
    }

    /**
     * Loads tasks from the storage file and returns a TaskList containing the loaded tasks.
     *
     * @return A TaskList containing tasks loaded from the file.
     * @throws DukeException If there's an error loading tasks from the file.
     */
    public TaskList load() throws DukeException {
        TaskList tasks = new TaskList();

        try {
            FileInputStream fis = new FileInputStream(file);
            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.deserializeFromString(line); // Implement deserialization
                if (task != null) {
                    tasks.addTask(task);
                }
            }

            reader.close();
        } catch (IOException e) {
            throw new DukeException("Error loading tasks from file: " + e.getMessage(), e);
        }

        return tasks;
    }

    /**
     * Saves the tasks from the provided TaskList to the storage file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws DukeException If there's an error saving tasks to the file.
     */
    public void save(TaskList tasks) throws DukeException {
        try {
            FileOutputStream fos = new FileOutputStream(file);
            PrintWriter writer = new PrintWriter(fos);

            for(int i = 0; i < tasks.getSize(); i++) {
                String taskString = tasks.getTaskAt(i).serializeToString(); 
                writer.println(taskString);
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException("Error saving tasks to file: " + e.getMessage(), e);
        }
    }
}