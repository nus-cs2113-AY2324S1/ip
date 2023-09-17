package elgin.utils;

import elgin.task.Deadline;
import elgin.task.Event;
import elgin.task.Task;
import elgin.task.Todo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.FileWriter;

public class Store {

    private static final String DONE_VALUE = "1";
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final String TASK_FILE_DIRECTORY_PATH = Path.of("src/main/java/elgin/data").toAbsolutePath().toString();
    private static final String TASK_FILE_PATH = Path.of("src/main/java/elgin/data/tasks.txt").toAbsolutePath().toString();

    private static final String SEPARATOR = " | ";

    public static void checkAndCreateDirectory(String dirPath) {
        File directory = new File(dirPath);
        if (!directory.exists()) {
            directory.mkdir();
        }
    }

    public static void checkAndCreateFile(String filePath) {
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static ArrayList<Task> getSavedTasks() {
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
            System.out.println(e.getMessage());
        }

        return savedTasks;
    }

    public static Task parseTask(String taskLine) {
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
            return new Deadline(taskInfo[2], taskInfo[3], taskInfo[1].equals(DONE_VALUE));
        case "E":
            if (taskInfo.length != 5) {
                return null;
            }
            return new Event(taskInfo[2], taskInfo[3], taskInfo[4], taskInfo[1].equals(DONE_VALUE));
        default:
            return null;
        }

    }

    private static void deleteFileContent(String filePath) {
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write("");
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void appendToFile(String filePath, String content) {
        try {
            FileWriter writer = new FileWriter(filePath, true);
            writer.write(content);
            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void saveTasks(ArrayList<Task> tasks) {
        deleteFileContent(TASK_FILE_PATH);
        for (Task task : tasks) {
            String taskAsString = getTaskAsString(task);
            appendToFile(TASK_FILE_PATH, taskAsString);
        }
    }

    public static String getTaskAsString(Task task) {
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
