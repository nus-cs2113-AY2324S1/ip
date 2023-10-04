package magpie.main;
import magpie.files.Storage;
import magpie.input.Ui;
import magpie.task.TaskList;
import java.io.IOException;

/**
 * Initializes Storage, TaskList, and Ui objects.
 * Loads data file to populate TaskList (if any).
 * Runs and exits process when user is done.
 */
public class Magpie {

    private Storage storage;
    private TaskList taskManager;
    private Ui ui;

    /**
     * Constructs Storage, TaskList, and Ui objects to prepare for execution and calls <code>loadFile</code>
     * to load tasks from data file.
     */
    public Magpie() {
        taskManager = new TaskList();
        ui = new Ui();
        storage = new Storage();
        storage.loadFile();

    }

    /**
     * Prints farewell message to user and writes current tasks in TaskList to data file.<br>
     */
    public void exit() {
        ui.printByeMessage();
        try {
            storage.saveToFile();
        } catch (IOException e) {
            System.out.println("Opps. File writing error!");
        }
    }

    /**
     * Prints <code>Magpie</code> Logo to welcome user and get user input.
     * Calls exit when user enters "bye".
     */
    public void run() {
        ui.printLogo();
        ui.processUserInput();

        exit();
    }

    /**
     * Main method that calls Magpie Constructor and begins program execution.
     */
    public static void main(String[] args) {

        new Magpie().run();

    }

}