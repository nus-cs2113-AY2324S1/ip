package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

/**
 * Represents commands that can be executed.
 */
public abstract class RCCommand {
    public RCCommand() {
    }

    public abstract void execute(TaskList taskList, Ui ui) throws RCException;
}
