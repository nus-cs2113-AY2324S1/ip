package luke.files;

import luke.tasks.Task;
import luke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Storage Class handles the reading from and writing task data to a file.
 */
public class Storage {
    protected String filePath;
    protected String folderPath;
    private ArrayList<Task> tasks;

    /**
     * Constructs a Storage object with the specified file path. If the file exists,
     * it retrieves tasks from the file. If the file does not exist, it creates a new file.
     *
     * @param folderPath The path to the folder where the memory file is located.
     */
    public Storage(String folderPath) {
        this.folderPath = folderPath;
        filePath = folderPath + "/memory.txt";

        System.out.println();
        createDirectory();
        createFile();
    }

    /**
     * Creates a directory at the specified folder path if it does not already exist.
     */
    public void createDirectory () {
        File d = new File(folderPath);
        if (d.mkdir()) {
            System.out.println("\tDirectory has been successfully created!");
        } else {
            System.out.println("\tDirectory cannot be created. Directory may already exist.");
        }
        //System.out.println("\tFull path: " + d.getAbsolutePath());
    }

    /**
     * Creates a memory file at the specified file path if it does not already exist.
     */
    public void createFile () {
        File f = new File(filePath);
        try {
            if (f.createNewFile()) {
                System.out.println("\tNew memory file has been successfully created!");
            } else {
                System.out.println("\tNew memory file cannot be created. Memory file may already exist.");
            }
        } catch (IOException e) {
            System.out.println("\t☹ An error occurred." + e.getMessage());
        }
        System.out.println("\tFull path: " + f.getAbsolutePath());
    }

    /**
     * Loads and returns the ArrayList of Task objects retrieved from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        try {
            tasks = Memory.readMemory(filePath);
            return tasks;
        } catch (FileNotFoundException e) {
            System.out.println("\t☹ An error occurred." + e.getMessage());
        }
        return null;
    }

    /**
     * Stores the main task list to a specified file path.
     *
     * @param tasksToStore The TaskList object containing the main task list.
     */
    public void store(TaskList tasksToStore) {
        //store in memory.txt
        Memory.storeMemory(filePath, tasksToStore.getMainTaskList());

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
