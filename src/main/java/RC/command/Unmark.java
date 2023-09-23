package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

public class Unmark extends RCCommand {
    private String index;

    public Unmark(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.unmarkTask(index, ui);
    }
}
