package elvis.operation;

import elvis.task.Deadline;
import elvis.task.Event;
import elvis.task.Task;
import elvis.task.ToDo;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.IOException;

/**
 * Manages the storage of tasks in a file.
 * Writes to and Reads from file, and inputs the tasks to TaskList
 */
public class Storage {
    private static final String FILE_PATH = "./tasks.txt";
    private static final String FILE_NAME = "tasks.txt";
    private static final DateTimeFormatter STD_FORMAT = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
    private static File openedFile;

    /**
     * Acts as the main function of Storage
     * Runs the bootup sequence
     * Runs the taskLoader that loads tasks from txt file
     */
    public static void fileManager() {
        BootUpShutDown.bootUpOne();
        boolean hasFile = bootUp();
        taskLoader();
        if (hasFile) {
            Ui.taskLoadedMessagePrinter();
            Parser.inputTaskFromFile("list");
        }
        BootUpShutDown.bootUpTwo();
        Parser.inputTaskManually();
    }

    /**
     * Manages the storage of tasks in a file.
     * Checks for any previously saved file, creates if missing
     */
    public static boolean bootUp() {
        System.out.print(System.lineSeparator());
        Ui.checkFileMessagePrinter();
        File file = new File(FILE_PATH);

        if (!file.exists()) {   //File doesn't exist, so create it
            try {
                if (file.createNewFile()) { //Returns true if successfully created
                    openedFile = file;      //File used to save tasks
                    Ui.noFileMessagePrinter();
                } else {
                    Ui.fileCreationFailMessagePrinter();
                }
            } catch (IOException exception) {
                Ui.errorMessagePrinter(exception);
            }
            return false;
        } else {
            Ui.fileExistMessagePrinter();
            openedFile = file;      //File used to save tasks
            return true;
        }
    }

    /**
     * Loads tasks from file into the TaskList
     */
    public static void taskLoader() {
        try {
            File file = openedFile;
            Scanner fileReader = new Scanner(openedFile);     // create a Scanner using the File as the source
            while (fileReader.hasNext()) {
                String lineOfFile = fileReader.nextLine();
                Parser.inputTaskFromFile(lineOfFile);   // Parse each line into the tasks ArrayList
            }
        } catch (FileNotFoundException exception) {
            Ui.fileNotFoundMessagePrinter();
        }
    }

    /**
     * Manages the storage of tasks in a file.
     * Write each task in the desired format to the file
     */
    public static void saver(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);
            for (Task task : tasks) {
                writer.write(toFileString(task) + "\n");
            }
            writer.close();
        } catch (IOException exception) {
            Ui.errorMessagePrinter(exception);
        }
    }

    /**
     * Returns String that is in the format to be written to file
     *
     * @param task
     * @return String
     * @throws IOException when there is problem with formatting the task
     */
    public static String toFileString(Task task) throws IOException {
        int status = task.getIsDone() ? 1 : 0;
        if (task instanceof ToDo) {
            return String.format("%s %d %s", "todo", status, task.getDescription());
        } else if (task instanceof Deadline) {
            return String.format("%s %d %s/by%s", "deadline", status, task.getDescription(),
                    task.getDateTime().format(STD_FORMAT));
        } else if (task instanceof Event) {
            return String.format("%s %d %s/from%s/to%s", "event", status, task.getDescription(),
                    task.getStartDateTime().format(STD_FORMAT), task.getEndDateTime().format(STD_FORMAT));
        }
        throw new IOException();
    }
}
