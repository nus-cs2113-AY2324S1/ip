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
import java.io.IOException;

/**
 * Represents the loading of tasks from a file to the task list.
 */
public abstract class LoadTasks {
    private static final String FILE_PATH = "data/tasks.txt";
    private static final String MESSAGE_LOAD_COMPLETE = "\tLoading is complete.";
    private static final String MESSAGE_LOAD_ERROR = "\tError loading file. File might be corrupted, " +
            "creating new file...";
    private static final String MESSAGE_FILE_NOT_FOUND = "\tFile not found. Creating new file...";
    private static final String MESSAGE_LOAD_FILE = "\tLoading existing file...";

    /**
     * Loads tasks from a file into the provided task list.
     *
     * @param taskList The task list for loading tasks.
     * @param ui The user interface for displaying messages.
     * @throws RCException If there is an issue loading the file.
     */
    public static void load(TaskList taskList, Ui ui) throws RCException {
        try {
            BufferedReader inputFile = new BufferedReader(new FileReader(FILE_PATH));
            String line;
            ui.showMessage(MESSAGE_LOAD_FILE);

            while ((line = inputFile.readLine()) != null) {
                final Task task = getTask(line);

                taskList.load(task);
            }
            inputFile.close();
        } catch (IOException e) {
            throw new RCException(MESSAGE_FILE_NOT_FOUND);
        } catch (IndexOutOfBoundsException | RCException e) {
            throw new RCException(MESSAGE_LOAD_ERROR);
        }

        ui.showMessage(MESSAGE_LOAD_COMPLETE);
    }

    /**
     * Creates a todo, deadline or event task based on the input string.
     *
     * @param line The data containing the task to be loaded.
     * @return The task created from the data.
     * @throws RCException If there is an issue parsing task data or the command is not recognised.
     */
    private static Task getTask(String line) throws RCException {
        String[] split = line.split("\\|");
        String command = split[0].trim();
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
}
