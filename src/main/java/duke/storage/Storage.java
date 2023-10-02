package duke.storage;

import duke.parser.Parser;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import duke.ui.TextUi;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A handler that modifies and gets data from a file.
 */
public class Storage {
    private final String DATA_PATH = "data/tasks.txt";
    private final String DATA_DIRECTORY = "data";

    private TextUi ui;
    private Parser parser;
    private ArrayList<Task> tasksListPlaceholder;

    /**
     * Creates a handler to modify and access data in the file.
     */
    public Storage() {
        tasksListPlaceholder = new ArrayList<>();
        ui = new TextUi();
        parser = new Parser();
    }

    /**
     * Inserts new data into the file.
     *
     * @param dataString A line that will be inserted into the file.
     * @param tasksCount The number of tasks in the list.
     * @throws IOException If there is an error related to access/modifying the file.
     */
    public void addNewData(String dataString, int tasksCount) throws IOException {
        FileWriter writer = new FileWriter(DATA_PATH, true);

        if (tasksCount != 1) {
            writer.write(System.lineSeparator());
        }

        writer.write(dataString);
        writer.close();
    }

    /**
     * Changes the status of a task with the specified index in the file.
     *
     * @param index The index/row with the modified task.
     * @param currentIndex Current index/row.
     * @param scanner The scanner that refers to the file.
     * @param change The changed status of a task whether it is mark or unmark.
     * @param buffer A string builder that will be replacing the contents into the file.
     */
    private void modifyTaskData(int index, int currentIndex, Scanner scanner, String change,
                                       StringBuilder buffer) {
        if (currentIndex == index) {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            String[] parsedTask = scanner.nextLine().split(" \\| ");
            parsedTask[1] = change;

            for (int i = 0; i < parsedTask.length; i++) {
                buffer.append(parsedTask[i]);

                if (i < parsedTask.length - 1) {
                    buffer.append(" | ");
                }
            }

        } else {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            buffer.append(scanner.nextLine());
        }
    }

    /**
     * Updates a line from the file that refers to a modified task.
     *
     * @param index The index/row with the modified task.
     * @param isDone Specifies if the task is marked or unmarked.
     * @throws IOException If there is an error related to access/modifying the file.
     */
    public void updateTaskDatabase(int index, boolean isDone) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        String change = isDone ? "true" : "false";
        int currentIndex = 0;

        while (scanner.hasNext()) {
            modifyTaskData(index, currentIndex, scanner, change, buffer);
            currentIndex++;
        }

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    /**
     * Creates a new task based on a line from the file.
     *
     * @param scan The scanner that refers to the file.
     */
    private void createNewData(Scanner scan) {
        String[] parsedTask = scan.nextLine().split(" \\| ");

        String taskType = parsedTask[0];
        String description = parsedTask[2];
        String isDone = parsedTask[1];

        switch (taskType) {
        case "T":
            tasksListPlaceholder.add(new Todo(description , isDone));
            break;
        case "D":
            LocalDateTime by = parser.parseDateTime(parsedTask[3]);
            tasksListPlaceholder.add(new Deadline(description, isDone, by));
            break;
        case "E":
            LocalDateTime start = parser.parseDateTime(parsedTask[3]);
            LocalDateTime end = parser.parseDateTime(parsedTask[4]);
            tasksListPlaceholder.add(new Event(description, isDone, start, end));
            break;
        }
    }

    /**
     * Restores data of the previous session from the file.
     *
     * @return A list of tasks that is going to be included in task list.
     */
    public ArrayList<Task> restoreSavedData() {
        try {
            File file = new File(DATA_PATH);
            Scanner scan = new Scanner(file);

            while (scan.hasNext()) {
                createNewData(scan);
            }
        } catch (FileNotFoundException exception) {
            handleFileNotFoundException();
        }

        return tasksListPlaceholder;
    }

    /**
     * Creates a directory to store the file.
     *
     * @param newDirectory The directory that is going to be created.
     */
    private void createDukeDirectory(File newDirectory) {
        if (!newDirectory.isDirectory() && newDirectory.mkdir()) {
            System.out.println("\tDirectory successfully created!");
        } else {
            System.out.println("\tDirectory found!");
        }
    }

    /**
     * Creates a file to store data.
     *
     * @param newDatabase The file that is going to be created.
     * @throws IOException If there is an error in creating the file.
     */
    private void createDukeFile(File newDatabase) throws IOException {
        if (!newDatabase.isFile() && newDatabase.createNewFile()) {
            System.out.println("\tData text file successfully created!");
        } else {
            System.out.println("\tText file found!");
        }
    }

    /**
     * Appends all lines except the line with the deleted task.
     *
     * @param index The index/row with the deleted task.
     * @param currentIndex Current index/row.
     * @param scanner The scanner that refers to the file.
     * @param buffer A string builder that will be replacing the contents into the file.
     */
    private void excludeDeletedData(int index, int currentIndex, Scanner scanner, StringBuilder buffer) {
        if (currentIndex == index) {
            scanner.nextLine();
        } else {
            if (buffer.length() > 0) {
                buffer.append(System.lineSeparator());
            }

            buffer.append(scanner.nextLine());
        }
    }

    /**
     * Deletes a line of the deleted task in the file.
     *
     * @param index The index/row with the deleted task.
     * @throws IOException If there is an error related to access/modifying the file.
     */
    //Solution below adapted by https://www.tutorialspoint.com/how-to-overwrite-a-line-in-a-txt-file-using-java
    public void deleteTaskData(int index) throws IOException {
        Scanner scanner = new Scanner(new File(DATA_PATH));

        StringBuilder buffer = new StringBuilder();
        int currentIndex = 0;

        while (scanner.hasNext()) {
            excludeDeletedData(index, currentIndex, scanner, buffer);
            currentIndex++;
        }

        scanner.close();

        FileWriter writer = new FileWriter(DATA_PATH);
        writer.append(buffer.toString());
        writer.close();
    }

    /**
     * Creates the missing directory and file for storing data.
     */
    public void handleFileNotFoundException() {
        ui.showLine();
        System.out.println("\tLooks like I can't find the file. Let me make it for you.");

        File newDirectory = new File(DATA_DIRECTORY);
        File newDatabase = new File(DATA_PATH);

        try {
            createDukeDirectory(newDirectory);
            createDukeFile(newDatabase);
        } catch (IOException exception){
            ui.handleIOException(exception);
        }

        System.out.println("\tPlease try to run the app again.");
        ui.showLine();
        System.exit(-1);
    }
}
