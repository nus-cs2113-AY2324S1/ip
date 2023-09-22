package zenbot;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

import java.io.IOException;
import exceptions.TaskEmptyDescriptionException;

import tasks.Tasklist;
import tasks.Task;
import tasks.Todo;
import tasks.Deadline;
import tasks.Event;

/**
 * Represents a file handler that can read and write to a file.
 */
public class FileHandler {
    
    private static final String DIRECTORY_PATH = "data";
    private static final String FILE_PATH = "data/zenbot.txt";

    /**
     * Writes the tasks in the tasklist to a file when the user exits the application.
     * @param tasks The tasklist to be written to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public static void writeToFile(Tasklist tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (int i = 1; i <= tasks.getTaskListSize(); i++) {
            if ( tasks.getTask(i) instanceof Todo) {
                fileWriter.write("T | " + tasks.getTask(i).getIsDone() + " | "
                    + tasks.getTask(i).getDescription() + System.lineSeparator());
            } else if ( tasks.getTask(i) instanceof Deadline) {
                fileWriter.write("D | " + tasks.getTask(i).getIsDone() + " | " 
                    + tasks.getTask(i).getDescription() + " | " 
                    + ((Deadline) tasks.getTask(i)).getDeadline() + System.lineSeparator());
            } else if ( tasks.getTask(i) instanceof Event) {
                fileWriter.write("E | " + tasks.getTask(i).getIsDone() + " | " + tasks.getTask(i).getDescription() + " | "
                    + ((Event) tasks.getTask(i)).getStartTime() + " | "
                    + ((Event) tasks.getTask(i)).getEndTime() + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    /**
     * Reads the tasks from a file and adds them to the tasklist when app starts.
     * @param tasks The tasklist to be added to.
     * @throws TaskEmptyDescriptionException If the task description is empty.
     * @throws IOException If an error occurs while reading from the file.
     */
    public static void readFromFile(Tasklist tasks) throws TaskEmptyDescriptionException, IOException {
        File directory = new File(DIRECTORY_PATH);
        if (!directory.exists()) {
            directory.mkdir();
        }
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            final String FILE_DELIMITER = " \\| ";
            String line = scanner.nextLine();
            String[] lineArray = line.split(FILE_DELIMITER);

            Task task = null;
            switch (lineArray[0]) {
            case "T":
                task = new Todo (lineArray[2]);
                break;
            case "D":
                task = new Deadline (lineArray[2], lineArray[3]);
                break;
            case "E":
                task = new Event (lineArray[2], lineArray[3], lineArray[4]);
                break;
            }
            if (lineArray[2].equals("true")) {
                task.setIsDone(true);
            }
            tasks.addTask(task);
        }
    }

    public static String getFilePath() {
        return FILE_PATH;
    }
}
