package Cara;

import Commands.Command;
import Tasks.TaskList;

import java.io.IOException;

/**
 * Duke is a task management program that allows users to manage their tasks.
 * It provides functionality for adding, deleting, and listing tasks.
 */
public class Cara {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructs a Duke instance with the specified file path.
     *
     * @param filePath The path to the file used for task storage.
     * @throws CaraException If there is an issue initializing Duke.
     */
    public Cara(String filePath) throws CaraException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTasks());
        } catch (CaraException | IOException e) {
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
            } catch (IOException | CaraException e) {
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
     * @throws CaraException If there is an issue with Duke initialization.
     */
    public static void main(String[] args) throws IOException, CaraException {
        new Cara("data/tasks.txt").run();
    }
}
