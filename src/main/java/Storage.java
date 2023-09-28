import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The Storage class handles reading and writing tasks to/from a file.
 */
public class Storage {
    private static String filePath;

    /**
     * Creates a new Storage instance with the specified file path.
     *
     * @param filePath The path to the data file where tasks are stored.
     */
    public Storage(String filePath) {
        Storage.filePath = filePath;
    }

    /**
     * Loads tasks from the data file.
     *
     * @return A list of tasks loaded from the data file.
     */
    public static List<Task> loadTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = createTaskFromFile(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            System.out.println("Ahnge: Error reading tasks from the data file.");
        }
        return tasks;
    }

    /**
     * Saves tasks to the data file.
     *
     * @param tasks The list of tasks to be saved to the data file.
     */
    public static void saveTasks(List<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Ahnge: Error saving tasks to the file.");
        }
    }

    /**
     * Creates a task object from a line of text in the data file.
     *
     * @param line A line of text representing a task in the data file.
     * @return The Task object created from the input line, or null if the line format is incorrect.
     */
    private static Task createTaskFromFile(String line) {

        String taskType = null;
        boolean isDone = false;
        String description = null;
        String additionalInfo = "";

        if (line.startsWith("[T]")) {
            taskType = "T";
            isDone = line.charAt(4) == 'X';
            description = line.substring(7);
        } else if (line.startsWith("[D]")) {
            taskType = "D";
            isDone = line.charAt(4) == 'X';
            int byIndex = line.indexOf("(by:");
            if (byIndex != -1) {
                description = line.substring(7, byIndex).trim();
                additionalInfo = line.substring(byIndex + 4, line.length() - 1).trim();
            } else {
                System.out.println("Ahnge: Skipped a line due to incorrect Deadline format: " + line);
                return null;
            }
        } else if (line.startsWith("[E]")) {
            taskType = "E";
            isDone = line.charAt(4) == 'X';

            int fromIndex = line.indexOf("(from:");
            if (fromIndex != -1) {
                description = line.substring(7, fromIndex).trim();
                additionalInfo = line.substring(fromIndex + 4, line.length() - 1).trim();
            } else {
                System.out.println("Ahnge: Skipped a line due to incorrect Event format: " + line);
                return null;
            }
        }

        Task task = null;
        if (taskType != null) {
            switch (taskType) {
                case "T":
                    task = new ToDo(description, isDone);
                    break;
                case "D":
                    task = new Deadline(description, additionalInfo, isDone);
                    break;
                case "E":
                    task = new Event(description, additionalInfo, isDone);
                    break;
                default:
                    // Handle unknown task type
                    task = null;
                    break;
            }
        }
        return task;
    }
}
