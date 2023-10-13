import command.Command;
import parser.Parser;
import task.TaskList;
import storage.Storage;
import ui.Ui;
import java.io.IOException;
import java.lang.String;

/**
 * The main class of the project
 */
public class ListWhisper {
    private final Ui ui;
    private final Parser parser;
    TaskList taskList;

    /**
     * Creates a ListWhisper object and runs the application
     * @param args
     */
    public static void main(String[] args) {
        new ListWhisper().run();
    }

    /**
     * Initialise ui and parser and load data into list.
     * Catches IOException and print the error message by calling showError in Ui
     */
    public ListWhisper() {
        this.ui = new Ui();
        this.parser = new Parser();
        Storage storage = new Storage(this.ui);

        try {
            this.taskList = storage.load(this.parser);
        } catch (IOException e) {
            Ui.showError(e);
            taskList = new TaskList();
        }
    }

    /**
     * Start the application by showing a welcome message and getting user command
     * Exit when receive bye command
     * Catches errors and display their error message by calling showError method in Ui
     */
    private void run() {
        Ui.showWelcome();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.getUserCommand();
                Ui.showLine();
                Command c = this.parser.parse(fullCommand);
                c.execute(taskList, ui);
                isExit = c.isExit();
            } catch (Exception e) {
                Ui.showError(e);
            } finally {
                Ui.showLine();
            }
        }
    }
}