package dawson;

import dawson.command.Command;
import dawson.command.CommandResult;
import dawson.command.ExitCommand;
import dawson.exception.DawsonException;
import dawson.parser.Parser;
import dawson.storage.Storage;
import dawson.task.TaskList;
import dawson.ui.TextUI;

/**
 * Entry point of the Dawson application.
 * Initializes the application and starts the interaction with the user.
 */
public class Dawson {

    private Command newCommand;
    private TextUI ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * Private constructor for the Dawson class, used for initializing the user interface, storage, and task list.
     * Load tasks from storage if available, or initializes an empty task list if there is an issue.
     * 
     * <p>These initialisations are <strong>mandatory</strong> and hence is declared in the constructor to ensure dependency.</p>
     */
    private Dawson() {
        ui = new TextUI();
        storage = new Storage("data/dawson.txt");

        try {
            taskList = new TaskList(storage.load());
        } catch (DawsonException e) {
            ui.printText(e.getMessage());
            taskList = new TaskList();
        }
    }

    private void setup() {
        ui.printWelcomeText();
    }

    /**
     * The main execution loop of the Dawson application.
     * 
     * <p> This method continuously accepts user input, parses commands, and executes them until the
     * user types a "bye" command, at which point the application exits.</p>
     */
    private void run() {
        do {
            String nextLineString = ui.getUserCommand();
            newCommand = Parser.parseCommand(nextLineString);

            try {
                CommandResult result = newCommand.execute(taskList);
                ui.printCommandResult(result);
                storage.save(taskList);

            } catch (DawsonException e) {
                ui.printText(e.getMessage());
            }
        } while (!(newCommand instanceof ExitCommand));

        System.exit(0);
    }

    public static void main(String[] args) {
        Dawson dawson = new Dawson();
        dawson.setup();
        dawson.run();
    }
}
