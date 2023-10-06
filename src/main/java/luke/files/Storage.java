package luke.files;

import luke.tasks.Task;
import luke.tasks.TaskList;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The Storage Class handles the reading and writing of task data from/to a file.
 */
public class Storage {
    private ArrayList<Task> tasks;

    /**
     * Constructs a Storage object with the specified file path. If the file exists,
     * it retrieves tasks from the file. If the file doesn't exist, it creates a new file.
     *
     * @param filePath The path to the file where task data is stored.
     */
    public Storage(String filePath) {
        File taskListFile = new File(filePath);
        if (taskListFile.exists()) {
            try {
                System.out.println("\n\tRetrieving memory...\n");
                tasks = Memory.readMemory(filePath);
                System.out.println("\n\tMemory retrieval successful!\n");
            } catch (FileNotFoundException e) {
                System.out.println("\n\tNo existing memory. (1)\n");
            }
        } else {
            try {
                if (taskListFile.createNewFile()) {
                    System.out.println("\n\tNo existing memory. Creating new memory...\n");
                }
            } catch (IOException e) {
                System.out.println("\n\tIOException.\n");
            } catch (SecurityException e) {
                System.out.println("\n\tSecurityException.\n");
            }
        }
    }

    /**
     * Loads and returns the ArrayList of Task objects retrieved from the file.
     *
     * @return An ArrayList of Task objects loaded from the file.
     */
    public ArrayList<Task> load() {
        return tasks;
    }

    /**
     * Stores the main task list to a specified file path.
     *
     * @param tasksToStore The TaskList object containing the main task list.
     */
    public void store(TaskList tasksToStore) {
        //store in memory.txt
        Memory.storeMemory("./out/artifacts/ip_jar/memory.txt", tasksToStore.getMainTaskList());

        System.out.println("\tBye. Hope to see you again soon!");
    }
}
