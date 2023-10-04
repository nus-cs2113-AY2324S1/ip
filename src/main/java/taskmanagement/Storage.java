package taskmanagement;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles the storing of tasks in the Zran application.
 * Manages the initialization, loading, and saving of tasks to a user specified file.
 */
public class Storage {
    /**
     * The default folder path for storing data files.
     */
    private static final String FOLDER_PATH = "./data";
    private String filePath;

    /**
     * Constructs an instance of Storage with the user specified file path.
     *
     * @param filePath The file path for saving and loading of tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Creates the necessary folder and file if they do not exist.
     *
     * @throws IOException If an I/O error occurs during initialization.
     */
    public void init() throws IOException {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists()) {
            folder.mkdirs();
        }

        File data = new File(filePath);
        if (!data.exists()) {
            if (!data.createNewFile()) {
                throw new IOException("Unable to create a new file.");
            }
        }
    }

    /**
     * Loads tasks from user specified file.
     *
     * @return An ArrayList of tasks loaded from the storage file.
     * @throws IOException If an I/O error occurs during loading.
     */
    public ArrayList<Task> load() throws IOException {
        init();
        ArrayList<Task> storedTasks = new ArrayList<>();
        File dataFile = new File(filePath);
        try (Scanner scanner = new Scanner(dataFile);) {
            while (scanner.hasNext()) {
                String data = scanner.nextLine();
                Task task = parseData(data);
                if (task != null) {
                    storedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            throw new IOException("File not found: " + filePath, e);
        }
        return storedTasks;
    }

    /**
     * Parses data from a string and creates a Task object.
     * Used in loading stored tasks.
     *
     * @param data The string containing task data.
     * @return A Task object created from the parsed data.
     */
    private Task parseData(String data) {
        String[] taskData = data.split("\\|");
        String taskType = taskData[0].trim();
        String description = taskData[2].trim();
        boolean isDone = Boolean.parseBoolean(taskData[1].trim());

        Task task = null;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        switch (taskType) {
        case "T":
            task = new ToDos(description, isDone);
            break;
        case "D":
            String by = taskData[3].trim();
            LocalDate parsedBy = LocalDate.parse(by, formatter);
            task = new Deadline(description, parsedBy, isDone);
            break;
        case "E":
            String from = taskData[3].trim();
            String to = taskData[4].trim();
            LocalDate parsedFrom = LocalDate.parse(from, formatter);
            LocalDate parsedTo = LocalDate.parse(to, formatter);
            task = new Event(description, parsedFrom, parsedTo, isDone);
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Saves tasks to user specified storage file.
     *
     * @param tasks The TaskList containing tasks to be saved.
     * @throws IOException If an I/O error occurs during saving.
     */
    public void saveTasks(TaskList tasks) throws IOException {
        try (FileWriter fw = new FileWriter(filePath)) {
            for (Task task : tasks.listItems) {
                String line = toString(task);
                fw.write(line + System.lineSeparator());
            }
        }
    }

    private String toString(Task task) {
        if (task instanceof ToDos) {
            ToDos todo = (ToDos) task;
            return todo.getTaskType() + " | " + task.getStatus() + " | " + task.getDescription();
        } else if (task instanceof Deadline) {
            Deadline deadline = (Deadline) task;
            return deadline.getTaskType() + " | " + deadline.getStatus() + " | " +
                    task.getDescription() + " | " + deadline.getBy();
        } else if (task instanceof Event) {
            Event event = (Event) task;
            return event.getTaskType() + " | " + event.getStatus() + " | " +
                    task.getDescription() + " | " + event.getFrom() + " | " + event.getTo();
        }
        return "";
    }
}
