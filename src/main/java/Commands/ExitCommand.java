package Commands;

import Cara.Storage;
import Tasks.Task;
import Cara.Ui;

import java.util.ArrayList;

/**
 * Represents a command to exit the Duke program.
 * The "bye" command is used to terminate the program.
 */
public class ExitCommand implements Command {

    /**
     * Executes the ExitCommand by displaying a farewell message.
     *
     * @param tasks   The list of tasks (not used in this command).
     * @param ui      The user interface.
     * @param storage The storage manager (not used in this command).
     */
    @Override
    public void execute(ArrayList<Task> tasks, Ui ui, Storage storage) {
        ui.printByeMessage();
    }

    /**
     * Checks if this command indicates program exit.
     *
     * @return True because this command indicates program exit.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
