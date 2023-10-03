package elgin.storage;

import elgin.exception.DukeException;
import elgin.task.Deadline;
import elgin.task.Event;
import elgin.task.Task;
import elgin.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

import static elgin.parser.Parser.isValidFromToDateTime;
import static elgin.parser.Parser.parseDateTime;

public class Storage {

    private static final String DONE_VALUE = "1";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String SEPARATOR = " | ";
    private final String TASK_FILE_DIRECTORY_PATH;
    private final String TASK_FILE_PATH;

    /**
     * Constructor for Storage.
     * Initialise the saved tasks file directory and path.
     */
    public Storage() {
        TASK_FILE_DIRECTORY_PATH = Path.of("data").toAbsolutePath().toString();
        TASK_FILE_PATH = Path.of("data/tasks.txt").toAbsolutePath().toString();
    }

    /**
     * Checks and create directory if not exist.
     *
     * @param dirPath Directory of the saved tasks file.
     */
    public static void checkAndCreateDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    /**
     * Check and create saved tasks file if not exist.
     *
     * @param filePath Path of the saved tasks file.
     * @throws IOException If the task file fails to create.
     */
    public static void checkAndCreateFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new IOException(e.getMessage());
            }
        }
    }

    /**
     * Loads saved tasks from file into arraylist.
     *
     * @return ArrayList of tasks
     * @throws IOException If error reading the file.
     * @throws DukeException If error parsing the task in the file.
     */
    public ArrayList<Task> getSavedTasks() throws IOException, DukeException {
        checkAndCreateDirectory(TASK_FILE_DIRECTORY_PATH);
        checkAndCreateFile(TASK_FILE_PATH);

        ArrayList<Task> savedTasks = new ArrayList<>();
        File file = new File(TASK_FILE_PATH);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNext()) {
                String taskLine = scanner.nextLine();
                Task newTask = parseTask(taskLine);
                if (newTask != null) {
                    savedTasks.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException(e.getMessage());
        }

        return savedTasks;
    }

    /**
     * Parse Task from line in saved file as Todo/Deadline/Event.
     *
     * @param taskLine Line containing string represenation of the task.
     * @return Task if valid, else null.
     * @throws DukeException If task cannot be parsed.
     */
    public Task parseTask(String taskLine) throws DukeException {
        String[] taskInfo = taskLine.split(" \\| ");
        switch (taskInfo[0]) {
        case "T":
            if (taskInfo.length != 3) {
                return null;
            }
            return new Todo(taskInfo[2], taskInfo[1].equals(DONE_VALUE));
        case "D":
            if (taskInfo.length != 4) {
                return null;
            }
            return new Deadline(taskInfo[2], parseDateTime(taskInfo[3]), taskInfo[1].equals(DONE_VALUE));
        case "E":
            if (taskInfo.length != 5) {
                return null;
            }
            LocalDateTime from = parseDateTime(taskInfo[3]);
            LocalDateTime to = parseDateTime(taskInfo[4]);
            isValidFromToDateTime(from, to);
            return new Event(taskInfo[2], from, to, taskInfo[1].equals(DONE_VALUE));
        default:
            return null;
        }

    }

    private void deleteFileContent(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private void appendToFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Save tasks as string representation to file
     *
     * @param tasks ArrayList of tasks to be saved.
     */
    public void saveToFile(ArrayList<Task> tasks) {
        deleteFileContent(TASK_FILE_PATH);
        for (Task task : tasks) {
            String taskAsString = getTaskAsString(task);
            appendToFile(TASK_FILE_PATH, taskAsString);
        }
    }

    /**
     * Gets task as string representation to be saved in file.
     *
     * @param task Task to be converted to string representation.
     * @return String representation of Task if valid task, else return empty string.
     */
    public String getTaskAsString(Task task) {
        switch (task.getType()) {
        case "T":
            return task.getType() + SEPARATOR + task.getIsDoneAsOneOrZero()
                    + SEPARATOR + task.getDescription() + NEW_LINE;
        case "D":
            Deadline d = (Deadline) task;
            return d.getType() + SEPARATOR + d.getIsDoneAsOneOrZero()
                    + SEPARATOR + d.getDescription() + SEPARATOR + d.getBy() + NEW_LINE;
        case "E":
            Event e = (Event) task;
            return e.getType() + SEPARATOR + e.getIsDoneAsOneOrZero()
                    + SEPARATOR + e.getDescription() + SEPARATOR + e.getFrom()
                    + SEPARATOR + e.getTo() + NEW_LINE;
        default:
            return "";
        }
    }

}
