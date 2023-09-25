package duke;

import java.io.IOException;

/**
 * The {@code Duke} class serves as the main class for the Duke application, orchestrating
 * the interaction between the user, the task list, the storage, and the user interface.
 * <p>
 * The Duke application is a task management system where users can add, delete,
 * and mark tasks as done. Tasks are saved in a specified file, and the application
 * can load tasks from this file upon startup. The Duke class initializes the required
 * components such as UI, Storage, and TaskList and handles the main execution loop
 * where commands are read, parsed, executed, and the tasks list is saved back to the file.
 * </p>
 * <p>
 * When an instance of {@code Duke} is created, it attempts to load the task list from a file.
 * If an error occurs during this loading, appropriate error messages are displayed,
 * and an empty task list is initialized. The Duke class has a {@code run} method that
 * starts the main execution of the application, and a {@code main} method that serves as
 * the entry point of the application.
 * </p>
 * <ul>
 *     <li>{@link #Duke(String)} - Constructs a new Duke object.</li>
 *     <li>{@link #run()} - Starts the execution of Duke.</li>
 *     <li>{@link #main(String[])} - The entry point of the application.</li>
 * </ul>
 *
 * @see Storage
 * @see TaskList
 * @see Ui
 * @see Command
 * @see Parser
 *
 * @author  Ashok Balaji
 * @version 1.0
 * @since   2023-09-25
 */
public class Duke {
    private static Storage STORAGE;
    private static TaskList TASKS;
    private static Ui UI;
    public static String DATAPATH = ".\\data\\duke.txt";

    /**
     * Constructs a new Duke object which initializes the user interface, storage, and task list.
     * <p>
     * Attempts to load the task list from the specified file path. If the loading fails due to an
     * IOException, it shows a loading error and initializes an empty task list. If it fails due to a
     * DukeException, it shows an error message and initializes an empty task list.
     * </p>
     *
     * @param filePath The file path where the task list is stored and from where it should be loaded.
     */
    public Duke(String filePath){
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            TASKS = new TaskList(STORAGE.load());
        } catch (IOException ioEx) {
            UI.showLoadingError(DATAPATH);
            TASKS = new TaskList();
        } catch (DukeException dukeEx) {
            UI.showError(dukeEx);
            System.out.println("Historical data load failed.");
            TASKS = new TaskList();
        }
    }

    /**
     * Starts the execution of Duke.
     * <p>
     * Displays a welcome message and continuously reads and executes commands until an exit command is received.
     * After executing a command, it saves the current state of the task list. If a DukeException occurs during
     * the execution of a command, it shows an error message. If an IOException occurs during the saving of tasks,
     * it prints an error message and returns, effectively ending the program.
     * </p>
     */
    public void run(){
        UI.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(TASKS);
                STORAGE.save(TASKS);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e);
            }catch (IOException IOEx) {
                System.out.println("Unable to open write handler @ " + DATAPATH);
                return;
            } finally {
                UI.showLine();
            }
        }
    }

    public static void main(String[] args){
        new Duke(DATAPATH).run();
        UI.showExitMessage();
    }
}


