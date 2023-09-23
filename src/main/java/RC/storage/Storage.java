package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private final Path path = Paths.get("data");
    private static final String MESSAGE_DIRECTORY_MISSING = "\tDirectory doesn't exist. Creating directory...";

    private static void createDirectory(Path path, Ui ui) throws IOException {
        ui.showMessage(MESSAGE_DIRECTORY_MISSING);
        Files.createDirectory(path);
    }

    public Storage(String filePath, Ui ui) {
        if (!Files.exists(path)) {
            try {
                createDirectory(path, ui);
            } catch (IOException e) {
                ui.showMessage("\tError: " + e.getMessage());
            }
        }
    }

    public void save(TaskList tasks) throws RCException {
        SaveTasks.save(tasks);
    }

    public void load(TaskList taskList, Ui ui) throws RCException {
        LoadTasks.load(taskList, ui);
    }
}
