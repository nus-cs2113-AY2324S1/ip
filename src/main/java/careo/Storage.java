package careo;

import java.util.ArrayList;

public class Storage {
    /** Where the persistence file (relative path) is stored */
    private String filePath;

    /**
     * Instantiates a Storage instance with access to a persistence file.
     *
     * @param filePath Where the persistence file (relative path) is stored.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads and returns stored tasks from the persistence file.
     *
     * @return The tasks stored in the persistence file.
     * @throws StorageLoadException When filePath doesn't point to a file.
     */
    public ArrayList<Task> load() throws StorageLoadException {
        ArrayList<Task> tasks = new ArrayList<>();

        throw new StorageLoadException();

        // return tasks;
    }

    /**
     * Saves tasks to a persitence file.
     *
     * @param tasks The TaskList containing the tasks to be saved.
     */
    public void save(TaskList tasks) {

    }
}
