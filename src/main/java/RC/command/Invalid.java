package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

public class Invalid extends RCCommand {
    private static final String MESSAGE_INVALID = "\tOOPS!!! I'm sorry, but I don't know what that means :-(";
    public Invalid() {
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        ui.showMessage(MESSAGE_INVALID);
    }
}
