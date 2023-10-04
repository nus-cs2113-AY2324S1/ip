import java.util.ArrayList;
import java.util.Scanner;

/**
 * This is the main program of Duke.
 * Duke is a chatbot for user track tasks such as todos, deadlines and events.
 * User can add tasks, mark tasks as done, delete tasks or find tasks based on a keyword.
 * These tasks are automatically saved in a file in ./data/data.txt whenever an operation
 * on the TaskList is made. This file will be loaded on future start up of the chatbot
 *
 */
public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    /**
     * Constructor of Duke. Initialise User interface, Storage and TaskList instances.
     * @param filePath Path to the file where tasks are to be loaded from and saved to.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * To start up the chatbot.
     * A greetings will be printed on the command line interface and the chatbot will
     * await instructions from the user. The chatbot will run until an exit command is received.
     * Any exception during the operation will produce an error message to notify the user
     */
    public void run() {
       Parser parser = new Parser();
       ui.showStartMessage();
       boolean isExit = false;
       while (!isExit) {
           try {
               String fullCommand = ui.readCommand();
               Command c = parser.parse(fullCommand);
               c.execute(taskList, ui, storage);
               isExit = c.isExit();
           } catch (DukeException e) {
               ui.showError(e);
           }
       }
       System.exit(0);
    }

    public static void main(String[] args) {
        new Duke("./data/data.txt").run();
    }

}
