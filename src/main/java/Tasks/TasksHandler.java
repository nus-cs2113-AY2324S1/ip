package Tasks;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The TasksHandler class is responsible for reading and writing tasks to a file.
 */
public class TasksHandler {

    private static final String FILE_PATH = "caraData.txt";

    /**
     * Writes tasks to a file.
     *
     * @param tasks The list of tasks to be written to the file.
     * @throws IOException If an I/O error occurs during file writing.
     */
    public static void writeToFile(ArrayList<Task> tasks) throws IOException {
        FileWriter fileWriter = new FileWriter(FILE_PATH);
        for (Task task : tasks) {
            if (task instanceof TodoTask) {
                fileWriter.write("T | " + task.isDone() + " | "
                        + task.getDescription() + System.lineSeparator());
            } else if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task; // Cast to DeadlineTask
                fileWriter.write("D | " + task.isDone() + " | "
                        + task.getDescription() + " | " + deadlineTask.getBy() + System.lineSeparator());
            } else if (task instanceof EventTask) {
                EventTask eventTask = (EventTask) task; // Cast to EventTask
                fileWriter.write("E | " + task.isDone() + " | " + task.getDescription() + " | "
                        + eventTask.getFrom() + " | " + eventTask.getTo() + System.lineSeparator());
            }
        }
        fileWriter.close();
    }

    /**
     * Reads tasks from a file and populates the tasks list.
     *
     * @param tasks The list of tasks to be populated with tasks from the file.
     * @throws IOException If an I/O error occurs during file reading.
     */
    public static void readFromFile(ArrayList<Task> tasks) throws IOException {
        File file = new File(FILE_PATH);
        boolean fileCreated = false;

        if (!file.exists()) {
            fileCreated = file.createNewFile();
        }

        if (fileCreated || file.exists()) {
            Scanner scanner = new Scanner(file); // Initialize the scanner
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] lineArray = line.split("\\|");

                Task task = null;
                switch (lineArray[0].trim()) {
                case "T":
                    task = new TodoTask(lineArray[2].trim());
                    break;
                case "D":
                    task = new DeadlineTask(lineArray[2].trim());
                    break;
                case "E":
                    task = new EventTask(lineArray[2].trim());
                    break;
                }

                if (task != null) {
                    // Parse the "done" status as a boolean
                    boolean isDone = lineArray[1].trim().equals("true");
                    if (isDone) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                } else {
                    // Handle the case where there was an issue creating the task
                    System.err.println("Error: Unable to create a task from input line: " + line);
                }
            }
            scanner.close(); // Close the scanner
        } else {
            // Handle the case where the file couldn't be created or opened.
            System.err.println("Error: Unable to create or access the file.");
        }
    }
}
