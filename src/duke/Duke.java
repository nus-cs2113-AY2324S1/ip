package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

import java.io.IOException;

/**
 * Duke task manager.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with a default save file ./data/duke.txt.
     */
    public Duke() {
        this("./data/duke.txt");
    }

    /**
     * Initializes Duke. Loads saved tasks from file path provided by filePath if the file
     * exists, else creates a new empty list.
     *
     * @param filePath path to an existing Duke savefile.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Duke. Reads in user input and processes commands until "bye" command is received.
     */
    public void run() {
        String command, arguments;
        String result;

        ui.printWelcome();

        do {
            String[] line = new Parser().parseCommand(ui.getCommand());
            command = line[0];
            arguments = line[1].trim();

            ui.printLine();

            switch (command) {
            case "list":
                result = tasks.handleGetList();
                break;
            case "mark":
                result = tasks.markItem(arguments);
                break;
            case "unmark":
                result = tasks.unmarkItem(arguments);
                break;
            case "todo":
                try {
                    result = tasks.handleCreateTodo(arguments);
                } catch (DukeException e) {
                    result = String.valueOf(e);
                }
                break;
            case "deadline":
                try {
                    result = tasks.handleCreateDeadline(arguments);
                } catch (DukeException e) {
                    result = String.valueOf(e);
                }
                break;
            case "event":
                result = tasks.handleCreateEvent(arguments);
                break;
            case "find":
                result = tasks.handleFindTask(arguments);
                break;
            case "delete":
                result = tasks.handleDeleteTask(arguments);
                break;
            default:
                result = "I don't know that command";
            }

            if (command.equals("bye")) {
                break;
            }

            ui.printCommandResult(result);
            ui.printLine();

        } while (true);

        try {
            storage.writeTasksToFile(tasks.handleWriteList());
        } catch (IOException e) {
            ui.println(String.valueOf(e));
        }

        ui.printFarewell();
    }

    /**
     * Creates and starts running a new instance of Duke.
     */
    public static void main() {
        new Duke().run();
    }
}
