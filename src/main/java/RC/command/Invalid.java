package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents a command when an invalid input is received.
 */
public class Invalid extends RCCommand {
    private static final String MESSAGE_INVALID = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
    public Invalid() {
    }

    /**
     * Displays error message when an invalid command is received.
     *
     * @param taskList The task list containing all tasks.
     * @param ui The user interface for displaying messages.
     */
    @Override
    public void execute(TaskList taskList, Ui ui) {
        ui.showMessage(MESSAGE_INVALID);
    }
}
