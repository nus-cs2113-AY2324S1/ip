package duke;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Duke is a task management program that allows users to manage their tasks.
 * It provides functionality for adding, deleting, and listing tasks.
 */
public class Duke {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The path to the file used for task storage.
     * @throws DukeException If there is an issue initializing Duke.
     */
    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (DukeException | IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program, handling user input and executing commands.
     */
    public void run() {
        ui.printWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks.getTasks(), ui, storage);
                isExit = c.isExit();
                Storage.saveTasks(tasks.getTasks());
            } catch (IOException | DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point for running the Duke program.
     *
     * @param args The command-line arguments (not used in this program).
     * @throws IOException   If an I/O error occurs.
     * @throws DukeException If there is an issue with Duke initialization.
     */
    public static void main(String[] args) throws IOException, DukeException {
        new Duke("data/tasks.txt").run();
    }
}
