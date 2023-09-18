package linguobot.file;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class taskFile{
    public static ArrayList<Task> loadTasksFromFile(String filePath) {
        ArrayList<Task> loadedTasks = new ArrayList<>();

        try (Scanner scanner = new Scanner(new File(filePath))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Parse each line to create a Task and add it to loadedTasks
                Task task = parseTaskFromString(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filePath);
        }
        return loadedTasks;
    }
    public static Task parseTaskFromString(String taskString) {
        String[] parts = taskString.split("\\s*\\|\\s*");
        String taskType = parts[0];
        switch (taskType) {
        case "T":
            if (parts.length >= 3) {
                return new Todo(parts[2]);
            }
            break;
        case "D":
            if (parts.length >= 4) {
                return new Deadline(parts[2], parts[3]);
            }
            break;
        case "E":
            if (parts.length >= 4) {
                if (parts[3].contains("to")) {
                    String[] eventParts = parts[3].split("to");
                    return new Event(parts[2], eventParts[0], eventParts[1]);
                }
            }
            break;
        default:
            // Invalid task type, return null
            break;
        }
        return null;
    }


    public static void saveTaskListToFile(String filePath, ArrayList<Task> taskList) {
        try (FileWriter taskFile = new FileWriter(filePath)) {
            for (Task task : taskList) {
                String taskString = task.toFileString();
                taskFile.write(taskString);
                taskFile.write(System.lineSeparator());
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void printFileContents(String filePath) throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            System.out.println(s.nextLine());
        }
    }

}
