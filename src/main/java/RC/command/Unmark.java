package RC.command;

import RC.RCException;
import RC.TaskList;

public class Unmark extends RCCommand {
    private String index;

    public Unmark(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) throws RCException {
        taskList.unmarkTask(index);
    }
}
