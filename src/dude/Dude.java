package dude;

import java.util.ArrayList;

/**
 * The `Dude` class represents the main application class for the Dude task manager.
 * It initializes the user interface, storage, and task list, and manages the main
 * execution flow of the program.
 */
public class Dude {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a new `Dude` instance with the specified file path.
     *
     * @param filePath The file path for storing task data.
     */
    public Dude(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile(), storage, ui);
        } catch (Exception e) {
            // Display a generic error message or log the exception details
            ui.showMessage("Error loading tasks from file: " + e.getMessage());
            tasks = new TaskList(new ArrayList<>(), storage, ui);
        }
    }

    /**
     * Runs the Dude application, displaying a greeting message and entering an
     * infinite loop to process user commands until the user exits the program.
     */
    public void run() {
        ui.showGreeting();
        while (true) { // Infinite loop since exit is handled in the Parser
            try {
                String fullCommand = ui.readCommand();
                Parser.parse(fullCommand, tasks, ui, storage);
            } catch (DudeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * The entry point of the Dude application. Creates a new `Dude` instance with
     * a specified data file path and starts the application by calling the `run` method.
     *
     * @param args Command-line arguments (not used in this application).
     */
    public static void main(String[] args) {
        new Dude("dude.txt").run();
    }
}
