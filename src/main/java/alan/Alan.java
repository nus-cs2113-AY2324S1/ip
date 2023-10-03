package alan;

import alan.data.TaskList;
import alan.data.exception.AlanException;
import alan.parser.Parser;
import alan.storage.Storage;
import alan.ui.Ui;

import java.io.FileNotFoundException;

/**
 * Represents the main class and entry point of the Alan chatbot program.
 */
public class Alan {
    private static Ui ui;
    private static TaskList tasks;
    private static Storage storage;

    /**
     * Represents the Alan constructor.
     * Initializes the Ui, Storage and TaskList.
     *
     * @param filePath filePath of the text file used to store task data.
     */
    public Alan(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Alan chatbot program.
     */
    public static void runAlan() {
        Parser parser = new Parser(tasks, ui);

        ui.showWelcomeMessage();

        String userInput = null;

        do {
            try {
                userInput = ui.getUserCommand();
                parser.processCommandHandler(userInput);
            } catch (AlanException e) {
                ui.showToUser(e.getMessage());
            } finally {
                ui.printHorizontalLine();
            }
        } while (!userInput.equals("bye"));

        try {
            storage.save();
        } catch (Exception e) {
            ui.showSavingError();
        }
    }

    /**
     * Main method of the Alan program.
     * @param args
     */
    public static void main(String[] args) {
        new Alan("data/tasks.txt").runAlan();
    }
}
