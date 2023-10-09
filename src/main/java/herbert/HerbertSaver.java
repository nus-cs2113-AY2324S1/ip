package herbert;

import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;

/**
 * This class deals with the local storage of the Herbert TaskList to disk.
 */
public class HerbertSaver {

    private final Path folderPath;
    private final Path filePath;

    /**
     * Constructor for the HerbertSaver class. Automatically creates a save file located at the given folder path
     * and file name if it does not already exist.
     * @param folderPath Path to the folder which the save file is located in.
     * @param fileName The name of the save file e.g. "HerbertTasks.txt"
     */
    public HerbertSaver(String folderPath, String fileName) {
        // Convert the paths given as Strings into Path objects
        this.folderPath = Paths.get(folderPath);
        this.filePath = this.folderPath.resolve(fileName);

        this.createSaveFileIfNotExists(this.folderPath, this.filePath);
    }

    private void createSaveFileIfNotExists(Path folderPath, Path filePath) {
        try {
            // Create directory if it doesn't exist
            if (!Files.exists(folderPath)) {
                Files.createDirectories(folderPath);
            }

            // Create file if it doesn't exist
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reads and parses the save file line-by-line to re-populate the chatbot TaskList with previously saved tasks.
     * @param herbert The current instance of the chatbot to add saved tasks to.
     */
    public void loadFromSaveFile(Herbert herbert) {
        try {
            BufferedReader reader = Files.newBufferedReader(filePath);
            String line = reader.readLine();

            while (line != null) {
                String[] split = line.split(" \\| ");

                String description;
                switch (split[0]) {
                case "T":
                    Todo t = new Todo(split[2]);
                    t.setCompleted(split[1].equals("1"));
                    herbert.addTask(t);
                    break;
                case "D":
                    description = split[2];
                    LocalDate dueDate = HerbertParser.parseDate(split[3]);
                    if (dueDate == null) {
                        return;
                    }

                    Deadline d = new Deadline(description, dueDate);
                    d.setCompleted(split[1].equals("1"));
                    herbert.addTask(d);
                    break;
                case "E":
                    description = split[2];
                    LocalDate fromDate = HerbertParser.parseDate(split[3]);
                    LocalDate toDate = HerbertParser.parseDate(split[4]);
                    if (fromDate == null || toDate == null) {
                        return;
                    }

                    Event e = new Event(description, fromDate, toDate);
                    e.setCompleted(split[1].equals("1"));
                    herbert.addTask(e);
                    break;
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Adds a new task to the save file in a standard encoding.
     * @param t The task to be encoded into the save file.
     */
    public void addTaskToSaveFile(Task t) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                    this.filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.APPEND
            );

            String s = this.buildTaskString(t);
            writer.write(s);
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Rewrites the entire save file with all the tasks in a given task list.
     * @param taskList The task list to override the save file with.
     */
    public void rewriteSaveFile(TaskList taskList) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(
                    this.filePath,
                    StandardCharsets.UTF_8,
                    StandardOpenOption.WRITE
            );
            for (int i = 0; i < taskList.size(); i++) {
                String s = this.buildTaskString(taskList.get(i));
                writer.write(s);
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encodes a given task into the format required for the save file.
     * @param t The task to be encoded.
     * @return The string encoding of the task in the standard format.
     */
    private String buildTaskString(Task t) {
        StringBuilder s = new StringBuilder(String.format(
                "%s | %s | %s",
                t.getCode(),
                t.isCompleted() ? "1" : "0",
                t.getDescription()
        ));
        if (t instanceof Deadline) {
            s.append(String.format(" | %s", ((Deadline) t).getDueDate()));
        } else if (t instanceof Event) {
            Event e = (Event) t;
            s.append(String.format(" | %s | %s", e.getFrom(), e.getTo()));
        }
        return s.toString();
    }
}
