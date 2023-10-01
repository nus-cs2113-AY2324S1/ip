import command.Command;
import exception.FrankException;
import task.TaskList;
import utility.CommandParser;
import utility.Storage;
import utility.Ui;

import java.io.IOException;
import java.util.Objects;

import static utility.Constants.FILEPATH;

public class Frank {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor checks if storage exists
     * If so, copies the TaskList from there
     * Else, creates a new TaskList
     * @param filePath The filepath where the storage file should be found
     */
    public Frank(String filePath) {
        ui = new Ui();
        ui.showWelcome();

        // Recover from storage or create new TaskList
        try {
            storage = new Storage(filePath);
        } catch (IOException | FrankException | NullPointerException e) {
            // System.out.println(e.getMessage());
            ui.showError(e.getMessage());
        }
        if(Objects.requireNonNull(storage).getTaskData() == null) {
            tasks = new TaskList();
        } else {
            tasks = new TaskList(storage.getTaskData());
        }
    }

    /**
     * Repeatedly runs commands, executes them then saves
     * Will stop once "bye" is run as it runs System.close()
     */
    public void run() {
        // Take commands
        Command command;
        while(true){
            try {
                command = CommandParser.getCommand();
                command.execute(tasks, ui);
                storage.setTaskData(tasks.getTaskData());
            } catch(FrankException | IOException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    /**
     * Starts the program
     * @param args Compiler-side
     */
    public static void main(String[] args) {
        new Frank(FILEPATH).run();
    }
}
