package linguobot.file;

import linguobot.task.Deadline;
import linguobot.task.Event;
import linguobot.task.Task;
import linguobot.task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The <code>TaskFile</code> class is responsible for managing the storage and retrieval of tasks in a file.
 * It provides methods to create, read, parse, and save tasks to a file, as well as print the tasks
 * stored in the file.
 */
public class TaskFile{
    private static File tasks;

    public TaskFile(String filename) {
        tasks = new File(filename);
        createFile();
    }

    /**
     * Creates taskFile to store tasks if it doesn't already exist.
     */
    private void createFile() {
        try {
            if (!tasks.exists()) {
                if (!tasks.getParentFile().exists()) {
                    tasks.getParentFile().mkdirs();
                }
                tasks.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Cannot create file; reason: " + e.getMessage());
        }
    }

    /**
     * Returns tasks that were stored in the taskFile.
     * @return an arraylist of tasks read from taskFile.
     */
    public ArrayList<String> readFile() {
        ArrayList<String> dataItems = new ArrayList<>();

        try (Scanner scanner = new Scanner(tasks)) {
            while (scanner.hasNextLine()) {
                dataItems.add(scanner.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks from file: " + e.getMessage());
        }

        return dataItems;
    }

    /**
     * Prints tasks that are stored in taskFile.
     */
    public static void printFile() {
        try (Scanner scanner = new Scanner(tasks)) {
            if (!scanner.hasNextLine()) {
                System.out.println("You do not have any prior tasks.");
            } else {
                System.out.println("Your tasks are as follows:");
                while (scanner.hasNextLine()) {
                    System.out.println(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }

    /**
     * Returns ArrayList of <code>Task</code> objects that are stored in taskFile.
     * @return ArrayList of <code>Task</code> objects.
     */
    public ArrayList<Task> loadTasksFromFile() {
        ArrayList<Task> taskList = null;
        ArrayList<String> dataItems = readFile();
        taskList = parseTaskFromString(dataItems);
        return taskList;
    }

    /**
     * Parses task data stored as strings into <code>Task</code> objects and returns them in an ArrayList.
     * @param taskFile File containing data strings of stored tasks.
     * @return ArrayList of <code>Task</code> objects.
     */
    public static ArrayList<Task> parseTaskFromString(ArrayList<String> taskFile) {
        ArrayList<Task> tasks = new ArrayList<>();

        for (String taskString : taskFile) {
            String[] parts = taskString.split("\\s*\\|\\s*");
            if (parts.length < 2) {
                continue;
            }

            String taskType = parts[0];
            Task task = null;

            switch (taskType) {
            case "T":
                if (parts.length >= 3) {
                    task = new Todo(parts[2]);
                }
                break;
            case "D":
                if (parts.length >= 4) {
                    task = new Deadline(parts[2], parts[3]);
                }
                break;
            case "E":
                if (parts.length >= 4) {
                    if (parts[3].contains("to")) {
                        String[] eventParts = parts[3].split("to");
                        task = new Event(parts[2], eventParts[0], eventParts[1]);
                    }
                }
                break;
            default:
                // Invalid task type, return null
                break;
            }

            if (task != null) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    /**
     * Saves the current taskList to the taskFile, overwriting its contents.
     * @param taskList ArrayList of tasks.
     */
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

}
