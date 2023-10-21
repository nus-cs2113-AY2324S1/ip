package bob;

import bob.commands.MarkCommand;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.tasklist.TaskList;
import bob.ui.Ui;
import bob.commands.DeadlineCommand;
import bob.commands.ListCommand;

import java.io.IOException;

/**
 * Duke task manager.
 */
public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Duke with a default save file ./data/duke.txt.
     */
    public Bob() {
        this("./data/duke.txt");
    }

    /**
     * Initializes Duke. Loads saved tasks from file path provided by filePath if the file
     * exists, else creates a new empty list.
     *
     * @param filePath path to an existing Duke savefile.
     */
    public Bob(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (BobException e) {
            ui.printLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Bob. Reads in user input and processes commands until "bye" command is received.
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
            case ListCommand.COMMAND_WORD:
                result = new ListCommand().execute(tasks);
                break;
            case MarkCommand.COMMAND_WORD:
                result = new MarkCommand(arguments).execute(tasks);
                break;
            case "unmark":
                result = tasks.unmarkItem(arguments);
                break;
            case "todo":
                try {
                    result = tasks.handleCreateTodo(arguments);
                } catch (BobException e) {
                    result = String.valueOf(e);
                }
                break;
            case DeadlineCommand.COMMAND_WORD:
                try {
                    //result = tasks.handleCreateDeadline(arguments);
                    DeadlineCommand deadline = new DeadlineCommand(arguments);
                    result = deadline.execute(tasks);
                } catch (BobException e) {
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
    public static void main(String[] args) {
        new Bob().run();
    }
}
