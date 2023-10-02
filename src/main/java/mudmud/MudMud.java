package mudmud;

import mudmud.command.Command;
import mudmud.exception.DukeCommandException;
import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;
import mudmud.parser.Parser;

/**
 * The entry point for Duke application.
 * Initializes and starts the application.
 */
public class MudMud {

    private TextUi ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /** Initializes all class members in Duke. */
    public void setup() {
        ui = new TextUi();
        storage = new Storage();
        tasks = new TaskList(storage.restoreSavedData());
        parser = new Parser();
    }

    /** Runs the application until termination. */
    public void run() {
        String input;
        boolean isExit = false;

        ui.tellGreeting();
        while (!isExit) {
            input = ui.getInput();
            try {
                ui.showLine();
                Command command = parser.parseCommand(input);
                command.executeCommand(tasks, ui, storage);
                isExit = command.isExit();
            } catch (DukeCommandException exception) {
                exception.handleDukeCommandException(parser.parseCommandType(input)[0]);
            } finally {
                ui.showLine();
            }
        }
    }

    /** Starts up the application. */
    public void start() {
        setup();
        run();
    }

    public static void main(String[] args) {
        new MudMud().start();
    }
}