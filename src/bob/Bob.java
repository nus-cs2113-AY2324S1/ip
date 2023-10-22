package bob;

import bob.commands.*;
import bob.parser.BobParser;
import bob.storage.Storage;
import bob.tasklist.TaskList;
import bob.ui.Ui;

import java.io.IOException;

/**
 * Bob task manager.
 */
public class Bob {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes Bob with a default save file ./data/bob.txt.
     */
    public Bob() {
        this("./data/bob.txt");
    }

    /**
     * Initializes Bob. Loads saved tasks from file path provided by filePath if the file
     * exists, else creates a new empty list.
     *
     * @param filePath path to an existing Bob savefile.
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
        // String command, arguments;
        String result;

        ui.printWelcome();

        do {
            try {
                Command command = new BobParser().parseCommand(ui.getCommand());
                result = command.execute(tasks);
            } catch (BobException e) {
                result = String.valueOf(e);
            }

            ui.printLine();

            if (result.equals("bye")) {
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
     * Creates and starts running a new instance of Bob.
     */
    public static void main(String[] args) {
        new Bob().run();
    }
}
