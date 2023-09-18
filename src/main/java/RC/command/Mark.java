package RC.command;

import RC.RCException;
import RC.TaskList;
import RC.task.Task;

public class Mark extends RCCommand {
    private String index;

    public Mark(String index) {
        this.index = index;
    }

    @Override
    public void execute(TaskList taskList) throws RCException {
        taskList.markAsDone(index);
    }
}
