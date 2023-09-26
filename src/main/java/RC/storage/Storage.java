package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Manages the storage and retrieval of tasks.
 */
public class Storage {
    private final Path path = Paths.get("data");
    private static final String MESSAGE_DIRECTORY_MISSING = "\tDirectory doesn't exist. Creating directory...";

    /**
     * Creates a new directory if the specified directory does not exist.
     *
     * @param path The directory path.
     * @param ui The user interface for displaying messages.
     * @throws IOException If the there is an issue creating directory.
     */
    private static void createDirectory(Path path, Ui ui) throws IOException {
        ui.showMessage(MESSAGE_DIRECTORY_MISSING);
        Files.createDirectory(path);
    }

    /**
     * Constructs a storage instance and creates a directory if
     * it does not exist.
     *
     * @param filePath The directory path where files are stored.
     * @param ui The user interface for displaying messages.
     */
    public Storage(String filePath, Ui ui) {
        if (!Files.exists(path)) {
            try {
                createDirectory(path, ui);
            } catch (IOException e) {
                ui.showMessage("\tError: " + e.getMessage());
            }
        }
    }

    /**
     * Saves tasks in the task list to storage file.
     *
     * @param taskList The task list containing tasks to be saved.
     * @throws RCException If there is an issue with saving tasks.
     */
    public void save(TaskList taskList) throws RCException {
        SaveTasks.save(taskList);
    }

    /**
     * Loads tasks from storage file into task list.
     *
     * @param taskList The task list to store the loaded tasks.
     * @param ui The user interface for displaying messages.
     * @throws RCException If there is an issue with loading tasks.
     */
    public void load(TaskList taskList, Ui ui) throws RCException {
        LoadTasks.load(taskList, ui);
    }
}
