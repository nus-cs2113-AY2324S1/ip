import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Storage {
    private static String filePath;
    public Storage(String filePath) {
        Storage.filePath =filePath;
    }

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
            System.out.println("En: Error reading tasks from the data file.");
        }
        return tasks;
    }


    public static void saveTasks(List<Task> tasks) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
            for (Task task : tasks) {
                writer.write(task.toString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("En: Error saving tasks to the file.");
        }
    }

    private static Task createTaskFromFile(String line) {
        String[] parts = line.split(" ");

        if (parts.length < 3) {
            System.out.println("En: Skipped a line due to incorrect format (not enough components): " + line);
            return null;
        }

        String taskType = parts[0].substring(1, 2);
        int isDone = parts[1].equals("[X]") ? 1 : 0;
        String description = parts[2];

        Task task;

        switch (taskType) {
            case "T":
                task = new ToDo(description, isDone == 1);
                break;
            case "D":
                if (parts.length < 4) {
                    System.out.println("En: Skipped a line due to incorrect Deadline format: " + line);
                    return null;
                }
                String deadline = parts[3].trim();
                task = new Deadline(description, deadline, isDone == 1);
                break;
            case "E":
                if (parts.length < 5) {
                    System.out.println("En: Skipped a line due to incorrect Event format: " + line);
                    return null;
                }
                String from = parts[3].trim();
                String to = parts[4].trim();
                task = new Event(description, from, to, isDone == 1);
                break;
            default:
                System.out.println("En: Skipped a line due to unknown task type: " + line);
                return null;
        }
        return task;
    }
}
