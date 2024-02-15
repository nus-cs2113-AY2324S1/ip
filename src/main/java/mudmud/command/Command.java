package mudmud.command;

import mudmud.storage.Storage;
import mudmud.tasklist.TaskList;
import mudmud.ui.TextUi;

/**
 * Represent a command in the application.
 */
public abstract class Command {
    private boolean isExit;

    /**
     * Creates a new command.
     *
     * @param isExit Checks if it is an exit command.
     */
    public Command(boolean isExit) {
        this.isExit = isExit;
    }

    public boolean isExit() {
        return isExit;
    }

    /**
     * Executes the command's functions.
     *
     * @param tasks The task list
     * @param ui The application's UI
     * @param storage The application's storage
     */
    public abstract void executeCommand(TaskList tasks, TextUi ui, Storage storage);
}
