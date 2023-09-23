package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.UI.Ui;

public class Mark extends RCCommand {
    private String index;

    public Mark(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws RCException {
        taskList.markAsDone(index, ui);
    }
}
