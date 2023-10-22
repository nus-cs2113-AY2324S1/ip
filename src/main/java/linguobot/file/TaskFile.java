package linguobot.file;

import linguobot.Ui;
import linguobot.task.*;
import linguobot.task.TaskList;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * The <code>TaskFile</code> class is responsible for managing the storage and retrieval of tasks in a file.
 * It provides methods to create, read, parse, and save tasks to a file, as well as print the tasks
 * stored in the file.
 */
public class TaskFile {
    private static File tasks;
    private static TaskList taskList;

    public TaskFile(String filename, TaskList taskList) {
        tasks = new File(filename);
        createFile();
        this.taskList = taskList;
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
            Ui.printTextWithoutLine("Cannot create file; reason: " + e.getMessage());
        }
    }


    /**
     * Prints tasks that are stored in taskFile.
     */
    public static void printFile() {
        try (Scanner scanner = new Scanner(tasks)) {
            if (!scanner.hasNextLine()) {
                Ui.printTextWithoutLine("You do not have any prior tasks.");
            } else {
                Ui.printTextWithoutLine("Your tasks are as follows:");
                while (scanner.hasNextLine()) {
                    Ui.printTextWithoutLine(scanner.nextLine());
                }
                scanner.close();
            }
        } catch (IOException e) {
            Ui.printTextWithoutLine("Something went wrong: " + e.getMessage());
        }
    }

    public TaskList loadTasksIntoTasklist() {
        try (Scanner scanner = new Scanner(tasks)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                // parse each line into a task and add it to the taskList
                Task task = parseTaskFromString(line);
                taskList.addTask(task);
            }
        } catch (IOException e) {
            Ui.printTextWithoutLine("Error reading tasks from file: " + e.getMessage());
        }
        return taskList;
    }

    public Task parseTaskFromString(String taskString) {
        String[] parts = taskString.split("\\s*\\|\\s*");
        if (parts.length < 2) {
            return null;
        }
        String taskType = parts[0];
        boolean isDone = parts[1].equals("1");
        Task task = null;

        switch(taskType) {
        case "T":
            if (parts.length >= 3) {
                task = new Todo(parts[2].trim());
            }
            break;
        case "D":
            if (parts.length >= 4) {
                task = new Deadline(parts[2].trim(), parts[3].trim());
            }
            break;
        case "E":
            if (parts.length >= 4) {
                if (parts[3].contains("to")) {
                    String[] eventParts = parts[3].split("to");
                    task = new Event(parts[2].trim(), eventParts[0].trim(), eventParts[1].trim());
                }
            }
            break;
        default:
            break;
        }
        return task;
    }

    /**
     * Saves the current taskList to the taskFile, overwriting its contents.
     * @param taskList ArrayList of tasks.
     */
    public static void saveTaskListToFile(TaskList taskList) {
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
            for (Task task : taskList.getTaskList()) {
                String taskString = task.toFileString();
                taskFile.write(taskString);
                taskFile.write(System.lineSeparator());
            }
            taskFile.close();
        } catch (IOException e) {
            Ui.printTextWithoutLine("Something went wrong: " + e.getMessage());
        }
    }

}
