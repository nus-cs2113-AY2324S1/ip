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

                switch (split[0]) {
                case "T":
                    Todo t = new Todo(split[2]);
                    t.setCompleted(split[1].equals("1"));
                    herbert.addTask(t);
                    break;
                case "D":
                    Deadline d = new Deadline(split[2], split[3]);
                    d.setCompleted(split[1].equals("1"));
                    herbert.addTask(d);
                    break;
                case "E":
                    Event e = new Event(split[2], split[3], split[4]);
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

            writer.write(s.toString());
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // TODO: Remove task from save file
    // TODO: Update task status in save file
}
