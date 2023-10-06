import kenergeticbot.TaskList;
import kenergeticbot.command.Command;
import kenergeticbot.fileaccess.Storage;
import kenergeticbot.ui.TextUi;
import kenergeticbot.Parser;
import kenergeticbot.command.ExitCommand;

/**
 * Entry point of the KenergeticBot chatbot.
 * Initializes the application and starts the interaction with the user.
 */
public class KenergeticBot {
    public static final String filePath = "data/KenergeticBot.txt";

    private TextUi ui;
    private Storage storage;
    public TaskList taskList;

    public static void main(String[] args) {
        new KenergeticBot().run();
    }

    /**
     * Sets up the required objects, loads up the data from the storage file, and prints the welcome message.
     */
    private void start(String filePath, TaskList taskList, TextUi ui) {
        this.storage = new Storage(filePath, ui);
        storage.loadPreviousList(taskList, ui);
        ui.printGreetingMessage();
    }

    /** Runs the program until termination.  */
    public void run() {
        this.taskList = new TaskList();
        this.ui = new TextUi();
        start(filePath, taskList, ui);
        runCommandLoopUntilExitCommand();
        exit(ui);
    }

    /** Reads the user command and executes it, until the user issues the exit command.  */
    public void runCommandLoopUntilExitCommand() {
        do {
            String item = ui.getUserCommand();
            Command c = Parser.parseCommand(taskList, item);
            c.execute(taskList, ui);
        } while (!ExitCommand.isExit());
        storage.saveList(taskList, ui);
    }

    /** Prints the Goodbye message and exits. */
    private static void exit(TextUi ui) {
        ui.printExitMessage();
        System.exit(0);
    }
}
