import lemon.commands.Command;
import lemon.exception.LemonException;
import lemon.file.Storage;
import lemon.task.TaskList;
import lemon.ui.UI;
import lemon.validation.Parser;

/**
 * Represents the main class for the Lemon chatbot.
 * It handles the initialization of the file management,
 * task management, user interaction, and input validation.
 */
public class Lemon {
    private final Storage storage;
    private TaskList tasks;
    private final UI ui;
    private final Parser parser = new Parser();

    /**
     * Constructs a Lemon instance with the specified file path.
     * Sets up user interaction and file management.
     * Creates a new task list from the specified file path, if have.
     *
     * @param filePath Path of the file storing task data.
     */
    public Lemon(String filePath) {
        ui = new UI();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (LemonException e) {
            ui.displayError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Initiates the main loop for the chatbot,
     * processes the user input and executes commands.
     * Displays messages and handles exceptions.
     */
    public void run() {
        ui.displayWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String input = ui.getInput();
                ui.displayDivider();
                Command c = parser.parseInput(input);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (LemonException e) {
                ui.displayError(e.getMessage());
            } finally {
                if (!isExit) {
                    ui.displayDivider();
                }
            }
        }
    }

    /**
     * Runs the chatbot with a specified file based on the file path.
     * Entry point of the chatbot.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Lemon("./data/lemon.txt").run();
    }
}
