
import luke.actions.*;
import luke.user.LukeException;
import luke.user.Ui;
import luke.tasks.*;
import luke.files.*;

/**
 * The Luke Class represents the main application class for Luke, a task management application.
 * It initializes the user interface, task storage and task list.
 * It runs the application loop, managing the user interface, task storage, and task list.
 */
public class Luke {
    public static final String folderPath = "data";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Luke object.
     */
    public Luke() {
        ui = new Ui();
        storage = new Storage(folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        } catch (NullPointerException e) {
            //ui.showNoMemoryFileError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Luke application.
     * It displays a welcome message, reads user commands, and executes corresponding actions.
     * The application continues running until the user exits.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                //c has theActionWord and parameters
                c.execute(tasks, ui, storage);
                //tasks has ArrayList<Task> mainTaskList, ui has String echo, storage has ArrayList<Task> tasks
                isExit = c.isExit(); //for bye command
            } catch (LukeException e) { //from Parser.parse
                ui.showError("\t☹ An error occurred." + e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main method that starts the Luke application.
     *
     * @param args The command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Luke().run();
    }
}

