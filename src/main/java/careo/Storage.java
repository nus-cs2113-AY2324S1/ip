package careo;

import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws StorageLoadException {
        ArrayList<Task> tasks = new ArrayList<>();

        throw new StorageLoadException();

        // return tasks;
    }

    public void save(TaskList tasks) {

    }
}
