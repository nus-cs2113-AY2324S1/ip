package Command;

import Storage.Storage;
import Storage.TaskList;
import Ui.Ui;

/**
 * Represents a command that is not recognized.
 * An <code>InvalidCommand</code> corresponds to a command
 * that is unable to be executed and contains an <code>errorFeedback</code>.
 * */

public class InvalidCommand extends Command {

    public final String errorFeedback;

    public InvalidCommand(String errorFeedback) {
        this.errorFeedback = errorFeedback;
    }

    /**
     * Display the error message to the user.
     *
     * @param tasks The taskList object containing tasks
     * @param ui The ui object to display messages to users
     * @param taskFile The storage file for tasks to be stored
     * @return Boolean of whether to exit the application.
     * */
    @Override
    public boolean execute(TaskList tasks, Ui ui, Storage taskFile) {
        ui.displayLine();
        ui.displayError(this.errorFeedback);
        return false;
    }
}
