package RC.storage;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;
import RC.task.Deadline;
import RC.task.Event;
import RC.task.Task;
import RC.task.Todo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Storage {
    private static final String FILE_PATH = "data/tasks.txt";
    private final Path path = Paths.get("data");
    private static final String MESSAGE_DIRECTORY_MISSING = "\tDirectory doesn't exist. Creating directory...";
    private static final String MESSAGE_WRITE_ERROR = "\tOOPS!!! Error writing to file.";
    private static final String MESSAGE_LOAD_COMPLETE = "\tLoading is complete.";
    private static final String MESSAGE_LOAD_ERROR = "\tError loading file. File might be corrupted, creating new file...";
    private static final String MESSAGE_FILE_NOT_FOUND = "\tFile not found. Creating new file...";
    private static final String MESSAGE_LOAD_FILE = "\tLoading existing file...";

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
        write(tasks);
    }

    public void load(TaskList taskList, Ui ui) throws RCException {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            ui.showMessage(MESSAGE_LOAD_FILE);

            while ((line = inputFile.readLine()) != null) {
                String[] split = line.split("\\|");
                String command = split[0].trim();
                final Task task = getTask(split, command);
                taskList.load(task);
            }
            inputFile.close();
        } catch (IOException e) {
            throw new RCException(MESSAGE_FILE_NOT_FOUND);
        } catch (IndexOutOfBoundsException e) {
            throw new RCException(MESSAGE_LOAD_ERROR);
        }

        ui.showMessage(MESSAGE_LOAD_COMPLETE);
    }

    private static Task getTask(String[] split, String command) throws RCException {
        String input;
        String isDone = split[1].trim();
        Task task;

        switch (command) {
        case "T":
            input = split[2].trim();
            task = new Todo(input);
            break;
        case "D":
            input = split[2].trim();
            String byString = split[3].trim();
            task = new Deadline(input, byString);
            break;
        case "E":
            input = split[2].trim();
            String fromString = split[3].trim();
            String toString = split[4].trim();
            task = new Event(input, fromString, toString);
            break;
        default:
            throw new RCException(MESSAGE_LOAD_ERROR);
        }

        if (isDone.equals("1")) { // anything not "1" will be counted as not done
            task.markAsDone();
        }
        return task;
    }

    public static void write(TaskList taskList) throws RCException {
        try {
            FileWriter fr = new FileWriter(FILE_PATH);
            for (Task task : taskList.tasks) {
                String text = task.formatString() + "\n";
                fr.write(text);
            }
            fr.close();
        } catch (IOException e) {
            throw new RCException(MESSAGE_WRITE_ERROR);
        }
    }
}
