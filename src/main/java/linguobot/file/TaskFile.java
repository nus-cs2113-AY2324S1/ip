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

public class TaskFile{
    public static ArrayList<Task> loadTasksFromFile() throws FileNotFoundException {
        ArrayList<Task> loadedTasks = new ArrayList<>();
        try {
            File dataDirectory = new File("./data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            File taskListFile = new File(dataDirectory, "tasks.txt");
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }
            Scanner scanner = new Scanner(taskListFile);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                // Parse each line to create a Task and add it to loadedTasks
                Task task = parseTaskFromString(line);
                if (task != null) {
                    loadedTasks.add(task);
                }
            }
            printFileContents(taskListFile);
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
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

    public static void saveTaskListToFile(ArrayList<Task> taskList) {
        try{
            File dataDirectory = new File("./data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdir();
            }

            File taskListFile = new File(dataDirectory, "tasks.txt");
            if (!taskListFile.exists()) {
                taskListFile.createNewFile();
            }

            FileWriter taskFile = new FileWriter(taskListFile);
            for (Task task : taskList) {
                String taskString = task.toFileString();
                taskFile.write(taskString);
                taskFile.write(System.lineSeparator());
            }
            taskFile.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    public static void printFileContents(File taskListFile) throws FileNotFoundException {
        try {
            Scanner s = new Scanner(taskListFile); // Create a Scanner directly from the file
            while (s.hasNextLine()) {
                System.out.println(s.nextLine());
            }
            s.close(); // Close the Scanner when done
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


}
